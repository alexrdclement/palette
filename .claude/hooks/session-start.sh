#!/bin/bash
set -euo pipefail

# Claude Code on the web starts each session from a fresh, shallow clone that
# does not include git tags. The root project's release plugin
# (shipkit-auto-version, via com.alexrdclement.gradle.plugin.github.release)
# derives the project version from tags matching version.properties (0.0.*).
# Without tags it fails root-project configuration with:
#   "null cannot be cast to non-null type kotlin.String"
# which blocks every Gradle invocation. Fetch tags so the build can configure.

# Only needed in the remote (web) environment; local clones already have tags.
if [ "${CLAUDE_CODE_REMOTE:-}" != "true" ]; then
  exit 0
fi

git fetch --tags --force origin || true
