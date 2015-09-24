#!/bin/bash

if [ "$1" == 'start' ]; then
  echo 'Starting Gemfire..';

  pushd . 1>/dev/null;
  mkdir /tmp/gemfire 2>/dev/null;
  cd /tmp/gemfire;
  gfsh start locator --name=LocatorX --bind-address=localhost --port=10334;
  gfsh start server --name=ServerA --locators=localhost[10334];
  gfsh -e "connect" -e "create region --type=REPLICATE --name=FooRegion";
  gfsh -e "connect" -e "put --key=abc --value=5 --region=FooRegion";
  gfsh -e "connect" -e "put --key=xyz --value=9 --region=FooRegion";
  gfsh -e "connect" -e "list members" -e "list regions";
  popd;

  echo 'Gemfire started';
elif [ "$1" == 'stop' ]; then
  echo 'Stopping Gemfire..';

  kill -5 `jps | grep ServerLauncher | awk '{print $1}'` 2>/dev/null;
  kill -5 `jps | grep LocatorLauncher | awk '{print $1}'` 2>/dev/null;

  echo 'Gemfire stopped';
else
  echo 'Usage: bin/gemfire.sh start|stop';
fi

echo 'Done';
