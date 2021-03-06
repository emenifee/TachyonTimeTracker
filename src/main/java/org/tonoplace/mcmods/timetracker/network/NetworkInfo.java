/**
 * 
 */
package org.tonoplace.mcmods.timetracker.network;

import org.tonoplace.mcmods.timetracker.network.client.HandleReplyAvailable;
import org.tonoplace.mcmods.timetracker.network.client.HandleReplyCurrent;
import org.tonoplace.mcmods.timetracker.network.client.HandleReplyOther;
import org.tonoplace.mcmods.timetracker.network.server.HandleRequestAdd;
import org.tonoplace.mcmods.timetracker.network.server.HandleRequestAvailable;
import org.tonoplace.mcmods.timetracker.network.server.HandleRequestDrop;
import org.tonoplace.mcmods.timetracker.network.server.HandleRequestReset;
import org.tonoplace.mcmods.timetracker.network.server.HandleRequestStatistics;
import org.tonoplace.mcmods.timetracker.network.server.HandleRequestToggle;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * Network black-box to wire up all IMessage and IMessageHandlers. Based on
 * information found at
 * http://www.minecraftforge.net/forum/index.php/topic,20135.0.html
 * 
 * @author BlackTachyon
 */
public final class NetworkInfo {
   
   /**
    * Simple network for passing messages between client and server, initialized
    * by class static initializer.
    */
   public static final SimpleNetworkWrapper network;

   /**
    * Simple network implementation channel this mod uses to communicate between
    * client and server, initialized by class static initializer.
    */
   public static final String               CHANNEL;
   
   /**
    * Method registers the IMessage and IMessageHandlers for client and server.
    */
   public static void initializeComms() {
      initializeServerHandlers();
      initializeClientHandlers();
   }
   
   /**
    * Tracks used channel discriminators, initialized by class static
    * initializer.
    */
   private static int                       channelDiscriminator;
   
   /**
    * For client to request the values of current stats, initialized by class
    * static initializer.
    */
   private static final int                 REQUEST_STATS_DISCRIMINATOR;
   
   /**
    * For client to request the list of available stats, initialized by class
    * static initializer.
    */
   private static final int                 REQUEST_AVAILABLE_DISCRIMINATOR;
   
   /**
    * For client to request a specific stat is reset, initialized by class
    * static initializer.
    */
   private static final int                 REQUEST_RESET_DISCRIMINATOR;
   
   /**
    * For client to request a new stat be added, initialized by class static
    * initializer.
    */
   private static final int                 REQUEST_ADD_DISCRIMINATOR;
   
   /**
    * For client to request a stat to be removed, initialized by class static
    * initializer.
    */
   private static final int                 REQUEST_DEL_DISCRIMINATOR;
   
   /**
    * For client to request a stat's the toggle of a stat as current,
    * initialized by class static initializer.
    */
   private static final int                 REQUEST_TOGGLE_DISCRIMINATOR;
   
   /**
    * For server to reply with current stat values, initialized by class static
    * initializer.
    */
   private static final int                 REPLY_STATS_DISCRIMINATOR;

   /**
    * For server to reply with available stats, initialized by class static
    * initializer.
    */
   private static final int                 REPLY_AVAILABLE_DISCRIMINATOR;

   /**
    * For server to reply to Rest,Add,Delete and Toggle with outcome
    * information, initialized by class static initializer.
    */
   private static final int                 REPLY_OTHER_DISCRIMINATOR;

   /**
    * Initializes internal constants.
    */
   static {
      CHANNEL = "TimeTrackerComm";
      channelDiscriminator = 0;
      REQUEST_STATS_DISCRIMINATOR     = ++channelDiscriminator;
      REQUEST_AVAILABLE_DISCRIMINATOR = ++channelDiscriminator;
      REQUEST_RESET_DISCRIMINATOR     = ++channelDiscriminator;
      REQUEST_ADD_DISCRIMINATOR       = ++channelDiscriminator;
      REQUEST_DEL_DISCRIMINATOR       = ++channelDiscriminator;
      REQUEST_TOGGLE_DISCRIMINATOR    = ++channelDiscriminator;
      REPLY_STATS_DISCRIMINATOR       = ++channelDiscriminator;
      REPLY_AVAILABLE_DISCRIMINATOR   = ++channelDiscriminator;
      REPLY_OTHER_DISCRIMINATOR       = ++channelDiscriminator;
      // per http://www.minecraftforge.net/forum/index.php/topic,20135.0.html
      // maximum PacketID available on channel is 255
      if (NetworkInfo.channelDiscriminator > 255) {
         throw new ExceptionInInitializerError(
               "Number of assigned channel discriminators on '" + NetworkInfo.CHANNEL + "' exceeds maximum.");
      } else {
         System.out.println("Using " + NetworkInfo.channelDiscriminator
               + " of 255 possible packet discriminators on network channel '" + NetworkInfo.CHANNEL + "'.");
      }
      network = NetworkRegistry.INSTANCE.newSimpleChannel(NetworkInfo.CHANNEL);
   }


   /**
    * Method registers the IMessage sent to and the IMessageHandlers for the
    * client.
    */
   private static void initializeClientHandlers() {
      network.registerMessage(HandleReplyAvailable.class, ReplyAvailableStatistics.class, REPLY_AVAILABLE_DISCRIMINATOR, Side.CLIENT);
      network.registerMessage(HandleReplyCurrent.class, ReplyCurrentStatistics.class, REPLY_STATS_DISCRIMINATOR, Side.CLIENT);
      network.registerMessage(HandleReplyOther.class, ReplyOther.class, REPLY_OTHER_DISCRIMINATOR, Side.CLIENT);
   }

   /**
    * Method registers the IMessage sent to and the IMessageHandlers for the
    * server.
    */
   private static void initializeServerHandlers() {
      network.registerMessage(HandleRequestAdd.class, RequestAddStatistic.class, REQUEST_ADD_DISCRIMINATOR, Side.SERVER);
      network.registerMessage(HandleRequestAvailable.class, RequestAvailableStatistics.class, REQUEST_AVAILABLE_DISCRIMINATOR, Side.SERVER);
      network.registerMessage(HandleRequestDrop.class, RequestDropStatistic.class, REQUEST_DEL_DISCRIMINATOR, Side.SERVER);
      network.registerMessage(HandleRequestReset.class, RequestStatisticReset.class, REQUEST_RESET_DISCRIMINATOR, Side.SERVER);
      network.registerMessage(HandleRequestStatistics.class, RequestStatistics.class, REQUEST_STATS_DISCRIMINATOR, Side.SERVER);
      network.registerMessage(HandleRequestToggle.class, RequestToggleCurrentStatistic.class, REQUEST_TOGGLE_DISCRIMINATOR, Side.SERVER);

   }

}
