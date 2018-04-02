package speeddev.info.cps;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Accounts   implements Listener
{
	  private Main plugin;
	  File file;
	  FileConfiguration fc;
	  
	  public Accounts(Main plugin)
	  {
	    this.plugin = plugin;
	  }
	  
	  public void init()
	  {
	    if (!this.plugin.getDataFolder().exists()) {
	      this.plugin.getDataFolder().mkdir();
	    }
	    this.file = new File(this.plugin.getDataFolder(), "accounts.yml");
	    if (!this.file.exists()) {
	      try
	      {
	        this.file.createNewFile();
	      }
	      catch (Exception exception)
	      {
	        exception.printStackTrace();
	      }
	    }
	    this.fc = YamlConfiguration.loadConfiguration(this.file);
	    if (getConfig().getConfigurationSection("accounts") == null) {
	      getConfig().createSection("accounts");
	    }
	    for (Player p : Bukkit.getOnlinePlayers())
	    {
	      if (!getConfig().getConfigurationSection("accounts").contains(p.getUniqueId().toString())) {
	        createAccount(p);
	      }
	      this.plugin.getDetector().resetClicks(p);
	    }
	  }
	  
	  public FileConfiguration getConfig()
	  {
	    return this.plugin.getAccounts().fc;
	  }
	  
	  public void createAccount(Player player)
	  {
	    getConfig().set("accounts." + player.getUniqueId().toString() + ".name", player.getName());
	    getConfig().set("accounts." + player.getUniqueId().toString() + ".autoclickeralerts", Boolean.valueOf(false));
	    try
	    {
	      getConfig().save(this.file);
	    }
	    catch (Exception exception)
	    {
	      exception.printStackTrace();
	    }
	  }
	  
	  public String getName(Player player)
	  {
	    return getConfig().getString("accounts." + player.getUniqueId().toString() + ".name");
	  }
	  
	  public boolean getAlerts(Player player)
	  {
	    return getConfig().getBoolean("accounts." + player.getUniqueId().toString() + ".autoclickeralerts");
	  }
	  
	  public void setAlerts(Player player, boolean alerts)
	  {
	    getConfig().set("accounts." + player.getUniqueId().toString() + ".autoclickeralerts", Boolean.valueOf(alerts));
	    try
	    {
	      getConfig().save(this.file);
	    }
	    catch (Exception exception)
	    {
	      exception.printStackTrace();
	    }
	  }
	  
	  @EventHandler
	  public void onPlayerJoin(PlayerJoinEvent event)
	  {
	    Player player = event.getPlayer();
	    if (getConfig().getConfigurationSection("accounts").getKeys(false).contains(player.getUniqueId().toString()))
	    {
	      if (player.getName() != getName(player))
	      {
	        getConfig().set("accounts." + player.getUniqueId().toString() + ".name", player.getName());
	        try
	        {
	          getConfig().save(this.file);
	        }
	        catch (Exception exception)
	        {
	          exception.printStackTrace();
	        }
	      }
	    }
	    else {
	      createAccount(player);
	    }
	  }
	}