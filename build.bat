@echo off
echo Starting build...

call gradlew.bat clean build shadowJar
if errorlevel 1 (
    echo Build failed!
    exit /b 1
)

echo Build completed!
pause