package me.ThaH3lper.com.Spawning;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Biome;

import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;

public class EpicSpawning {

	public String cmdName;
	public EpicMobs em;
	public int priority;
	public float chance;
	public List<Biome> biomes = new ArrayList<Biome>();
	public List<World> worlds = new ArrayList<World>();
	
	public EpicSpawning(String cmdName, String mobname, int priority, float chance, String world, String biome)
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
		if(biome != null)
		{
			String[] biomename = biome.split(",");
			for(String name : biomename)
			{
				Biome b = Biome.valueOf(name);
				if(b != null) biomes.add(b);
			}
		}
	}
}
