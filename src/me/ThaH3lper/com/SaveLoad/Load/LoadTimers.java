package me.ThaH3lper.com.SaveLoad.Load;

import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.SaveLoad.SaveLoad;
import me.ThaH3lper.com.Timer.EpicTimer;
import me.ThaH3lper.com.Timer.TimerHandler;

public class LoadTimers{

	public static void LoadAllTimers()
	{
		for(SaveLoad sl :EpicBoss.plugin.saveTimerList)
		{
			for(String s: sl.getCustomConfig().getConfigurationSection("").getKeys(false))
			{
				if(sl.getCustomConfig().getString(s + ".Bosses") != null)
				{
					String cmdName = s;
					String file = sl.thefile.getName();

					String string = sl.getCustomConfig().getString(s + ".Bosses");
					List<EpicMobs> bosses = TimerHandler.getMobs(string);
					int amount = sl.getCustomConfig().getInt(s + ".MaxAmount");
					int interval = sl.getCustomConfig().getInt(s + ".RespawnTime");
					int walk = sl.getCustomConfig().getInt(s + ".WalkDistance");
					
					EpicBoss.plugin.listTimers.add(new EpicTimer(bosses, amount, interval, walk, cmdName, file));
				}
			}
		}
	}
}
