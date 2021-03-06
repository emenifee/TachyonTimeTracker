/**
 * 
 */
package org.tonoplace.mcmods.timetracker.proxies;

import java.io.File;
import java.io.IOException;

import org.tonoplace.mcmods.timetracker.ModInfo;
import org.tonoplace.mcmods.timetracker.TimeTrackerMod;
import org.tonoplace.mcmods.timetracker.Config.Config;
//import org.tonoplace.mcmods.timetracker.data.ConfigHandler;
import org.tonoplace.mcmods.timetracker.data.Statistics;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

/**
 * @author BlackTachyon
 *
 */
public class ServerProxy extends CommonProxy {

   public static Statistics stats;
   private File modDir;
   
   @Override
   public void loadStatistics(FMLPreInitializationEvent event) {
      super.loadStatistics(event);
      modDir = new File(event.getModConfigurationDirectory(),ModInfo.MODID);
      try {
         TimeTrackerMod.logger.info("Server needs to loadStatistics(), from directory: " + modDir.getCanonicalPath());
         //ConfigHandler.init(f);
         stats = new Statistics();
         stats.load(modDir);
         stats.listStatistics();
      } catch (Exception e) {
         // TODO Auto-generated catch block
         TimeTrackerMod.logger.error("Error getting statistics data...",e);
      }
   }
   
   @Override
   public void shutdownStatistics(FMLServerStoppingEvent event) {
      super.shutdownStatistics(event);
      try {
         TimeTrackerMod.logger.info("Server needs to saveStatistics(), to directory: " + modDir.getCanonicalPath());
         stats.shutdown(modDir);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         TimeTrackerMod.logger.error("Error saving statistics data during shutdown...",e);
      }
   }
   
   @Override
   public void autoSaveStats() {
      super.autoSaveStats();
      try
      {
         TimeTrackerMod.logger.info("Server auto-saving the stats, to directory: " + modDir.getCanonicalPath());
         stats.save(modDir);
      } catch (Exception e) {
         TimeTrackerMod.logger.error("Error auto-saving statistics...",e);
      }
   }

}
