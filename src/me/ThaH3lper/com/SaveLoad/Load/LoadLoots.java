package me.ThaH3lper.com.SaveLoad.Load;

import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Drops.EpicNormal;
import me.ThaH3lper.com.SaveLoad.SaveLoad;

public class LoadLoots{

	public static void LoadAllLoot()
	{
		for(SaveLoad sl :EpicBoss.plugin.saveLootList)
		{
			for(String s: sl.getCustomConfig().getConfigurationSection("").getKeys(false))
			{
				if(sl.getCustomConfig().getStringList(s + ".Loot") != null)
				{
					String cmdName = s;
					String file = sl.thefile.getName();
					List<String> list = sl.getCustomConfig().getStringList(s + ".Loot");
					
					EpicBoss.plugin.listLoots.add(new EpicNormal(list, cmdName, file));
				}
			}
		}
	}
}
