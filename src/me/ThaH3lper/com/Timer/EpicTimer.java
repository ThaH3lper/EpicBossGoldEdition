package me.ThaH3lper.com.Timer;

import java.util.List;

import me.ThaH3lper.com.Mobs.EpicMobs;

public class EpicTimer {
	public String cmdName, file;
	public List<EpicMobs> bosses;
	public int amount;
	public int interval;
	public int walk;
	
	public EpicTimer(List<EpicMobs> bosses, int amount, int interval, int walk, String cmdName, String file)
	{
		this.bosses = bosses;
		this.amount = amount;
		this.interval = interval;
		this.walk = walk;
		this.cmdName = cmdName;
		this.file = file;
	}
}
