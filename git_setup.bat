@echo off
echo Initializing Git repository for CSD201...

:: Initialize Git repository
git init

:: Add all files
git add .

:: Create initial commit
git commit -m "Initial commit: CSD201 Course Implementation"

:: Add GitHub remote (replace YOUR_USERNAME with actual GitHub username)
git remote add origin https://github.com/YOUR_USERNAME/CSD201.git

:: Push to GitHub
git push -u origin main

echo.
echo Repository has been initialized and pushed to GitHub
echo.
echo Please make sure to:
echo 1. Create a GitHub repository named 'CSD201'
echo 2. Replace YOUR_USERNAME in this script with your actual GitHub username
echo 3. Make sure you have GitHub authentication set up
echo.
pause