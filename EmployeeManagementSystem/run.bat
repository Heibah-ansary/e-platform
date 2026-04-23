@echo off
REM Employee Management System - Build and Run Script
REM This script compiles and runs the Employee Management System

cls
echo.
echo ╔═══════════════════════════════════════════════════════════════╗
echo ║                                                               ║
echo ║          EMPLOYEE MANAGEMENT SYSTEM - BUILD & RUN             ║
echo ║                                                               ║
echo ╚═══════════════════════════════════════════════════════════════╝
echo.

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo ❌ ERROR: Java is not installed or not in PATH
    echo.
    echo Please install Java Development Kit (JDK) from:
    echo https://www.oracle.com/java/technologies/downloads/
    echo.
    pause
    exit /b 1
)

echo ✅ Java is installed
java -version
echo.

REM Create bin directory
echo Creating output directory...
if not exist bin (
    mkdir bin
    echo ✅ Created 'bin' directory
) else (
    echo ✅ 'bin' directory already exists
)
echo.

REM Compile all Java files
echo Compiling Java files...
echo.

javac -d bin src\com\employeemgmt\model\*.java src\com\employeemgmt\service\*.java src\com\employeemgmt\app\*.java

if errorlevel 1 (
    echo.
    echo ❌ COMPILATION FAILED!
    echo.
    echo Please check:
    echo 1. All Java files are in correct directories
    echo 2. No syntax errors in source code
    echo 3. Java version compatibility
    echo.
    pause
    exit /b 1
)

echo.
echo ✅ Compilation successful!
echo.

REM Run the application
echo ═══════════════════════════════════════════════════════════════
echo Starting Employee Management System...
echo ═══════════════════════════════════════════════════════════════
echo.

java -cp bin com.employeemgmt.app.EmployeeManagementSystem

echo.
echo ═══════════════════════════════════════════════════════════════
echo Thank you for using Employee Management System!
echo ═══════════════════════════════════════════════════════════════
echo.
pause
