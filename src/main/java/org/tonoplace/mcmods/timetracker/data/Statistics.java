/**
 * 
 */
package org.tonoplace.mcmods.timetracker.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tonoplace.mcmods.timetracker.TimeTrackerMod;



/**
 * @author BlackTachyon
 *
 */
public class Statistics {
   private TimeStatistic totalUpTime;
   private TimeStatistic currentUpTime;
   private Map<String,TimeStatistic> stats;
   private Set<String> players;
   
   public Statistics() {
      stats = new HashMap<String, TimeStatistic>();
      players = new HashSet<String>();
      add(new TimeStatistic(StatisticCategory.Global,"","SinceServerWorldCreated"));
      add(totalUpTime = new TimeStatistic(StatisticCategory.Global,"","TotalUpTime"));
      add(currentUpTime = new TimeStatistic(StatisticCategory.Global,"","CurrentUpTime"));
   }

   public void listStatistics()
   {
      List<TimeStatistic> statsList = new ArrayList<TimeStatistic>();
      statsList.addAll(stats.values());
      Collections.sort(statsList);
      for(TimeStatistic ts : statsList)
         TimeTrackerMod.logger.info(ts.toString());
   }
   
   public void load(File dataDirectory) throws Exception {
      if(!dataDirectory.exists())
         dataDirectory.mkdirs();
      if(!dataDirectory.exists())
         throw new Exception("Unable to create DataDirectory " +dataDirectory.getCanonicalPath());
      if(!dataDirectory.isDirectory())
         throw new Exception("Not a directory: " + dataDirectory.getCanonicalPath());
      File dataFile = new File(dataDirectory,"Statistics.dat");
      if(!dataFile.exists()) {
         TimeTrackerMod.logger.info("Do not yet have data!");
         save(dataDirectory);
      }
      InputStreamReader isr = new InputStreamReader(new FileInputStream(dataFile));
      BufferedReader br = new BufferedReader(isr);
      boolean process = true;
      while(process == true) {
         String comment = br.readLine();
         if(comment!=null) {
            String category = br.readLine();
            String player = br.readLine();
            String stat = br.readLine();
            String serialData = br.readLine();
            TimeStatistic fromFile = new TimeStatistic(StatisticCategory.valueOf(category),player,stat);
            fromFile.load(serialData);
            add(fromFile);
         } else {
            process = false;
         }
      }
      br.close();
      totalUpTime = stats.get(totalUpTime.getName());
      currentUpTime = stats.get(currentUpTime.getName());
      totalUpTime.unpause();
      currentUpTime.reset();
      save(dataDirectory);
   }
   
   public void save(File dataDirectory) throws Exception {
      if(!dataDirectory.exists())
         dataDirectory.mkdirs();
      if(!dataDirectory.exists())
         throw new Exception("Unable to create DataDirectory " +dataDirectory.getCanonicalPath());
      if(!dataDirectory.isDirectory())
         throw new Exception("Not a directory: " + dataDirectory.getCanonicalPath());
      File dataFile = new File(dataDirectory,"Statistics.dat");
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dataFile)));
      Date normalizeTime = new Date();
      for(TimeStatistic ts : stats.values())
      {
         ts.normalizeTime(normalizeTime);
         String comment = "# " + ts.toString();
         String category = ts.getCategory().toString();
         String player = ts.getPlayer();
         String stat = ts.getStatName();
         String serialData = ts.toSerialString();
         bw.write(comment + "\n");
         bw.write(category + "\n");
         bw.write(player +"\n");
         bw.write(stat +"\n");
         bw.write(serialData +"\n");
      }
      bw.flush();
      bw.close();
   }
   
   public void shutdown(File dataDirectory) throws Exception {
      totalUpTime = stats.get(totalUpTime.getName());
      currentUpTime = stats.get(currentUpTime.getName());
      totalUpTime.pause();
      currentUpTime.pause();
      for(TimeStatistic ts : stats.values()) {
         if(ts.getCategory()==StatisticCategory.Player) {
            String stat = ts.getStatName();
            if(stat.equals("CurrentSession"))
               ts.reset();
            if(stat.equals("TotalPlayTime"))
               ts.pause();
         }
      }
      save(dataDirectory);
   }
   
   
   /**
    * @param timeStatistic
    */
   private void add(TimeStatistic timeStatistic) {
      String player = timeStatistic.getPlayer();
      if(player!=null && player.length()>0)
      {
         add(player);
      }
      stats.put(timeStatistic.getName(), timeStatistic);
   }
   
   private void add(String player) {
      if(players.contains(player))
         return;
      players.add(player);
      add(new TimeStatistic(StatisticCategory.Player,player,"TotalPlayTime"));
      add(new TimeStatistic(StatisticCategory.Player,player,"TimeSinceLogoff"));
      add(new TimeStatistic(StatisticCategory.Player,player,"CurrentSession"));
      add(new TimeStatistic(StatisticCategory.Player,player,"SinceLastDeath"));
      add(new TimeStatistic(StatisticCategory.Player,player,"Timer1"));
      add(new TimeStatistic(StatisticCategory.Player,player,"Timer2"));
   }

}
