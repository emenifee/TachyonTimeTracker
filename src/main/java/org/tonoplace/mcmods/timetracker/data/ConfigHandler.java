/**
 * 
 */
package org.tonoplace.mcmods.timetracker.data;

import java.io.File;

import org.tonoplace.mcmods.timetracker.Timer;

import net.minecraftforge.common.config.Configuration;

/**
 * @author BlackTachyon
 *
 */
public class ConfigHandler {
   
   public static void init(File file) {
      
      Configuration config = new Configuration(file);
      config.load();
      Timer.MIN_TICKS = config.getInt("MinTicks", "AutoSaveStatisticsTiming", Timer.DEFAULT_MIN_TICKS, Timer.MIN_AUTO_SAVE_TICKS, Timer.MAX_AUTO_SAVE_TICKS, "The minimum amount of server ticks to wait before checking if it is time to save all the Time Statistics");
      Timer.MAX_TICKS = config.getInt("MaxTicks", "AutoSaveStatisticsTiming", Timer.DEFAULT_MAX_TICKS, Timer.MIN_AUTO_SAVE_TICKS, Timer.MAX_AUTO_SAVE_TICKS, "The maximum amount of server ticks to wait before checking if it is time to save all the Time Statistics");
      Timer.AUTO_SAVE_MINUTES = config.getInt("AutoSaveMinutes", "AutoSaveStatisticsTiming", Timer.DEFAULT_AUTO_SAVE_MINUTES, Timer.MIN_MINUTES, Timer.MAX_MINUTES, "The number of minutes between file saves of the Time Statisitics");
      config.save();
   }

}
