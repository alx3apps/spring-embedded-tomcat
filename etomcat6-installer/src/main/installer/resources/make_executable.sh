#!/bin/bash
set -e
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
find "$DIR" -name "*.sh" -exec chmod +x {} \;