///**
// * 
// */
//package org.tonoplace.mcmods.timetracker.data;
//
//import java.io.File;
//
//import org.tonoplace.mcmods.timetracker.AutoSaveTimer;
//
//import net.minecraftforge.common.config.Configuration;
//
///**
// * @author BlackTachyon
// *
// */
//public class ConfigHandler {
//   
//   public static void init(File file) {
//      
//      Configuration config = new Configuration(file);
//      config.load();
//      AutoSaveTimer.MIN_TICKS = config.getInt("MinTicks", "AutoSaveStatisticsTiming", AutoSaveTimer.DEFAULT_MIN_TICKS, AutoSaveTimer.MIN_AUTO_SAVE_TICKS, AutoSaveTimer.MAX_AUTO_SAVE_TICKS, "The minimum amount of server ticks to wait before checking if it is time to save all the Time Statistics");
//      AutoSaveTimer.MAX_TICKS = config.getInt("MaxTicks", "AutoSaveStatisticsTiming", AutoSaveTimer.DEFAULT_MAX_TICKS, AutoSaveTimer.MIN_AUTO_SAVE_TICKS, AutoSaveTimer.MAX_AUTO_SAVE_TICKS, "The maximum amount of server ticks to wait before checking if it is time to save all the Time Statistics");
//      AutoSaveTimer.AUTO_SAVE_MINUTES = config.getInt("AutoSaveMinutes", "AutoSaveStatisticsTiming", AutoSaveTimer.DEFAULT_AUTO_SAVE_MINUTES, AutoSaveTimer.MIN_MINUTES, AutoSaveTimer.MAX_MINUTES, "The number of minutes between file saves of the Time Statisitics");
//      config.save();
//   }
//
//}
