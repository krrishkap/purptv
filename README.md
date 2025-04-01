# PurpleTV

An open source modification of the Twitch Android Application

## Build Guide

### 1. Preparation
**The final modification and build process has been tested on Windows. You may use Linux or macOS, but the author does not guarantee any successful results.**

#### Requirements:
- **Platform:** Windows 10 or later
- **Software:** Android Studio 2024.3.1 Patch 1, Java SDK 17, Python 3.13, Git
- **Libraries:** pyaxmlparser (Python), Android SDK 35

### 2. Build Process
The build process consists of two major stages:

1. **Decompiling the original APK**: Using `apktool`, applying fixes and patches, adding/replacing resources and libraries. The result should be a nearly complete working version without the `monolith` module.
2. **Building the `monolith` module (`app.dex`)**: This module contains all the core mod logic, business logic, and integrations with Twitch and third-party services.

Let's go through the build process in more detail.

#### 2.1 Environment Setup & Decompilation

1. Clone this repository using Git or download the source code as a ZIP archive. Git is required for a proper build.
2. Install Python 3.13 and create a virtual environment (`.venv`) in toolchain dir. Install `pyaxmlparser` within the virtual environment.
3. The main tool for the build process is `app.py` located in the `toolchain` directory. Available commands:

   - `--decompile` – Decompiles the APK file. Extracts resources (XML, images, binary data) and `smali` files, applies basic fixes, and initializes Git for tracking changes, generating, and applying patches.
   - `--recompile` – Runs a series of tasks to rebuild the mod. Uses `apktool` to recompile the APK, copies mod resources, integrates third-party libraries and mod `dex` files, signs final APK.
   - `--check` – Checks patch compatibility before applying them. If errors are detected, they should be fixed, and new patches should be generated using `--generate`. Restore the original state with `--restore`.
   - `--apply` – Applies patches after decompilation.
   - `--make` – A shortcut for running `--decompile`, `--apply`, and `--recompile` in sequence.

#### Decompilation Steps:

1. Obtain or build the base APK file of the application. Only full APK files are supported (no split APKs).
2. Generate a JAR file from the Twitch library:
   - Copy the APK file to the `apk` directory.
   - Run the command: `--jar`
3. Set up two environment variables for signing:
   - `PURPLE_TV_SIGN_DIR` – Path to the signing key.
   - `PURPLE_TV_KEY_PASS` – Password for signing.
4. Carefully review the console output during the decompilation process.

After decompilation, the output directory should contain the necessary files for patch application and verification.

#### Build Steps:

1. Run `--check` to ensure no patch errors.
2. Run `--apply` to apply patches.
3. Open the `app` project in Android Studio and build it.
   - In **Build Variants**, select `Release`.
   - Ensure that the APK is built without errors.
4. Run `--recompile` to finalize the build.

TODO: continue

# Apk Release website 
[PurpleTV ](https://PurpleTV.aeong.win/)

# Telegram Hub
[nHub](https://t.me/pubTwChat)

# PurpleTV Wiki 
[PurpleTV Wiki](https://nopbreak.ru/purpletv/wiki)
