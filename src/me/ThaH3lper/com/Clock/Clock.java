package me.ThaH3lper.com.Clock;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.SaveLoad.LoadSetup;
import me.ThaH3lper.com.SaveLoad.Load.LoadMobList;
import me.ThaH3lper.com.Spawning.SpawningHandler;
import me.ThaH3lper.com.Timer.Timer;

public class Clock implements Runnable{

	int save = 0, timer = 0, walk = 0;
	
	@Override
	public void run() {
		save++;
		timer++;
		walk++;
		if(save >= LoadSetup.Inteval)
		{
			executeSave();
			save = 0;
		}
		if(timer >= LoadSetup.timerupdate)
		{
			for(Timer t : EpicBoss.plugin.allTimers)
				t.tick(LoadSetup.timerupdate);
			
			//LoadSetup.Update();
			timer = 0;
		}
		if(walk>= LoadSetup.walkupdate)
		{
			for(Timer t : EpicBoss.plugin.allTimers)
				t.WalkCheck();
			walk = 0;
		}
		SpawningHandler.updateSpawning();
	}
	
	public static void executeSave()
	{
		LoadSetup.SaveAll();
		EpicBoss.plugin.allMobs = LoadMobList.Refresh(EpicBoss.plugin.getMobsAll());
	}
}
