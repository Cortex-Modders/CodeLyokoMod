/**
 * Copyright (c) Jadar, 2013
 * Developer Capes API by Jadar
 * License: Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * version 1.3.1
 */
package com.jadarstudios.api.developercapesapi;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DeveloperCapesTickHandler implements ITickHandler {

    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final DeveloperCapesAPI instance = DeveloperCapesAPI.getInstance();

    private HashSet<String> playersChanged = new HashSet<String>();
    
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        /*
         * Will not run if there is no world, and if there are player entities
         * in the playerEntities list.
         */
        if ((mc.theWorld != null) && (mc.theWorld.playerEntities.size() > 0)){

            // Grabs a list of all the players, and the world.
            @SuppressWarnings("unchecked")
            List<EntityPlayer> players = mc.theWorld.playerEntities;

            // A loop that goes through each player
            for (int counter = 0; counter < players.size(); counter++){

                // Helps keep from getting an ArrayOutOfBoundException
                if (players.get(counter) != null){

                    // Gets the player from the players list.
                    EntityPlayer player = players.get(counter);
                    
                    if (playersChanged.contains(player.username)){
                        /*
                         * Lowers the case of the Username, so that there are no
                         * problems with the Username's case.
                         */
                        String lowerUsername = player.username.toLowerCase();

                        if (instance.getUserGroup(lowerUsername) != null){

                            /*
                             * Gets the user from the hash map and gets the cape
                             * URL.
                             */
                            
                            AbstractClientPlayer test = (AbstractClientPlayer)player;
                            
                            String userGroup = instance.getUserGroup(lowerUsername);
                            String groupUrl = instance.getGroupUrl(userGroup);
                            ResourceLocation capeLocation = instance.getCapeLocation(userGroup);
                            
                            // Sets the cape URL.
  //                          player.cloakUrl = groupUrl;
//                            test.func_110307_b(AbstractClientPlayer.func_110299_g(player.username), capeLocation);
//                            test.field_110313_e = test.func_110299_g(test.username);
//                            test.field_110315_c = func_110307_b(test.field_110313_e, test.username);
                            playersChanged.add(player.username);
                        }
                    }
                }
            }
        }
    }

    /*
     * Not used, stub method.
     */
    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel() {
        return "DeveloperCapesTickHandler";
    }
}