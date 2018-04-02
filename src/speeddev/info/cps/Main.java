package speeddev.info.cps;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main  extends JavaPlugin
{
	  private Detector detector;
	  private Permissions permissions;
	  public Accounts accounts;
	  public Config config;
	  private Commands commands;
	  private Timer timer;
	  
	  public void onEnable()
	  {
	    this.config = new Config(this);
	    this.config.init();
	    
	    this.detector = new Detector(this);
	    
	    this.accounts = new Accounts(this);
	    this.accounts.init();
	    
	    this.permissions = new Permissions();
	    this.commands = new Commands(this);
	    
	    this.timer = new Timer(this);
	    this.timer.startTimer();
	    
	    registerEvents();
	    registerCommands();
	  }
	  
	  public void registerEvents()
	  {
	    PluginManager pm = getServer().getPluginManager();
	    pm.registerEvents(this.detector, this);
	    pm.registerEvents(this.accounts, this);
	  }
	  
	  public void registerCommands()
	  {
	    getCommand("autoclickeralert").setExecutor(this.commands);
	  }
	  
	  public Detector getDetector()
	  {
	    return this.detector;
	  }
	  
	  public Permissions getPermissions()
	  {
	    return this.permissions;
	  }
	  
	  public Accounts getAccounts()
	  {
	    return this.accounts;
	  }
}