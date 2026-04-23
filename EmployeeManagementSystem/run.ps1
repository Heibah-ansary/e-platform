#!/usr/bin/env pwsh
# Employee Management System - Build and Run Script (PowerShell)
# This script compiles and runs the Employee Management System

Clear-Host

Write-Host "`n"
Write-Host "╔═══════════════════════════════════════════════════════════════╗"
Write-Host "║                                                               ║"
Write-Host "║          EMPLOYEE MANAGEMENT SYSTEM - BUILD & RUN             ║"
Write-Host "║                                                               ║"
Write-Host "╚═══════════════════════════════════════════════════════════════╝"
Write-Host "`n"

# Check if Java is installed
try {
    $javaVersion = java -version 2>&1
    Write-Host "✅ Java is installed"
    Write-Host $javaVersion[0]
} catch {
    Write-Host "❌ ERROR: Java is not installed or not in PATH"
    Write-Host "`nPlease install Java Development Kit (JDK) from:"
    Write-Host "https://www.oracle.com/java/technologies/downloads/"
    Write-Host "`n"
    Read-Host "Press Enter to exit"
    exit 1
}

Write-Host "`n"

# Create bin directory
Write-Host "Creating output directory..."
if (-not (Test-Path "bin")) {
    New-Item -ItemType Directory -Path "bin" -Force | Out-Null
    Write-Host "✅ Created 'bin' directory"
} else {
    Write-Host "✅ 'bin' directory already exists"
}

Write-Host "`n"

# Compile all Java files
Write-Host "Compiling Java files..."
Write-Host "`n"

$modelFiles = Get-ChildItem -Path "src\com\employeemgmt\model\*.java" -ErrorAction SilentlyContinue
$serviceFiles = Get-ChildItem -Path "src\com\employeemgmt\service\*.java" -ErrorAction SilentlyContinue
$appFiles = Get-ChildItem -Path "src\com\employeemgmt\app\*.java" -ErrorAction SilentlyContinue

$allFiles = @($modelFiles.FullName) + @($serviceFiles.FullName) + @($appFiles.FullName)

& javac -d bin $allFiles 2>&1

if ($LASTEXITCODE -ne 0) {
    Write-Host "`n"
    Write-Host "❌ COMPILATION FAILED!"
    Write-Host "`n"
    Write-Host "Please check:"
    Write-Host "1. All Java files are in correct directories"
    Write-Host "2. No syntax errors in source code"
    Write-Host "3. Java version compatibility"
    Write-Host "`n"
    Read-Host "Press Enter to exit"
    exit 1
}

Write-Host "`n"
Write-Host "✅ Compilation successful!"
Write-Host "`n"

# Run the application
Write-Host "═══════════════════════════════════════════════════════════════"
Write-Host "Starting Employee Management System..."
Write-Host "═══════════════════════════════════════════════════════════════"
Write-Host "`n"

& java -cp bin com.employeemgmt.app.EmployeeManagementSystem

Write-Host "`n"
Write-Host "═══════════════════════════════════════════════════════════════"
Write-Host "Thank you for using Employee Management System!"
Write-Host "═══════════════════════════════════════════════════════════════"
Write-Host "`n"
