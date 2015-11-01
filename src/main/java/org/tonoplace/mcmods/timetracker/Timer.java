/**
 * 
 */
package org.tonoplace.mcmods.timetracker;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

/**
 * @author BlackTachyon
 *
 */
public class Timer {
   private static final Logger logger                    = LogManager.getFormatterLogger(ModInfo.MODID);
   public static final int     MIN_AUTO_SAVE_TICKS       = 10;
   public static final int     DEFAULT_MIN_TICKS         = 100;
   public static final int     MAX_AUTO_SAVE_TICKS       = 20 * 60 * 60;
   public static final int     DEFAULT_MAX_TICKS         = 20 * 60 * 5;
   public static final int     DEFAULT_AUTO_SAVE_MINUTES = 15;
   public static final int     MIN_MINUTES               = 1;
   public static final int     MAX_MINUTES               = 120;
   public static int           MIN_TICKS                 = DEFAULT_MIN_TICKS;
   public static int           MAX_TICKS                 = DEFAULT_MAX_TICKS;
   public static int           AUTO_SAVE_MINUTES         = DEFAULT_AUTO_SAVE_MINUTES;
   int                         count                     = 0;
   private Date                lastSaved                 = new Date();
   private int                 nextCheck                 = -1;

   @SubscribeEvent
   public void onServerTick(ServerTickEvent ste) {
      if (nextCheck < MIN_TICKS) {
         long maxTicks = AUTO_SAVE_MINUTES * 60 * 20;
         nextCheck = (int) (maxTicks / 3);
         nextCheck = Math.min(MAX_TICKS, nextCheck);
         nextCheck = Math.max(MIN_TICKS, nextCheck);
         logger.info("Setting auto-save server check to " + nextCheck + " server ticks.");
      }
      if (ste.phase == TickEvent.Phase.START) {
         count++;
         // logger.info("precount: " + count + " on " + nextCheck);
         count = count % nextCheck;
         // logger.info("postcount: " + count);
         if (count == 0) {
            logger.info("ServerTickEvent...(" + nextCheck + ")");
            Date now = new Date();
            int secondsDiff = (int) ((lastSaved.getTime() + AUTO_SAVE_MINUTES * 60 * 1000 - now.getTime()) / 1000);
            if (secondsDiff <= 0) {
               logger.info("Server auto-saving stats...);");
               TimeTrackerMod.autoSave();
               lastSaved = now;
               nextCheck = -1;
            } else {
               logger.info("Remaining seconds till next auto-save" + secondsDiff);
               nextCheck = secondsDiff * 20 / 3;
               nextCheck = Math.min(MAX_TICKS, nextCheck);
               nextCheck = Math.max(MIN_TICKS, nextCheck);
               logger.info("Setting auto-save server check to " + nextCheck + " server ticks.");
            }
         }
      }
   }
}
