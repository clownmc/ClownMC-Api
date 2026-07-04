@echo off
echo Starting build...

call gradlew.bat clean shadowJar
if errorlevel 1 (
    echo Build failed!
    exit /b 1
)

echo Build completed!
pause