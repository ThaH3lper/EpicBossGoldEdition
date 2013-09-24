package me.ThaH3lper.com.SaveLoad;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import me.ThaH3lper.com.EpicBoss;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


public class SaveLoad {
		
	private FileConfiguration DataConfig = null;
	private File data = null;
	
	private EpicBoss plugin;
	private String file;
	public File thefile;
	
	public SaveLoad(EpicBoss plugin, String newfile)
	{
		this(plugin, newfile, null);
	}
	public SaveLoad(EpicBoss plugin, String newfile, String folder)
	{
		this.plugin = plugin;
		file = newfile;
		
		if(folder != null)
			thefile = new File(plugin.getDataFolder() + System.getProperty("file.separator") + folder, newfile);
		else
			thefile = new File(plugin.getDataFolder(), newfile);
		
		if(thefile.exists())
			data = thefile;
		
		reloadCustomConfig();
		saveCustomConfig();
	}
	public void reloadCustomConfig() {
	    if (data == null) 
	    {
			data = new File(thefile.getParent(), file);
	    	DataConfig = YamlConfiguration.loadConfiguration(data);
	    	InputStream defConfigStream = plugin.getResource(file);
	    	if (defConfigStream != null) 
	    	{
	    		YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	    		DataConfig.setDefaults(defConfig);
	    	}
	    	getCustomConfig().options().copyDefaults(true);
	    	plugin.logger.info(file + " did not exist! Generated a new one!");
	    }
	    else
	    {
	    	DataConfig = YamlConfiguration.loadConfiguration(data);
	    }
	}

	public FileConfiguration getCustomConfig() {
	    if (DataConfig == null) {
	        reloadCustomConfig();
	    }
	    return DataConfig;
	}

	public void saveCustomConfig() {
	    if (DataConfig == null || data == null) {
	    return;
	    }
	    try {
	        getCustomConfig().save(data);
	    } catch (IOException ex) {
	    	plugin.getLogger().log(Level.SEVERE, "Could not save config to " + data, ex);
	    }
	    
	}
}