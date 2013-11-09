package me.ThaH3lper.com.SaveLoad.Load;

import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.EpicMobsList;
import me.ThaH3lper.com.SaveLoad.SaveLoad;

public class LoadMobs{

	public static void LoadAllMobs()
	{
		for(SaveLoad sl :EpicBoss.plugin.saveMobList)
		{
			for(String s: sl.getCustomConfig().getConfigurationSection("").getKeys(false))
			{
				if(sl.getCustomConfig().getString(s + ".Bosslist") != null)
				{
					String cmdName = s;
					String file = sl.thefile.getName();
					String list = sl.getCustomConfig().getString(s + ".Bosslist");
					
					EpicBoss.plugin.listMobslist.add(new EpicMobsList(file, cmdName, list));
				}
				else
				{
					String cmdName = s;
					String file = sl.thefile.getName();
					
					//Main Stuff
					String Mobtype = sl.getCustomConfig().getString(s + ".Mobtype");
					String Display = sl.getCustomConfig().getString(s + ".Display");
					double health = sl.getCustomConfig().getDouble(s + ".Health");
					double damage = sl.getCustomConfig().getDouble(s + ".Damage");
					boolean showhp = sl.getCustomConfig().getBoolean(s + ".ShowHealth");
					boolean despawn = sl.getCustomConfig().getBoolean(s + ".Despawn");
					
					//Tags
					double speed = sl.getCustomConfig().getDouble(s + ".Tags.Speed");
					double knock = sl.getCustomConfig().getDouble(s + ".Tags.KnockbackRes");
					double follow = sl.getCustomConfig().getDouble(s + ".Tags.Followrange");
					
					int size = sl.getCustomConfig().getInt(s + ".Tags.Size");
					int color = sl.getCustomConfig().getInt(s + ".Tags.Color");
					int maxCombatDistance = sl.getCustomConfig().getInt(s + ".Tags.MaxCombatDistance");
					String oso = sl.getCustomConfig().getString(s + ".Tags.Ocelot");
					String horseStyle = sl.getCustomConfig().getString(s + ".Tags.HorseStyle");
					String horseType = sl.getCustomConfig().getString(s + ".Tags.HorseType");
					String horseColor = sl.getCustomConfig().getString(s + ".Tags.HorseColor");
					String villagerType = sl.getCustomConfig().getString(s + ".Tags.VillagerType");
					
					//Skills, Loot
					List<String> skills = sl.getCustomConfig().getStringList(s + ".Skills");
					List<String> loot = sl.getCustomConfig().getStringList(s + ".Loot");
					List<String> equipment = sl.getCustomConfig().getStringList(s + ".Equipment");
					
					EpicBoss.plugin.listMobs.add(new EpicMobs(file, cmdName, Mobtype, Display, loot, equipment, health, damage, speed, knock, follow, skills, 
							despawn, showhp, size, color, oso, horseStyle, horseType, horseColor, villagerType, maxCombatDistance));
				}
			}
		}
	}
}
