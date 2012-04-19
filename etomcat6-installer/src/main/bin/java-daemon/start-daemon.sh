#!/bin/bash
set -e
# get dirs
DAEMON_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
BIN_DIR="$( dirname "$DAEMON_DIR" )"
APP_DIR="$( dirname "$BIN_DIR" )"
# get paths
PID_FILE="$APP_DIR"/.pid
OUT_FILE="$APP_DIR"/logs/std.out
# get java
JAVA="$( "$DAEMON_DIR"/find-java.sh )"
# check pid file
if [ -f "$PID_FILE" ] ; then
    echo "Pid file exists, path: "$PID_FILE", pid: $( cat "$PID_FILE" )"
    exit 1
fi
# startup
nohup "$JAVA" $JVM_ARGS -jar "$BIN_DIR"/"$@" > "$OUT_FILE" 2>&1 &
PID="$!"
echo "$PID" > "$PID_FILE"
echo "Started, pid: $PID"
