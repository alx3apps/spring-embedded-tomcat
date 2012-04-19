#!/bin/bash
# find java
if [ "x$JAVA_HOME" = "x" ] ; then
    RES="$( which java 2>/dev/null )"
else
    RES="$JAVA_HOME"/bin/java
fi
# check results
if [ "x$RES" = "x" ] ; then
    echo "Cannot find java executable, check JAVA_HOME, aborting" 1>&2
    exit 1
else
    echo "$RES"
fi
