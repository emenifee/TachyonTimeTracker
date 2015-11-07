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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.tonoplace.mcmods.timetracker.AutoSaveTimer;
import org.tonoplace.mcmods.timetracker.ModInfo;
import org.tonoplace.mcmods.timetracker.TimeTrackerMod;
import org.tonoplace.mcmods.timetracker.client.RenderUtils.HUDPositions;

import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

/**
 * @author BlackTachyon
 * 
 *         Code pulled from Tonius: Master branch of Tonis/SimplyJetpacks
 *         SimplyJetpacks/src/main/java/tonius/simplyjetpacks/config/Config.java
 *         on 20151101_1102 CST
 *         https://github.com/Tonius/SimplyJetpacks/blob/master/src/main/java/
 *         tonius/simplyjetpacks/config/Config.java
 * 
 *         Heavily modified to work in this mod.
 *
 */
public class Config {
   public static final List<Section> configSections = new ArrayList<Section>();
   private static final Section      sectionSave    = new Section(false, "Auto Save Settings", "autosave");
   private static final Section      sectionGui     = new Section(true, "GUI Settings", "gui");
   public static Configuration       config;
   public static Configuration       configClient;

   // autosave
   public static int                 Min_Check_Ticks;
   public static int                 Max_Check_Ticks;
   public static int                 Auto_Save_Minutes;

   // gui
   public static int                 HUDPosition    = Defaults.HUDPosition;
   public static int                 HUDOffsetX     = Defaults.HUDOffsetX;
   public static int                 HUDOffsetY     = Defaults.HUDOffsetY;
   public static double              HUDScale       = Defaults.HUDScale;

   public static void preInit(FMLPreInitializationEvent evt) {
      FMLCommonHandler.instance().bus().register(new Config());

      config = new Configuration(new File(evt.getModConfigurationDirectory(), ModInfo.MODID + ".cfg"));
      configClient = new Configuration(new File(evt.getModConfigurationDirectory(), ModInfo.MODID + "-client.cfg"));

      syncConfig();
   }

   private static void syncConfig() {
      TimeTrackerMod.logger.info("Loading configuration files");
      try {
         processConfig();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         config.save();
         configClient.save();
      }
   }

   public static void onConfigChanged(String modid) {
      if (modid.equals(ModInfo.MODID)) {
         syncConfig();
      }
   }

   private static void processConfig() {

      Min_Check_Ticks = config.getInt(sectionSave.name, "MIN Check Ticks", Defaults.MIN_CHECK_TICKS,
            AutoSaveTimer.MIN_AUTO_SAVE_TICKS, AutoSaveTimer.MAX_AUTO_SAVE_TICKS,
            "Minimum number of server ticks to wait before checking if it is time to auto-save the collection of time statistics.");
      Max_Check_Ticks = config.getInt(sectionSave.name, "MAX Check Ticks", Defaults.MAX_CHECK_TICKS,
            AutoSaveTimer.MIN_AUTO_SAVE_TICKS, AutoSaveTimer.MAX_AUTO_SAVE_TICKS,
            "Maximum number of server ticks to wait before checking if it is time to auto-save the collection of time statistics.");
      Auto_Save_Minutes = config.getInt(sectionSave.name, "Auto Save Interval", Defaults.AUTO_SAVE_MINUTES,
            AutoSaveTimer.MIN_MINUTES, AutoSaveTimer.MAX_MINUTES,
            "Number of minutes between auto-save of time statistics.");

      HUDPosition = configClient
            .get(sectionGui.name, "HUD Base Position", Defaults.HUDPosition,
                  "The base position of the HUD on the screen. 0 = top left, 1 = top center, 2 = top right, 3 = left, 4 = right, 5 = bottom left, 6 = bottom right")
            .setMinValue(0).setMaxValue(HUDPositions.values().length - 1).getInt(Defaults.HUDPosition);
      HUDOffsetX = configClient
            .get(sectionGui.name, "HUD Offset - X", Defaults.HUDOffsetX,
                  "The HUD display will be shifted horizontally by this value. This value may be negative.")
            .getInt(Defaults.HUDOffsetX);
      HUDOffsetY = configClient
            .get(sectionGui.name, "HUD Offset - Y", Defaults.HUDOffsetY,
                  "The HUD display will be shifted vertically by this value. This value may be negative.")
            .getInt(Defaults.HUDOffsetY);
      HUDScale = Math.abs(configClient
            .get(sectionGui.name, "HUD Scale", Defaults.HUDScale,
                  "How large the HUD will be rendered. Default is 1.0, can be bigger or smaller")
            .setMinValue(0.001D).getDouble(Defaults.HUDScale));
   }

   @SubscribeEvent
   public void onConfigChanged(OnConfigChangedEvent evt) {
      onConfigChanged(evt.modID);
   }

}
