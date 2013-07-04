CodeLyokoMod
============

Code Lyoko Minecraft Mod

Current Release: Minecraft 1.5.2
Version: 0.5.0

Author: Matthew Warren

Co-Developers: Andrew Silver, MarQ Blue, Jake

Dimensions/Portals Manager: Andrew Silver

Main Developer: Matthew Warren

Textures: MarQ Blue, Matthew Warren

Download
========

The official download is on the [Minecraft Forum post](http://www.minecraftforum.net/topic/1403995-152-code-lyoko-mod-043-minecraft-forum/).

We also have a Jenkins system doing nightly builds for us. Please do note that these are experimental and most likely will contain bugs.

http://ci.jadarstudios.com/job/Code-Lyoko/

[![Build Status](http://ci.jadarstudios.com/job/Code-Lyoko/badge/icon)](http://ci.jadarstudios.com/job/Code-Lyoko/)

Building
========

Requirements: JDK, Ant, and whatever you need to compile with MCP.

To build, make a clean MCP with Forge installed. Then make a new file in the git clone directory called build.properties
and put this in it.

```
mcpLocation=[LOCATION TO MCP]
```

Make sure to replace the [LOCATION TO MCP] with the location to your MCP directory. Then cd to the directory you cloned
the repository to and run "ant build".
