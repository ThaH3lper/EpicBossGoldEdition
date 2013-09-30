package me.ThaH3lper.com.Spawning;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;

import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;

public class EpicSpawning {

	String cmdName;
	EpicMobs em;
	int priority;
	float chance;
	List<World> worlds = new ArrayList<World>();
	
	public EpicSpawning(String cmdName, String mobname, int priority, float chance, String world)
	{
		this.cmdName = cmdName;
		this.em = MobCommon.getEpicMob(mobname);
		this.priority = priority;
		this.chance = chance;
		String[] worldName = world.split(",");
		
		for(String name : worldName)
		{
			World w = Bukkit.getWorld(name);
			if(w != null) worlds.add(w);
		}
	}
}
