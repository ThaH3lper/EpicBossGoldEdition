package me.ThaH3lper.com.SaveLoad;

import java.io.EOFException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.v1_6_R3.org.bouncycastle.LICENSE;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Mobs.MobCommon;
import me.ThaH3lper.com.SaveLoad.Load.LoadItems;
import me.ThaH3lper.com.SaveLoad.Load.LoadLocation;
import me.ThaH3lper.com.SaveLoad.Load.LoadLoots;
import me.ThaH3lper.com.SaveLoad.Load.LoadMobList;
import me.ThaH3lper.com.SaveLoad.Load.LoadMobs;
import me.ThaH3lper.com.SaveLoad.Load.LoadSkills;
import me.ThaH3lper.com.SaveLoad.Load.LoadSpawning;
import me.ThaH3lper.com.SaveLoad.Load.LoadTimers;
import me.ThaH3lper.com.Timer.TimerHandler;

public class LoadSetup {

	public File[] MobFiles, ItemFiles, LootFiles, SkillFiles, TimerFiles, SpawningFiles;
	
	EpicBoss eb;
	public int Inteval = 5 * 60;
	public int timerupdate, walkupdate;
	public LoadSetup(EpicBoss eb)
	{
		this.eb = eb;
		LoadAll(true);
	}
	public void ResetAll()
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
		EpicBoss.plugin.listTempPlayer.clear();
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
	public void LoadAll(boolean msg)
	{
		EpicBoss.plugin.savelist = new SaveLoad(EpicBoss.plugin, "SaveList.yml", "Save");
		EpicBoss.plugin.mobs = new SaveLoad(EpicBoss.plugin, "MobsExample.yml", "Mobs");
		EpicBoss.plugin.items = new SaveLoad(EpicBoss.plugin, "ItemsExample.yml", "Items");
		EpicBoss.plugin.loots = new SaveLoad(EpicBoss.plugin, "LootExample.yml", "Loots");
		EpicBoss.plugin.skills = new SaveLoad(EpicBoss.plugin, "SkillExample.yml", "Skills");
		EpicBoss.plugin.timers = new SaveLoad(EpicBoss.plugin, "TimerExample.yml", "Timers");
		EpicBoss.plugin.spawning = new SaveLoad(EpicBoss.plugin, "SpawningExample.yml", "RandomSpawning");
		EpicBoss.plugin.settings = new SaveLoad(EpicBoss.plugin, "Settings.yml");
		
		MobFiles = new File(eb.mobs.thefile.getParent()).listFiles();
		ItemFiles = new File(eb.items.thefile.getParent()).listFiles();
		LootFiles = new File(eb.loots.thefile.getParent()).listFiles();
		SkillFiles = new File(eb.skills.thefile.getParent()).listFiles();
		TimerFiles = new File(eb.timers.thefile.getParent()).listFiles();
		SpawningFiles = new File(eb.spawning.thefile.getParent()).listFiles();
		
		eb.saveItemList = SaveLoadHandler.getSaveLoad(ItemFiles, "Items");
		eb.saveMobList = SaveLoadHandler.getSaveLoad(MobFiles, "Mobs");
		eb.saveLootList = SaveLoadHandler.getSaveLoad(LootFiles, "Loots");
		eb.saveSkillList = SaveLoadHandler.getSaveLoad(SkillFiles, "Skills");
		eb.saveTimerList = SaveLoadHandler.getSaveLoad(TimerFiles, "Timers");
		eb.saveSpawningList = SaveLoadHandler.getSaveLoad(SpawningFiles, "RandomSpawning");
		
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
	
	public void SaveAll()
	{
		Bukkit.savePlayers();
		for(World w : Bukkit.getWorlds())
			w.save();
		LoadMobList.SaveMobsList();
		LoadLocation.saveAllLocations();
		TimerHandler.SaveAllTimers();
	}
	
	public void loadSettings()
	{
		if(eb.settings.getCustomConfig().contains("SaveInterval"))
		{
			int i = eb.settings.getCustomConfig().getInt("SaveInterval");
			Inteval = i * 60;
			timerupdate = eb.settings.getCustomConfig().getInt("TimerUpdate");
			walkupdate = eb.settings.getCustomConfig().getInt("WalkUpdate");
		}
	}
	
	public static void Update()
	{
		LoadMobList.SaveMobsList();
		TimerHandler.SaveAllTimers();
		
		EpicBoss.plugin.allMobs.clear();
		EpicBoss.plugin.allTimers.clear();
		
		LoadMobList.LoadMobsList();
		TimerHandler.LoadAllTimers();
	}
	

		
}
