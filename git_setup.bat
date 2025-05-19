@echo off
setlocal enabledelayedexpansion

echo Initializing Git repository for LAB...

:: Check if Git is installed
where git >nul 2>nul
if %ERRORLEVEL% neq 0 (
    echo Error: Git is not installed or not in PATH
    echo Please install Git from https://git-scm.com/download/win
    pause
    exit /b 1
)

:: Prompt for GitHub repository URL
set /p REPO_URL="https://github.com/macdogiahuy/Data-structure-and-algorithm"

:: Initialize Git repository
git init
if %ERRORLEVEL% neq 0 (
    echo Error: Failed to initialize Git repository
    pause
    exit /b 1
)

:: Add all files
git add .
if %ERRORLEVEL% neq 0 (
    echo Error: Failed to add files
    pause
    exit /b 1
)

:: Prompt for commit message
set /p COMMIT_MSG="Enter your commit message: "

:: Create commit with user's message
git commit -m "%COMMIT_MSG%"
if %ERRORLEVEL% neq 0 (
    echo Error: Failed to create commit
    pause
    exit /b 1
)

:: Add GitHub remote
git remote add origin %REPO_URL%
if %ERRORLEVEL% neq 0 (
    echo Error: Failed to add remote repository
    pause
    exit /b 1
)

:: Push to GitHub
git push -u origin main
if %ERRORLEVEL% neq 0 (
    echo Error: Failed to push to GitHub
    pause
    exit /b 1
)

echo.
echo Changes have been successfully pushed to GitHub
echo.
echo Setup completed successfully!
echo.
pause