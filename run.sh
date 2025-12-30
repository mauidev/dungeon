#!/bin/bash

# Dungeon Crawl CLI - Build and Run Script

set -e

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$PROJECT_DIR"

echo "Building Dungeon Crawl..."
mvn -DskipTests clean package

echo ""
echo "Running Dungeon Crawl..."
java -jar target/cli-0.1.0.jar
