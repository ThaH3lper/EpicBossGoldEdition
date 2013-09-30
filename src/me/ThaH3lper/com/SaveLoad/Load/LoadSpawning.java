package me.ThaH3lper.com.SaveLoad.Load;

import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.SaveLoad.SaveLoad;
import me.ThaH3lper.com.Skills.EpicSkill;
import me.ThaH3lper.com.Spawning.EpicSpawning;

public class LoadSpawning{

	public static void LoadSpawnings()
	{
		for(SaveLoad sl :EpicBoss.plugin.saveSpawningList)
		{
			for(String s: sl.getCustomConfig().getConfigurationSection("").getKeys(false))
			{			
				if(sl.getCustomConfig().getStringList(s + ".Mobname") != null)
				{
					String cmdName = s;
					String mobName = sl.getCustomConfig().getString(s + ".Mobname");
					String worlds  = sl.getCustomConfig().getString(s + ".Worlds");
					float chance = (float)sl.getCustomConfig().getDouble(s + ".Chance");
					int priority = sl.getCustomConfig().getInt(s + ".Priority");
					
					EpicBoss.plugin.listSpawning.add(new EpicSpawning(cmdName, mobName, priority, chance, worlds));
				}
			}
		}
	}
}
