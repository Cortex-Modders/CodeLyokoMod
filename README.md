Code Lyoko Minecraft Mod
============

Current Release: Minecraft 1.6.4
Version: 0.5.0

Authors: Matthew Warren, Jake R., Andrew Silver, MarQ Blue
Dimensions/Portals Manager: Andrew Silver
Main Developers: Matthew Warren, Jake R
Textures: MarQ Blue, Matthew Warren

###Download

The official download is on the [Minecraft Forum post](http://www.minecraftforum.net/topic/1403995-152-code-lyoko-mod-043-minecraft-forum/).

We also have Travis-CI making builds for us. The downloads can be found at repo.jadarstudios.com.

[![Build Status](https://travis-ci.org/Cortex-Modders/CodeLyokoMod.png?branch=master)](https://travis-ci.org/Cortex-Modders/CodeLyokoMod)

###Setting up IDE

Becasue Code Lyoko is a Gradle project, Gradle will automatically set up a workspace/project for either Eclipse or IDEA.

1. Open command line.
2. Change the directory to the project root.
3. Run `./gradlew setupDevWorkspace` to set up forge. If you want to have the deobfuscated source, replace `setupDevWorkspace` with `setupDecompWorkspace`.
4. Run `./gradlew eclipse` to generate the Eclipse project. If you want IDEA then replace `eclipse` with `idea`.
5. Import the project (or IDEA module) into the workspace.

Run configuration are as follows.

#####Main class  
`net.minecraft.launchwrapper.Launch`   
#####Program Arguments  
`--version 1.6 --tweakClass cpw.mods.fml.common.launcher.FMLTweaker`  
#####VM Arguments
`-Dfml.ignoreInvalidMinecraftCertificates=true`  
If you're in IDEA then you need to add this to the VM arguments.  
`-Djava.library.path="FullSystemPathToRepoClone/build/natives"`  
Replace `FullSystemPathToRepoClone` with the full path to the root of the repo.
#####Working directory
Preferrably something empty like the ```eclipse/``` directory.

###Building

Requirements: JDK

Open a command line, and change the directory to the base directory of the project. Run `gradlew setupDevWorkspace build`. The builds are in `build/libs/C`
