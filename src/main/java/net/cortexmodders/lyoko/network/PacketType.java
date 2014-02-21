package net.cortexmodders.lyoko.network;


public enum PacketType
{
    PLAYER_INFORMATION(PacketPlayerInformation.class),
    CONSOLE_COMMAND(PacketConsoleCommand.class);
    
    public final Class<? extends PacketLyoko> packetClass;
    
    private PacketType(Class<? extends PacketLyoko> packetClass)
    {
        this.packetClass = packetClass;
    }
}
