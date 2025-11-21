#!/bin/bash

# Define project directories and jar names
PROJECT1_DIR="configserver"
PROJECT1_JAR="build/libs/configserver-0.0.1-SNAPSHOT.jar"
PROJECT1_PORT=8071

PROJECT2_DIR="accounts"
PROJECT2_JAR="build/libs/accounts-0.0.1-SNAPSHOT.jar"
PROJECT2_PORT=8080

PROJECT3_DIR="loans"
PROJECT3_JAR="build/libs/loans-0.0.1-SNAPSHOT.jar"
PROJECT3_PORT=8090

PROJECT4_DIR="cards"
PROJECT4_JAR="build/libs/cards-0.0.1-SNAPSHOT.jar"
PROJECT4_PORT=9000

# Function to start a project
start_project() {
    local dir=$1
    local jar=$2
    local port=$3
    local log="logs/${dir}.log"

    echo "----------------------------------------------------"
    echo "Starting $dir on port $port, logging to $log"
    mkdir -p logs # Ensure logs directory exists

    # Run the application in the background, redirecting output to a log file
    # and ensuring it continues after the shell exits using nohup and &
    (cd "$dir" && nohup java -jar -Dserver.port="$port" "$jar" > "$log" 2>&1 &)

    # Capture the process ID (PID) of the last background command
    # NOTE: This approach has caveats if the subshell immediately exits.
    # A more robust solution involves writing the PID to a file within the subshell.
    # For a simple script, we just note they are started.

    echo "$dir started. Check $log for details."
}

# Start all projects
start_project "$PROJECT1_DIR" "$PROJECT1_JAR" "$PROJECT1_PORT"
start_project "$PROJECT2_DIR" "$PROJECT2_JAR" "$PROJECT2_PORT"
start_project "$PROJECT3_DIR" "$PROJECT3_JAR" "$PROJECT3_PORT"
start_project "$PROJECT4_DIR" "$PROJECT4_JAR" "$PROJECT4_PORT"

echo "----------------------------------------------------"
echo "All projects are starting up in the background."
echo "TODO: ----------------------------------------------"
echo "Use 'tail -f logs/*.log' to monitor their status."
echo "Use a separate script to manage/kill processes if needed."
