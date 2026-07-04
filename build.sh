#!/usr/bin/env bash
set -e

echo "Starting build..."
./gradlew clean shadowJar

echo "Build completed!"