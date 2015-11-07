/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Elijah C Menifee
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
 * 
 */
package org.tonoplace.mcmods.timetracker;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tonoplace.mcmods.timetracker.Config.Config;
import org.tonoplace.mcmods.timetracker.Config.Defaults;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

/**
 * @author BlackTachyon
 *
 * Provides a tick timer to determine when the statistics should be saved/backed-up.
 */
public class AutoSaveTimer {
   private static final Logger logger                    = LogManager.getFormatterLogger(ModInfo.MODID);
   public static final int     MIN_AUTO_SAVE_TICKS       = 10;
   public static final int     MAX_AUTO_SAVE_TICKS       = 20 * 60 * 60;
   public static final int     MIN_MINUTES               = 1;
   public static final int     MAX_MINUTES               = 120;
   int                         count                     = 0;
   private Date                lastSaved                 = new Date();
   private int                 nextCheck                 = -1;
   private int                 secondsDiff               = -1;

   @SubscribeEvent
   public void onServerTick(ServerTickEvent ste) {
      if (nextCheck < Config.Min_Check_Ticks) {
         secondsDiff = (int) ((lastSaved.getTime() + Config.Auto_Save_Minutes * 60 * 1000 - (new Date()).getTime()) / 1000);
         nextCheck = calculateNextCheck(secondsDiff);
//         long maxTicks = Config.AUTO_SAVE_MINUTES * 60 * 20;
//         nextCheck = (int) (maxTicks / 3);
//         nextCheck = Math.min(Config.MAX_CHECK_TICKS, nextCheck);
//         nextCheck = Math.max(Config.MIN_CHECK_TICKS, nextCheck);
//         logger.info("Setting auto-save server check to " + nextCheck + " server ticks.");
      }
      if (ste.phase == TickEvent.Phase.START) {
         count++;
         count = count % nextCheck;
         if (count == 0) {
            logger.info("ServerTickEvent...(" + nextCheck + ")");
            int secondsDiff = (int) ((lastSaved.getTime() - (new Date()).getTime()) / 1000) + Config.Auto_Save_Minutes * 60;
            if (secondsDiff <= 0) {
               logger.info("Server auto-saving stats...);");
               TimeTrackerMod.proxy.autoSaveStats();
               lastSaved = new Date();
               nextCheck = calculateNextCheck(Config.Auto_Save_Minutes * 60);
            } else {
               logger.info("Remaining seconds till next auto-save: " + secondsDiff);
               //nextCheck = secondsDiff * 20 / 3;
               //nextCheck = Math.min(Config.MAX_CHECK_TICKS, nextCheck);
               //nextCheck = Math.max(Config.MIN_CHECK_TICKS, nextCheck);
               nextCheck = calculateNextCheck(secondsDiff);
               //logger.info("Setting auto-save server check to " + nextCheck + " server ticks.");
            }
         }
      }
   }
   private int calculateNextCheck(int secondsDiff) {
      int totalTicks = secondsDiff * 20;
      // wait approx 85% of remaining time before checking again
      int waitTicks = totalTicks * 85 / 100;
      // bound wait ticks to within min/max check values.
      waitTicks = Math.min(Config.Max_Check_Ticks, waitTicks);
      waitTicks = Math.max(Config.Min_Check_Ticks, waitTicks);
      logger.info("Setting auto-save server check to " + waitTicks + " server ticks.");
      return waitTicks;
   }
}
