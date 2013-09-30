package me.ThaH3lper.com.Clock;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.SaveLoad.LoadSetup;
import me.ThaH3lper.com.SaveLoad.Load.LoadMobList;
import me.ThaH3lper.com.Timer.Timer;

public class Clock implements Runnable{

	int save = 0, timer = 0, walk = 0;
	
	@Override
	public void run() {
		save++;
		timer++;
		walk++;
		if(save >= EpicBoss.plugin.loadSetup.Inteval)
		{
			executeSave();
			save = 0;
		}
		if(timer >= EpicBoss.plugin.loadSetup.timerupdate)
		{
			boolean spawned = false;
			for(Timer t : EpicBoss.plugin.allTimers)
				spawned = t.tick(EpicBoss.plugin.loadSetup.timerupdate);
			if(spawned)
				LoadSetup.Update();
			timer = 0;
		}
		if(walk>= EpicBoss.plugin.loadSetup.walkupdate)
		{
			for(Timer t : EpicBoss.plugin.allTimers)
				t.WalkCheck();
			walk = 0;
		}
	}
	
	public static void executeSave()
	{
		EpicBoss.plugin.loadSetup.SaveAll();
		EpicBoss.plugin.allMobs = LoadMobList.Refresh(EpicBoss.plugin.allMobs);
	}
}
