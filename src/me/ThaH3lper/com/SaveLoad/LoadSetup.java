package me.ThaH3lper.com.SaveLoad;

import java.io.File;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.SaveLoad.Load.LoadItems;
import me.ThaH3lper.com.SaveLoad.Load.LoadLocation;
import me.ThaH3lper.com.SaveLoad.Load.LoadLoots;
import me.ThaH3lper.com.SaveLoad.Load.LoadMobList;
import me.ThaH3lper.com.SaveLoad.Load.LoadMobs;
import me.ThaH3lper.com.SaveLoad.Load.LoadSkills;
import me.ThaH3lper.com.SaveLoad.Load.LoadSpawning;
import me.ThaH3lper.com.SaveLoad.Load.LoadTimers;
import me.ThaH3lper.com.Timer.TimerHandler;

import org.bukkit.ChatColor;

public class LoadSetup {

	public static File[] MobFiles, ItemFiles, LootFiles, SkillFiles, TimerFiles, SpawningFiles;
	
	public static int Inteval = 5 * 60;
	public static int timerupdate, walkupdate, ShowHealthRadius;
	public static boolean lootCompatibility = false;
	public static String ShowHealth;
	public static void ResetAll()
	{
		EpicBoss.plugin.saveItemList.clear();
		EpicBoss.plugin.saveMobList.clear();
		EpicBoss.plugin.saveLootList.clear();
		EpicBoss.plugin.saveSkillList.clear();
		EpicBoss.plugin.saveTimerList.clear();
		EpicBoss.plugin.saveSpawningList.clear();
		
		EpicBoss.plugin.allMobs.clear();
		EpicBoss.plugin.allTimers.clear();
		EpicBoss.plugin.listFair.clear();
		EpicBoss.plugin.fairItems.clear();
		
		EpicBoss.plugin.listLoots.clear();
		EpicBoss.plugin.listItems.clear();
		EpicBoss.plugin.listMobslist.clear();
		EpicBoss.plugin.listMobs.clear();
		EpicBoss.plugin.listLoc.clear();
		EpicBoss.plugin.listSkills.clear();
		EpicBoss.plugin.listTimers.clear();
		EpicBoss.plugin.listSpawning.clear();
	}
	public static void LoadAll(boolean msg)
	{
		EpicBoss.plugin.savelist = new SaveLoad(EpicBoss.plugin, "SaveList.yml", "Save");
		EpicBoss.plugin.mobs = new SaveLoad(EpicBoss.plugin, "MobsExample.yml", "Mobs");
		EpicBoss.plugin.items = new SaveLoad(EpicBoss.plugin, "ItemsExample.yml", "Items");
		EpicBoss.plugin.loots = new SaveLoad(EpicBoss.plugin, "LootExample.yml", "Loots");
		EpicBoss.plugin.skills = new SaveLoad(EpicBoss.plugin, "SkillExample.yml", "Skills");
		EpicBoss.plugin.timers = new SaveLoad(EpicBoss.plugin, "TimerExample.yml", "Timers");
		EpicBoss.plugin.spawning = new SaveLoad(EpicBoss.plugin, "SpawningExample.yml", "RandomSpawning");
		EpicBoss.plugin.settings = new SaveLoad(EpicBoss.plugin, "Settings.yml");
		
		MobFiles = new File(EpicBoss.plugin.mobs.thefile.getParent()).listFiles();
		ItemFiles = new File(EpicBoss.plugin.items.thefile.getParent()).listFiles();
		LootFiles = new File(EpicBoss.plugin.loots.thefile.getParent()).listFiles();
		SkillFiles = new File(EpicBoss.plugin.skills.thefile.getParent()).listFiles();
		TimerFiles = new File(EpicBoss.plugin.timers.thefile.getParent()).listFiles();
		SpawningFiles = new File(EpicBoss.plugin.spawning.thefile.getParent()).listFiles();
		
		EpicBoss.plugin.saveItemList = SaveLoadHandler.getSaveLoad(ItemFiles, "Items");
		EpicBoss.plugin.saveMobList = SaveLoadHandler.getSaveLoad(MobFiles, "Mobs");
		EpicBoss.plugin.saveLootList = SaveLoadHandler.getSaveLoad(LootFiles, "Loots");
		EpicBoss.plugin.saveSkillList = SaveLoadHandler.getSaveLoad(SkillFiles, "Skills");
		EpicBoss.plugin.saveTimerList = SaveLoadHandler.getSaveLoad(TimerFiles, "Timers");
		EpicBoss.plugin.saveSpawningList = SaveLoadHandler.getSaveLoad(SpawningFiles, "RandomSpawning");
		
		LoadItems.LoadAllItems();
		LoadMobs.LoadAllMobs();
		LoadMobList.LoadMobsList();
		LoadLocation.loadAllLocations();
		LoadLoots.LoadAllLoot();
		LoadSkills.LoadAllSkills();
		LoadTimers.LoadAllTimers();
		LoadSpawning.LoadSpawnings();
		TimerHandler.LoadAllTimers();
		loadSettings();
		
		if(msg)
		{
			EpicBoss.plugin.logger.info("----------<| \u001B[32mEpicBoss \u001B[33mGold \u001B[32mEdition \u001B[0m|>----------");
			EpicBoss.plugin.logger.info(SaveLoadHandler.getList("\u001B[31mMobFiles:\u001B[0m ", MobFiles));
			EpicBoss.plugin.logger.info(SaveLoadHandler.getList("\u001B[31mItemFiles:\u001B[0m ", ItemFiles));
			EpicBoss.plugin.logger.info(SaveLoadHandler.getList("\u001B[31mLootFiles:\u001B[0m ", LootFiles));
			EpicBoss.plugin.logger.info(SaveLoadHandler.getList("\u001B[31mSkillFiles:\u001B[0m ", SkillFiles));
			EpicBoss.plugin.logger.info(SaveLoadHandler.getList("\u001B[31mTimerFiles:\u001B[0m ", TimerFiles));
			EpicBoss.plugin.logger.info(SaveLoadHandler.getList("\u001B[31mSpawningFiles:\u001B[0m ", SpawningFiles));
			EpicBoss.plugin.logger.info("-----------------------------------------------");
		}
	}
	
	public static void SaveAll()
	{
		/*Bukkit.savePlayers();
		for(World w : Bukkit.getWorlds())
			w.save();*/
		LoadMobList.SaveMobsList();
		LoadLocation.saveAllLocations();
		TimerHandler.SaveAllTimers();
	}
	
	public static void loadSettings()
	{
		if(EpicBoss.plugin.settings.getCustomConfig().contains("SaveInterval"))
		{
			int i = EpicBoss.plugin.settings.getCustomConfig().getInt("SaveInterval");
			Inteval = i * 60;
			timerupdate = EpicBoss.plugin.settings.getCustomConfig().getInt("TimerUpdate");
			walkupdate = EpicBoss.plugin.settings.getCustomConfig().getInt("WalkUpdate");		
			ShowHealthRadius = EpicBoss.plugin.settings.getCustomConfig().getInt("ShowHealthRadius");
			ShowHealth = EpicBoss.plugin.settings.getCustomConfig().getString("ShowHealth");
			ShowHealth = ChatColor.translateAlternateColorCodes('&', ShowHealth);
			lootCompatibility = EpicBoss.plugin.settings.getCustomConfig().getBoolean("LootCompatibility");
		}
	}
	
	/*public static void Update()
	{
		LoadMobList.SaveMobsList();
		TimerHandler.SaveAllTimers();
		
		EpicBoss.plugin.allMobs.clear();
		EpicBoss.plugin.allTimers.clear();
		
		LoadMobList.LoadMobsList();
		TimerHandler.LoadAllTimers();
	}*/
	

		
}
