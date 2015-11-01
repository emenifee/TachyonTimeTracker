package org.tonoplace.mcmods.timetracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tonoplace.mcmods.timetracker.data.Statistics;
import org.tonoplace.mcmods.timetracker.proxies.CommonProxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import net.minecraft.init.Blocks;

/**
 * Minecraft mod to support tracking time based statistics.
 * @author BlackTachyon
 *
 */
@Mod(modid = ModInfo.MODID, name=ModInfo.MOD_NAME, version = ModInfo.VERSION)
public class TimeTrackerMod
{
	/**
     * Public available instance of this mod.
     */
    @Instance(ModInfo.MODID)
    public static TimeTrackerMod instance;
    
    public static Logger logger = LogManager.getFormatterLogger(ModInfo.MODID);
    
    private Timer events = new Timer();
    

    @SidedProxy(clientSide = "org.tonoplace.mcmods.timetracker.proxies.ClientProxy",
                serverSide = "org.tonoplace.mcmods.timetracker.proxies.ServerProxy")
    public static CommonProxy proxy;
    
    /**
     * Pre-initialization section, register block-ids, read config, assign
     * network channels, etc...
     * @param event The FMLPreInitializationEvent, marking this method for the pre-init lifecycle of FML. 
     *    
     */
    @EventHandler
    void preInit(FMLPreInitializationEvent event)
    {
    	proxy.loadStatistics(event);
    }
    
    /**
     * Initialization section, initiate and add any new default stats as needed.
     * @param event The FMLInitializationEvent, marking this mode for the mod initialization lifecycle of FML.
     */
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	// internal mod setup comms
		// some example code
      //  System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
      //  System.out.println("Anvil >> " + Blocks.anvil.getUnlocalizedName());
    }
    
    /**
     * Post-initialization section, communicate/cordinate with other mods as needed.
     * @param event The FMLPostInitializationEvent, marking this method for the post-init lifecycle of FML.
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
       //FMLCommonHandler.instance().getEffectiveSide()
    	// connect to other mods...
       //cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent
       FMLCommonHandler.instance().bus().register(new Timer());
       //MinecraftForge.EVENT_BUS.register(events);
       //MinecraftForge.
    }
    
    @EventHandler
    public void shutdown(FMLServerStoppingEvent event)
    {
       //System.out.println("TimeTracker detected server stopping event...");
       logger.info("TimeTracker detected server stopping event...");
       proxy.shutdownStatistics(event);
    }

   /**
    * 
    */
   public static void autoSave() {
      // TODO Auto-generated method stub
      proxy.autoSaveStats();
      
   }

    ////////////////////////////////////////////////////////////////////////
    // PRIVATE INTERNAL VARIABLES
    ////////////////////////////////////////////////////////////////////////
    
    
     
}
