package me.ThaH3lper.com.SaveLoad.Load;

import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.SaveLoad.SaveLoad;
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
					String biomes = sl.getCustomConfig().getString(s + ".Biomes");
					float chance = (float)sl.getCustomConfig().getDouble(s + ".Chance");
					int priority = sl.getCustomConfig().getInt(s + ".Priority");
					
					EpicBoss.plugin.listSpawning.add(new EpicSpawning(cmdName, mobName, priority, chance, worlds, biomes));
				}
			}
		}
		EpicBoss.plugin.listSpawning = sort(EpicBoss.plugin.listSpawning);
	}
	
	//Lowest first, Highet last
	public static List<EpicSpawning> sort(List<EpicSpawning> list)
	{
		for(int i = 0; i<list.size(); i++)
		{
			for(int q = 1; q<list.size()-i; q++)
			{
				EpicSpawning o = list.get(q-1);
				EpicSpawning n = list.get(q);
				if(o.priority > n.priority)
				{
					list.set(q-1, n);
					list.set(q, o);
				}
			}
		}
		return list;
	}
}
