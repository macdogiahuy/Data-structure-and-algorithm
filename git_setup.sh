#!/bin/bash

# Initialize Git repository
git init

# Add all files
git add .

# Create initial commit
git commit -m "Initial commit: CSD201 Course Implementation"

# Add GitHub remote (replace YOUR_USERNAME with actual GitHub username)
git remote add origin https://github.com/macdogiahuy/Data-structure-and-algorithm.gits

# Push to GitHub
git push -u origin main

echo "Repository has been initialized and pushed to GitHub"
echo "Don't forget to:"
echo "1. Create a GitHub repository named 'CSD201'"
echo "2. Replace YOUR_USERNAME in this script with your actual GitHub username"
echo "3. Make sure you have GitHub authentication set up"
