package speeddev.info.cps;

public class Config {
	
	  private Main plugin;
	  public int cpsAlert;
	  
	  public Config(Main plugin)
	  {
	    this.plugin = plugin;
	  }
	  
	  public void init()
	  {
	    this.plugin.getConfig().options().copyDefaults(true);
	    this.plugin.saveConfig();
	    
	    this.cpsAlert = this.plugin.getConfig().getInt("cps-to-alert-at");
	  }
	}

