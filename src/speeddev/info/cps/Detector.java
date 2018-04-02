package speeddev.info.cps;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Detector implements Listener
{
	  public Main instance;
	  
	  public Detector(Main instance)
	  {
	    this.instance = instance;
	  }
	  
	  private Map<UUID, Integer> clicks = new HashMap<UUID, Integer>();
	  
	  @EventHandler
	  public void onPlayerInteract(PlayerInteractEvent event)
	  {
	    Player player = event.getPlayer();
	    UUID playerUuid = player.getUniqueId();
	    if ((event.getAction() == Action.LEFT_CLICK_AIR) || (event.getAction() == Action.LEFT_CLICK_BLOCK)) {
	      this.clicks.put(playerUuid, Integer.valueOf(((Integer)this.clicks.get(playerUuid)).intValue() + 1));
	    }
	  }
	  
	  @EventHandler
	  public void onPlayerJoin(PlayerJoinEvent event)
	  {
	    Player player = event.getPlayer();
	    resetClicks(player);
	  }
	  
	  @EventHandler
	  public void onPlayerQuit(PlayerQuitEvent event)
	  {
	    Player player = event.getPlayer();
	    resetClicks(player);
	  }
	  
	  public Integer getClicks(Player player)
	  {
	    return (Integer)this.clicks.get(player.getUniqueId());
	  }
	  
	  public void resetClicks(Player player)
	  {
	    this.clicks.put(player.getUniqueId(), Integer.valueOf(0));
	  }
	}
