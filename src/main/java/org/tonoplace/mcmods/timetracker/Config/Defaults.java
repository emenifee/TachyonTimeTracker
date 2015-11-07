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
package org.tonoplace.mcmods.timetracker.Config;

import org.tonoplace.mcmods.timetracker.client.RenderUtils.HUDPositions;

/**
 * @author BlackTachyon
 * 
 * Code pulled from Tonius:
 * Master branch of Tonis/SimplyJetpacks 
 * SimplyJetpacks/src/main/java/tonius/simplyjetpacks/config/Defaults.java on 20151101_1709 CST
 * https://github.com/Tonius/SimplyJetpacks/blob/master/src/main/java/tonius/simplyjetpacks/config/Defaults.java
 * 
 * Heavily modified to work in this mod.
 *
 */
public abstract class Defaults {
   // autosave
   public static final int MIN_CHECK_TICKS = 50;
   public static final int MAX_CHECK_TICKS = 20 * 60 * 5;
   public static final int AUTO_SAVE_MINUTES = 15;

   // gui
   public static final int HUDPosition = HUDPositions.TOP_LEFT.ordinal();
   public static final int HUDOffsetX = 0;
   public static final int HUDOffsetY = 0;
   public static final double HUDScale = 1.0D;   
}
