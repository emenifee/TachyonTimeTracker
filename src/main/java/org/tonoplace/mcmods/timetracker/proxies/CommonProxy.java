/**
 * 
 */
package org.tonoplace.mcmods.timetracker.proxies;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

/**
 * @author BlackTachyon
 *
 */
public class CommonProxy {

   /**
    * @param event 
    * 
    */
   public void loadStatistics(FMLPreInitializationEvent event) {
      // TODO Auto-generated method stub
      System.out.println("Common loadStatistics...");
   }

   /**
    * @param event
    */
   public void shutdownStatistics(FMLServerStoppingEvent event) {
      // TODO Auto-generated method stub
      System.out.println("Common shutdownStatistics...");
   }

   /**
    * 
    */
   public void autoSaveStats() {
      // TODO Auto-generated method stub
      System.out.println("Common autoSaveStats");
      
   }

}
