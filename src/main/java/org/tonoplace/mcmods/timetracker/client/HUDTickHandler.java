/**
 * 
 */
package org.tonoplace.mcmods.timetracker.client;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraft.client.Minecraft;

/**
 * @author BlackTachyon
 *
 */
public class HUDTickHandler {
   private static final Minecraft mc = Minecraft.getMinecraft();
   
   private static void tickEnd() {
      if(mc.thePlayer != null) {
         if(   (    mc.currentScreen==null 
                 || Config.showHUDWhileChatting) 
             && !mc.gameSettings.hideGUI
             && !mc.gameSettings.showDebugInfo) {
            
            GL11.glPushMatrix();
            mc.entityRenderer.setupOverlayRendering();
            GL11.glScaled(Config.HUDScale, Config.HUDScale, 1.0d);
            
            String[] testLines = new String[] { "Test line 1", "Test line 2" };
            int i = 0;
            for (String s : testLines) {
               drawStringAtHUDPosition(s, HUDPositions.values()[Config.HUDPosition], mc.fontRenderer, Config.HUDOffsetX, Config.HUDOffsetY, Config.HUDScale, 0xeeeeee, true, i);
               i++;
            }
            
            GL11.glPopMatrix();
         }
      }
   }
   
   @SubscribeEvent
   public void onRenderTick(RenderTickEvent evt) {
      if(evt.phase == Phase.END && Config.enableTimeHUD) {
         tickEnd();
      }
   }

}
