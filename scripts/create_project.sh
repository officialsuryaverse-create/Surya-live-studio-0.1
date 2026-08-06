#!/data/data/com.termux/files/usr/bin/bash

set -e

echo "=================================="
echo " Creating Surya Live Studio"
echo "=================================="

mkdir -p app/src/main/java/com/surya/livestudio
mkdir -p app/src/main/res/layout
mkdir -p app/src/main/res/values
mkdir -p app/src/main/assets
mkdir -p app/src/main/res/drawable
mkdir -p .github/workflows

touch app/src/main/AndroidManifest.xml
touch app/src/main/java/com/surya/livestudio/MainActivity.kt
touch app/src/main/java/com/surya/livestudio/SplashActivity.kt
touch app/src/main/res/layout/activity_main.xml
touch app/src/main/res/layout/activity_splash.xml
touch app/src/main/res/values/colors.xml
touch app/src/main/res/values/themes.xml
touch app/src/main/res/values/strings.xml

echo "Project structure created successfully."
