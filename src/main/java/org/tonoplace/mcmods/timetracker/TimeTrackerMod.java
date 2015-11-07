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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tonoplace.mcmods.timetracker.Config.Config;
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
 */
@Mod(modid = ModInfo.MODID, name=ModInfo.MOD_NAME, version = ModInfo.VERSION)
public class TimeTrackerMod
{
	 /**
     * Public available instance of this mod.
     */
    @Instance(ModInfo.MODID)
    public static TimeTrackerMod instance;
    
    /**
     * Global logger for this mod.
     */
    public static Logger logger = LogManager.getFormatterLogger(ModInfo.MODID);
    
    /**
     * Proxy to handle separate code paths for server and client.
     */
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
      Config.preInit(event);
    	proxy.loadStatistics(event);
    }
    
    /**
     * Initialization section, initiate and add any new default stats as needed.
     * @param event The FMLInitializationEvent, marking this mode for the mod initialization lifecycle of FML.
     */
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
    
    /**
     * Post-initialization section, communicate/cordinate with other mods as needed.
     * @param event The FMLPostInitializationEvent, marking this method for the post-init lifecycle of FML.
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
       FMLCommonHandler.instance().bus().register(new AutoSaveTimer());
    }
    
    @EventHandler
    public void shutdown(FMLServerStoppingEvent event)
    {
       logger.info("TimeTracker detected server stopping event...");
       proxy.shutdownStatistics(event);
    }
}
