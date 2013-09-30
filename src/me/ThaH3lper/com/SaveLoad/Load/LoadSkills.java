package me.ThaH3lper.com.SaveLoad.Load;

import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.SaveLoad.SaveLoad;
import me.ThaH3lper.com.Skills.EpicSkill;

public class LoadSkills{

	public static void LoadAllSkills()
	{
		for(SaveLoad sl :EpicBoss.plugin.saveSkillList)
		{
			for(String s: sl.getCustomConfig().getConfigurationSection("").getKeys(false))
			{
				int cooldown = sl.getCustomConfig().getInt(s + ".Cooldown");
				
				if(sl.getCustomConfig().getStringList(s + ".Skills") != null)
				{
					String cmdName = s;
					String file = sl.thefile.getName();
					List<String> list = sl.getCustomConfig().getStringList(s + ".Skills");
					
					EpicBoss.plugin.listSkills.add(new EpicSkill(cmdName, file, list, cooldown));
				}
			}
		}
	}
}
