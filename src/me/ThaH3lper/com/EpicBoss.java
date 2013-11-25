package me.ThaH3lper.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import me.ThaH3lper.com.Clock.Clock;
import me.ThaH3lper.com.Commands.CommandInput;
import me.ThaH3lper.com.Drops.EpicNormal;
import me.ThaH3lper.com.Drops.Fair.FairDrops;
import me.ThaH3lper.com.Items.EpicItems;
import me.ThaH3lper.com.Listener.LeashEvent;
import me.ThaH3lper.com.Listener.MobDamaged;
import me.ThaH3lper.com.Listener.MobDrop;
import me.ThaH3lper.com.Listener.MobHit;
import me.ThaH3lper.com.Listener.MobSkill;
import me.ThaH3lper.com.Listener.MobSpawn;
import me.ThaH3lper.com.Listener.SignPlace;
import me.ThaH3lper.com.Listener.SkillShootProjectileListener;
import me.ThaH3lper.com.Listener.SlimeSplit;
import me.ThaH3lper.com.Location.EpicLocation;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.EpicMobsList;
import me.ThaH3lper.com.SaveLoad.LoadSetup;
import me.ThaH3lper.com.SaveLoad.SaveLoad;
import me.ThaH3lper.com.Skills.EpicSkill;
import me.ThaH3lper.com.Spawning.EpicSpawning;
import me.ThaH3lper.com.Timer.EpicTimer;
import me.ThaH3lper.com.Timer.Timer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EpicBoss extends JavaPlugin{
	
	public Logger logger = Logger.getLogger("Minecraft");
	public static EpicBoss plugin;
	public String menu = ChatColor.GREEN + "oOo_-_-_-_-_oOo"  + ChatColor.GOLD + ChatColor.BOLD + " EpicBoss Gold Edition " + ChatColor.GREEN + "oOo_-_-_-_-_oO";
	public static Random r = new Random();
	
	public SaveLoad mobs, items, loots, skills, savelist, settings, timers, spawning;
	public List<SaveLoad> saveItemList;
	public List<SaveLoad> saveMobList;
	public List<SaveLoad> saveLootList;
	public List<SaveLoad> saveSkillList;
	public List<SaveLoad> saveTimerList;
	public List<SaveLoad> saveSpawningList;
	
	public List<UUID> allMobs = new ArrayList<UUID>();
	public List<Timer> allTimers = new ArrayList<Timer>();
	public List<FairDrops> listFair = new ArrayList<FairDrops>();
	public List<Item> fairItems = new ArrayList<Item>();
	
	public List<EpicNormal> listLoots = new ArrayList<EpicNormal>();
	public List<EpicItems> listItems = new ArrayList<EpicItems>();
	public List<EpicMobs> listMobs = new ArrayList<EpicMobs>();
	public List<EpicMobsList> listMobslist = new ArrayList<EpicMobsList>();
	public List<EpicLocation> listLoc = new ArrayList<EpicLocation>();
	public List<EpicSkill> listSkills = new ArrayList<EpicSkill>();
	public List<EpicTimer> listTimers = new ArrayList<EpicTimer>();
	public List<EpicSpawning> listSpawning = new ArrayList<EpicSpawning>();
	
	@Override
	public void onDisable() {
		LoadSetup.SaveAll();
		PluginDescriptionFile pdfFile = this.getDescription();
		logger.info(pdfFile.getName() +  " Has Been Disabled!");	

	}
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		logger.info(pdfFile.getName()+ " " + pdfFile.getVersion() +  " Has Been Enabled!");
		
		plugin = this;
		LoadSetup.LoadAll(true);
		getCommand("EpicBoss").setExecutor(new CommandInput());
		
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Clock(), 0L, 20L);
		
		PluginManager manager = this.getServer().getPluginManager();
		manager.registerEvents(new MobDrop(), this);
		manager.registerEvents(new MobHit(), this);
		manager.registerEvents(new SignPlace(), this);
		manager.registerEvents(new LeashEvent(), this);
		manager.registerEvents(new MobSkill(), this);
		manager.registerEvents(new SlimeSplit(), this);
		manager.registerEvents(new MobSpawn(), this);
		manager.registerEvents(new MobDamaged(), this);
		manager.registerEvents(new SkillShootProjectileListener(), this);
	}
	
	public List<LivingEntity> getMobsAll()
	{
		List<LivingEntity> list = new ArrayList<LivingEntity>();
		for(World w : Bukkit.getWorlds())
		{
			for(LivingEntity e : w.getLivingEntities())
			{
				if(allMobs.contains(e.getUniqueId()))
					list.add(e);
			}
		}
		return list;
	}
}
