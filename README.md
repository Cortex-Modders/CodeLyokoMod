CodeLyokoMod
============

Code Lyoko Minecraft Mod

Current Release: Minecraft 1.6.2
Version: 0.5.0

Author: Matthew Warren

Co-Developers: Andrew Silver, MarQ Blue, Jake

Dimensions/Portals Manager: Andrew Silver

Main Developer: Matthew Warren

Textures: MarQ Blue, Matthew Warren

###Download

The official download is on the [Minecraft Forum post](http://www.minecraftforum.net/topic/1403995-152-code-lyoko-mod-043-minecraft-forum/).

We also have a Jenkins system doing nightly builds for us. Please do note that these are experimental and most likely will contain bugs.

http://ci.jadarstudios.com/job/Code-Lyoko/

[![Build Status](http://ci.jadarstudios.com/job/Code-Lyoko/badge/icon)](http://ci.jadarstudios.com/job/Code-Lyoko/)

###Setting up Eclipse
Setting up Eclipse for Code Lyoko is pretty easy. Code Lyoko is a [Gradle](http://www.gradle.org/) project, and uses the [ForgeGradle](https://github.com/MinecraftForge/ForgeGradle) Gradle plugin. Install the Eclipse Gradle plugin into your Eclipse, and choose Import->Gradle->Gradle Project. Set the root folder to the place you cloned the Code Lyoko repository to. Click "Build Model." Choose the project that comes up. Next click the "Run before" check box, and add "clean setupDevWorkspace" to the text box next to it. Thats it, click finish and Eclipse is set up!

###Building

Requirements: JDK

Open a command line, and change the directory to the base directory of the project. Run `gradlew setupDevWorkspace build`. The builds are in `projectRoot/build/libs/CodeLyoko-*.jar`
