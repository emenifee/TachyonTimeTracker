/**
 * 
 */
package org.tonoplace.mcmods.timetracker.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.tonoplace.mcmods.timetracker.TimeTrackerMod;

import org.apache.commons.codec.binary.*;

import net.minecraftforge.common.config.Configuration;

/**
 * @author BlackTachyon
 * Tracks a given statistic.
 */
public class TimeStatistic implements Serializable, Comparable<TimeStatistic> {
   private StatisticCategory category;
   private String playerName;
   private String statName;
   private long baseTime;
   private Date timeStamp;
   private boolean paused;
   private transient long lastSeconds;
   private transient String lastTime;
   private transient long UID;
   private static AtomicLong UID_GEN = new AtomicLong(0);
   
   public TimeStatistic()
   {
      UID = UID_GEN.incrementAndGet();
      category = StatisticCategory.Global;
      playerName = "";
      statName = "";
      reset();
   }
   
   public TimeStatistic(StatisticCategory category, String playerName, String statName) {
      UID = UID_GEN.incrementAndGet();
      this.category = category;
      this.playerName = playerName==null ? "" : playerName;
      this.statName = statName==null ? "" : statName;
      reset();
   }
   
   public String getName() {
      if(category==StatisticCategory.Global) {
         return statName;
      } else {
         return playerName + "." + statName;
      }
   }
   
   public synchronized void normalizeTime(Date normalizeTo) {
      if(normalizeTo==null)
         normalizeTo = new Date();
      if(!paused) {
         long sinceLastTimeStamp = normalizeTo.getTime() - timeStamp.getTime();
         baseTime += sinceLastTimeStamp;
      }
      timeStamp = normalizeTo;
   }
   
   public synchronized void reset() {
      baseTime = 0;
      timeStamp = new Date();
      paused = false;
      lastSeconds = -1;
      lastTime = "N/A";
   }
   
   public void pause() {
      normalizeTime(null);
      paused = true;
      
   }
   
   public void unpause() {
      pause(); // make sure we were paused/updated so we don't gain the time while we were paused...
      paused = false;
      
   }
   
   public String getTime()
   {
      long totalSeconds = getElapsedTime(new Date()) / 1000;
      // Skip additional calculation and formating if no measurable time has passed...  
      if (totalSeconds > lastSeconds) {
         lastSeconds  = totalSeconds;
         long seconds = totalSeconds % 60;
         long minutes = totalSeconds / 60 % 60;
         long hours   = totalSeconds / 3600 % 24;
         long days    = totalSeconds / 3600 / 24;
         lastTime     = String.format("%01dD %02d:%02d:%02d", days, hours, minutes, seconds);
      }
      return lastTime;
   }
  
   @Override
   public String toString() {
      return "TimeStatistic[" + category + ", " + getName() + ", " + (paused?"Paused":"Running")+ " @ " + getTime() +"]";
   }
   
   @Override
   public boolean equals(Object o) {
      TimeStatistic ts = (o instanceof TimeStatistic) ? (TimeStatistic)o : null;
      if(ts==null) return false;
      if(this==ts) return true;
      if(category==ts.category &&
         paused==ts.paused &&
         playerName.equals(ts.playerName) &&
         statName.equals(ts.statName))
      {
         Date timeCheck = new Date();
         return getElapsedTime(timeCheck) == ts.getElapsedTime(timeCheck);
      }
      return false;
   }
   
   @Override 
   public int hashCode() {
      return Arrays.hashCode(new Object[] { category, playerName, statName, paused } );
   }
   
   private synchronized long getElapsedTime(Date since) {
      if(since==null)
         since = new Date();
      if(paused)
         return baseTime;
      else
         return baseTime + since.getTime() - timeStamp.getTime();
   }

   public void load(String serialData) throws Exception {
      TimeStatistic loadedValue = deserializeString(serialData);
      baseTime = loadedValue.baseTime;
      timeStamp = loadedValue.timeStamp;
      paused = loadedValue.paused;
      TimeTrackerMod.logger.info("Loaded `" + this + "` from string.");      
   }

   /**
    * @param config
    */
   public synchronized void load(Configuration config) {
      String cat = category.toString();
      String baseKey = getName();
      try {
         String defaultValue = convertToSerializedString();
         String configValue = config.getString(baseKey, category.toString(), defaultValue, "Serialized `" + this +"` Base64 encoded.");
         if(defaultValue.equals(configValue)) {
            TimeTrackerMod.logger.info("Config file did not contain existing value for: " + this);
         } else {
            TimeStatistic loadedValue = deserializeString(configValue);
            baseTime = loadedValue.baseTime;
            timeStamp = loadedValue.timeStamp;
            paused = loadedValue.paused;
            TimeTrackerMod.logger.info("Loaded `" + this + "` from config file.");
         }
      } catch (Exception exception) {
         TimeTrackerMod.logger.error("Error loading config for " + this);
      }
   }
   /**
    * @return
    * @throws Exception 
    */
   public String toSerialString() throws Exception {
      return convertToSerializedString();
   }
   
   private String convertToSerializedString() throws Exception {
      String result = null;
      ByteArrayOutputStream baos = null;
      Exception caught = null;
      try {
         baos = new ByteArrayOutputStream();
         fillBufferWithSerializedData(baos);
         baos.flush();
         baos.close();
         byte[] serializedData = baos.toByteArray();
         result = Base64.encodeBase64String(serializedData);
      } catch (Exception baosException) {
         TimeTrackerMod.logger.error("Error converting to serialized string.", baosException);
         caught = baosException;
      } finally {
         if(baos!=null) {
            try {
               baos.close();
               baos.reset();
               baos = null;
            } catch (Exception baosCloseException) {
               TimeTrackerMod.logger.error("Error closing the BAOS while serializing the string...", baosCloseException);
               if(caught==null)
                  caught = baosCloseException;
            }
         }
      }
      if(caught!=null)
         throw caught;
      return result;
   }
   
   private void fillBufferWithSerializedData(ByteArrayOutputStream baos) throws Exception {
      ObjectOutputStream oos = null;
      Exception caught = null;
      try {
         oos = new ObjectOutputStream(baos);
         synchronized (this) {
            oos.writeObject(this);   
         }
         oos.flush();
         oos.close();
      } catch (Exception oosException) {
         TimeTrackerMod.logger.error("Error serializaing out data.", oosException);
         caught = oosException;
      }
      finally {
         if(oos!=null) {
            try {
               oos.close();
               oos = null;
            } catch (Exception oosCloseException) {
               TimeTrackerMod.logger.error("Error closing the OOS while serializing the data...", oosCloseException);
               if(caught==null)
                  caught = oosCloseException;
            }
         }
      }
      if(caught!=null)
         throw caught;
   }
   
   private static TimeStatistic deserializeString(String serializedString) throws Exception {
      byte[] serializedData = Base64.decodeBase64(serializedString);
      TimeStatistic returnValue = null;
      ByteArrayInputStream bais = null;
      Exception caught = null;
      try {
         bais = new ByteArrayInputStream(serializedData);
         ObjectInputStream ois = null;
         try {
            ois = new ObjectInputStream(bais);
            returnValue = (TimeStatistic) ois.readObject();
         } catch (Exception deSerializationException) {
            TimeTrackerMod.logger.error("Error deserializing a time statistic.", deSerializationException);
            caught = deSerializationException;
         } finally {
            if(ois!=null) {
               try {
                  ois.close();
                  ois = null;
               } catch (Exception oisCloseException) {
                  TimeTrackerMod.logger.error("Error closing the object input stream.", oisCloseException);
                  if(caught==null)
                     caught = oisCloseException;
               }
            }
         }
      } catch (Exception baisException) {
         TimeTrackerMod.logger.error("Error creating a data input stream.", baisException);
         if(caught==null)
            caught = baisException;
      } finally {
         if(bais!=null) {
            try {
               bais.close();
               bais = null;
            } catch (Exception baisCloseException) {
               TimeTrackerMod.logger.error("Error closing the data input stream.", baisCloseException);
               if(caught==null)
                  caught = baisCloseException;
            }
         }
      }
      if(caught!=null)
         throw caught;
      return returnValue;
   }

   /**
    * @param config
    */
   public void save(Configuration config) {
      // TODO Auto-generated method stub
      //config.
      
   }

   /**
    * @return
    */
   public String getPlayer() {
      return playerName;
   }

   /* (non-Javadoc)
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   @Override
   public int compareTo(TimeStatistic o) {
      if(o==null)
         return Integer.MIN_VALUE;
      if(this==o || this.equals(o) )
         return 0;
      int compare = Integer.compare(category.ordinal(), o.category.ordinal());
      if(compare!=0) return compare;
      compare = playerName.compareTo(o.playerName);
      if(compare!=0) return compare;
      compare = statName.compareTo(o.statName);
      if(compare!=0) return compare;
      Date now = new Date();
      compare = Long.compare(getElapsedTime(now), o.getElapsedTime(now));
      if(compare!=0) return compare;
      return Long.compare(UID, o.UID);
   }

   /**
    * @return
    */
   public StatisticCategory getCategory() {
      // TODO Auto-generated method stub
      return category;
   }

   /**
    * @return
    */
   public String getStatName() {
      // TODO Auto-generated method stub
      return statName;
   }

   
   
}
