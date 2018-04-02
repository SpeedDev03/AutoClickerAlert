package speeddev.info.cps;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class Timer {
	  private Main plugin;
	  int timerId;
	  
	  public Timer(Main plugin)
	  {
	    this.plugin = plugin;
	  }
	  
	  public void startTimer()
	  {
	    this.timerId = this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable()
	    {
	      public void run()
	      {
	        for (Player player : )
	        {
	          if (!player.hasPermission(Timer.this.plugin.getPermissions().exempt)) {
	            if (Timer.this.plugin.getDetector().getClicks(player).intValue() >= Timer.this.plugin.config.cpsAlert)
	            {
	              Iterator<String> localIterator2 = Timer.this.plugin.getAccounts().getConfig().getConfigurationSection("accounts").getKeys(false).iterator();
	              while (localIterator2.hasNext())
	              {
	                String uuidString = (String)localIterator2.next();
	                
	                UUID staffUuid = UUID.fromString(uuidString);
	                OfflinePlayer offlineStaff = Bukkit.getOfflinePlayer(staffUuid);
	                if (offlineStaff.isOnline())
	                {
	                  Player staff = (Player)offlineStaff;
	                  if (Timer.this.plugin.getAccounts().getAlerts(staff)) {
	                    staff.sendMessage("§9[AutoClickerAlert] " + 
	                      ChatColor.GOLD + player.getName() + " is clicking at " + ChatColor.YELLOW + 
	                      Timer.this.plugin.getDetector().getClicks(player).toString() + " clicks per second!");
	                  }
	                }
	              }
	            }
	          }
	          Timer.this.plugin.getDetector().resetClicks(player);
	        }
	      }
	    }, 0L, 20L);
	  }
	}
