#!/usr/bin/env bash


tasks_dump(){
  /usr/bin/lynx -dump http://localhost:8080/crud/v1/task/getTasks
}

if /bin/bash ./runcrud.bsh; then
  /bin/echo $0": runcrud.bsh finished."
else
  /bin/echo $0": runcrud.bsh does not run."
fi
/bin/echo $0": start answer from CRUD"
tasks_dump
/bin/echo -e "\n$0: stop answer"