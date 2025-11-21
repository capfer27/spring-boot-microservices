#!/bin/bash

echo "Sending SIGTERM to all Spring Boot processes..."

# Use pkill with the -f flag to match the full command line ("java -jar")
pkill -f "java -jar"

echo "Check system for remaining processes using: ps aux | grep 'java -jar'"