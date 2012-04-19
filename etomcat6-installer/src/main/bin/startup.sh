#!/bin/bash
set -e
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
"$DIR"/java-daemon/start-daemon.sh etomcat6-installer.jar $@