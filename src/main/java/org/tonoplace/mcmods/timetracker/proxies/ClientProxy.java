/**
 * 
 */
package org.tonoplace.mcmods.timetracker.proxies;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author BlackTachyon
 *
 */
public class ClientProxy extends CommonProxy {
/*   private static void tickEnd() {
      if (mc.thePlayer != null) {
          if ((mc.currentScreen == null || Config.showHUDWhileChatting && mc.currentScreen instanceof GuiChat) && !mc.gameSettings.hideGUI && !mc.gameSettings.showDebugInfo) {
              ItemStack chestplate = mc.thePlayer.getCurrentArmor(2);
              if (chestplate != null && chestplate.getItem() instanceof IHUDInfoProvider) {
                  IHUDInfoProvider provider = (IHUDInfoProvider) chestplate.getItem();
                  
                  List<String> info = new ArrayList<String>();
                  provider.addHUDInfo(info, chestplate, Config.enableFuelHUD, Config.enableStateHUD);
                  if (info.isEmpty()) {
                      return;
                  }
                  
                  GL11.glPushMatrix();
                  mc.entityRenderer.setupOverlayRendering();
                  GL11.glScaled(Config.HUDScale, Config.HUDScale, 1.0D);
                  
                  int i = 0;
                  for (String s : info) {
                      RenderUtils.drawStringAtHUDPosition(s, HUDPositions.values()[Config.HUDPosition], mc.fontRenderer, Config.HUDOffsetX, Config.HUDOffsetY, Config.HUDScale, 0xeeeeee, true, i);
                      i++;
                  }
                  
                  GL11.glPopMatrix();
              }
          }
      }
  }
  
  @SubscribeEvent
  public void onRenderTick(RenderTickEvent evt) {
      if (evt.phase == Phase.END && (Config.enableFuelHUD || Config.enableStateHUD)) {
          tickEnd();
      }
  }
*/
   
/*   package tonius.simplyjetpacks.client.util;

   import net.minecraft.client.Minecraft;
   import net.minecraft.client.gui.FontRenderer;
   import net.minecraft.client.gui.ScaledResolution;
   import net.minecraft.client.model.ModelBiped;
   import net.minecraft.entity.EntityLivingBase;
   import net.minecraft.entity.player.EntityPlayer;
   import tonius.simplyjetpacks.client.model.ModelFluxPack;
   import tonius.simplyjetpacks.client.model.ModelJetpack;
   import tonius.simplyjetpacks.item.meta.PackBase;

   public abstract class RenderUtils {
       
       private static final Minecraft mc = Minecraft.getMinecraft();
       
       public static void drawStringLeft(String string, FontRenderer fontRenderer, int x, int y, int color, boolean shadow) {
           fontRenderer.drawString(string, x, y, color, shadow);
       }
       
       public static void drawStringCenter(String string, FontRenderer fontRenderer, int x, int y, int color, boolean shadow) {
           fontRenderer.drawString(string, x - fontRenderer.getStringWidth(string) / 2, y, color, shadow);
       }
       
       public static void drawStringRight(String string, FontRenderer fontRenderer, int x, int y, int color, boolean shadow) {
           fontRenderer.drawString(string, x - fontRenderer.getStringWidth(string), y, color, shadow);
       }
       
       public static void drawStringAtHUDPosition(String string, HUDPositions position, FontRenderer fontRenderer, int xOffset, int yOffset, double scale, int color, boolean shadow, int lineOffset) {
           ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
           
           int screenWidth = res.getScaledWidth();
           screenWidth /= scale;
           int screenHeight = res.getScaledHeight();
           screenHeight /= scale;
           
           switch (position) {
           case TOP_LEFT:
               yOffset += lineOffset * 9;
               drawStringLeft(string, fontRenderer, 2 + xOffset, 2 + yOffset, color, shadow);
               break;
           case TOP_CENTER:
               yOffset += lineOffset * 9;
               drawStringCenter(string, fontRenderer, screenWidth / 2 + xOffset, 2 + yOffset, color, shadow);
               break;
           case TOP_RIGHT:
               yOffset += lineOffset * 9;
               drawStringRight(string, fontRenderer, screenWidth - 2 + xOffset, 2 + yOffset, color, shadow);
               break;
           case LEFT:
               yOffset += lineOffset * 9;
               drawStringLeft(string, fontRenderer, 2 + xOffset, screenHeight / 2 + yOffset, color, shadow);
               break;
           case RIGHT:
               yOffset += lineOffset * 9;
               drawStringRight(string, fontRenderer, screenWidth - 2 + xOffset, screenHeight / 2 + yOffset, color, shadow);
               break;
           case BOTTOM_LEFT:
               yOffset -= lineOffset * 9;
               drawStringLeft(string, fontRenderer, 2 + xOffset, screenHeight - 9 + yOffset, color, shadow);
               break;
           case BOTTOM_RIGHT:
               yOffset -= lineOffset * 9;
               drawStringRight(string, fontRenderer, screenWidth - 2 + xOffset, screenHeight - 9 + yOffset, color, shadow);
           }
       }
       
       public enum HUDPositions {
           TOP_LEFT,
           TOP_CENTER,
           TOP_RIGHT,
           LEFT,
           RIGHT,
           BOTTOM_LEFT,
           BOTTOM_RIGHT
       }
       
       public static ModelBiped getArmorModel(PackBase pack, EntityLivingBase entity) {
           ModelBiped model = null;
           switch (pack.armorModel) {
           case JETPACK:
               model = ModelJetpack.INSTANCE;
               break;
           case FLUX_PACK:
               model = ModelFluxPack.INSTANCE;
           default:
           }
           if (model == null) {
               return null;
           }
           model.isSneak = entity.isSneaking();
           model.isRiding = entity.isRiding();
           model.isChild = entity.isChild();
           model.heldItemRight = entity.getEquipmentInSlot(0) != null ? 1 : 0;
           if (entity instanceof EntityPlayer) {
               model.aimedBow = ((EntityPlayer) entity).getItemInUseDuration() > 2;
           }
           return model;
       }
       
   }
*/
}
