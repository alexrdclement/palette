#!/bin/bash
# SessionStart hook for Claude Code on the web.
#
# The environment setup script (pasted into the environment settings) installs
# the toolchain -- JDK 17, the Android SDK platforms/build-tools, git-lfs, and a
# seeded Gradle distribution -- because that work is environment-level and gets
# snapshotted. But three things depend on the freshly-cloned repository, which
# does not exist when the setup script runs, so they must happen per session,
# here, after checkout:
#
#   1. A version tag. The clone is shallow with no tags. The
#      alexrdclement.github.release plugin reads shipkit-auto-version's
#      previous-tag in the root project's afterEvaluate and casts it to a
#      non-null String; with no tag matching the "0.0.*" spec it is null and
#      every Gradle task dies with
#      "null cannot be cast to non-null type kotlin.String".
#   2. local.properties. AGP needs the SDK location; writing sdk.dir is the most
#      reliable way to provide it (it's gitignored, so the tree stays clean).
#   3. Paparazzi golden images. They are stored in Git LFS and arrive as pointer
#      files unless hydrated, which makes verifyPaparazzi fail.
set -euo pipefail

# Only relevant in the remote (web) environment; a local clone already has tags,
# its own local.properties, and hydrated LFS objects.
if [ "${CLAUDE_CODE_REMOTE:-}" != "true" ]; then
  exit 0
fi

REPO="${CLAUDE_PROJECT_DIR:-$(git rev-parse --show-toplevel)}"
cd "$REPO"

# 1. Ensure shipkit-auto-version has a previous tag to read.
if [ -z "$(git tag -l)" ]; then
  git fetch --tags --quiet origin 2>/dev/null || true
  if [ -z "$(git tag -l)" ]; then
    # No tags reachable; a baseline matching the version spec is enough to
    # unblock configuration (build/test tasks don't need the real version).
    git tag v0.0.0 2>/dev/null || true
  fi
fi

# 2. Point AGP at the SDK installed by the setup script.
SDK="${ANDROID_HOME:-${ANDROID_SDK_ROOT:-/opt/android-sdk}}"
if [ -d "$SDK" ] && ! grep -qs '^sdk.dir=' local.properties 2>/dev/null; then
  echo "sdk.dir=$SDK" >> local.properties
fi

# 3. Hydrate the Git LFS golden images for Paparazzi.
if command -v git-lfs >/dev/null 2>&1; then
  git lfs pull 2>/dev/null || true
fi

echo "session-start hook: tags=[$(git tag -l | tr '\n' ' ')] sdk.dir -> $SDK, LFS hydrated"
