package speeddev.info.cps;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands   implements CommandExecutor
{
	
	String prefix = "§9[AutoClickerAlert] ";
	
	  private Main plugin;
	  
	  public Commands(Main plugin)
	  {
	    this.plugin = plugin;
	  }
	  
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
	    if (cmd.getName().equalsIgnoreCase("autoclickeralert"))
	    {
	      if ((sender instanceof Player))
	      {
	        Player player = (Player)sender;
	        if (player.hasPermission(this.plugin.getPermissions().main))
	        {
	          if (args.length >= 1)
	          {
	            if (args[0].equalsIgnoreCase("setlimit"))
	            {
	              if (player.hasPermission(this.plugin.getPermissions().setlimit))
	              {
	                int limit = 0;
	                boolean setNumber = true;
	                try
	                {
	                  limit = Integer.parseInt(args[1]);
	                }
	                catch (Exception exception)
	                {
	                  setNumber = false;
	                  player.sendMessage(prefix + ChatColor.RED + "That's not a number!");
	                }
	                if (setNumber)
	                {
	                  this.plugin.getConfig().set("cps-to-alert-at", Integer.valueOf(limit));
	                  this.plugin.config.cpsAlert = limit;
	                  this.plugin.saveConfig();
	                  player.sendMessage(prefix + ChatColor.AQUA + "CPS alerts set to " + Integer.toString(limit) + ".");
	                }
	              }
	              else
	              {
	                player.sendMessage(prefix + ChatColor.RED + "You do not have permission to execute this command.");
	              }
	            } 
	            else if (args[0].equalsIgnoreCase("alertson"))
	            {
	              if (player.hasPermission(this.plugin.getPermissions().alertson))
	              {
	                this.plugin.getAccounts().setAlerts(player, true);
	                player.sendMessage(prefix + ChatColor.AQUA + "You turned on CPS alerts.");
	              }
	              else
	              {
	                player.sendMessage(prefix + ChatColor.RED + "You do not have permission to execute this command.");
	              }
	            }
	            else if (args[0].equalsIgnoreCase("alertsoff"))
	            {
	              if (player.hasPermission(this.plugin.getPermissions().alertson))
	              {
	                this.plugin.getAccounts().setAlerts(player, false);
	                player.sendMessage(prefix + ChatColor.AQUA + "You turned off CPS alerts.");
	              }
	              else
	              {
	                player.sendMessage(prefix + ChatColor.RED + "You do not have permission to execute this command.");
	              }
	            }
	            else {
	              player.sendMessage(prefix + ChatColor.RED + "Unknown command.");
	            }
	          }
	          else {
	            player.sendMessage(prefix + ChatColor.RED + "Improper arguments.");
	          }
	        }
	        else {
	          player.sendMessage(prefix + ChatColor.RED + "You do not have permission to execute this command.");
	        }
	      }
	      else
	      {
	        System.out.println(prefix + ChatColor.RED + "This command can only be executed in game.");
	      }
	      return true;
	    }
	    return false;
	  }
	}
