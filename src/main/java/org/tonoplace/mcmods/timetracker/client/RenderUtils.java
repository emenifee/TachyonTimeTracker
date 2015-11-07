/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014 Ton Broekhuizen
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.tonoplace.mcmods.timetracker.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

/**
 * @author BlackTachyon
 * 
 * Code grabbed from Tonius:
 * Master branch of Tonis/SimplyJetpacks 
 * SimplyJetpacks/src/main/java/tonius/simplyjetpacks/client/util/RenderUtils.java on 20151101_1715 CST
 * https://github.com/Tonius/SimplyJetpacks/blob/master/src/main/java/tonius/simplyjetpacks/client/util/RenderUtils.java
 * 
 * Modified to work in this mod.
 *
 */
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
}
