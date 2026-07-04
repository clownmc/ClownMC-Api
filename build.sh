#!/usr/bin/env bash
set -e

echo "Starting build..."
./gradlew clean build shadowJar

echo "Build completed!"