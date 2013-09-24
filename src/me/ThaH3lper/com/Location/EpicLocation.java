package me.ThaH3lper.com.Location;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

public class EpicLocation {

	public Location location;
	public String name;
	Chunk chunk;
	
	public EpicLocation(String str)
	{
		String[] part = str.split(",");
		
		World w = Bukkit.getWorld(part[0]);
		double x = Double.parseDouble(part[1]);
		double y = Double.parseDouble(part[2]);
		double z = Double.parseDouble(part[3]);
		
		String n = part[4];	
		Location l = new Location(w, x, y, z);
		
		this.location = l;
		this.name = n;
		this.chunk = l.getChunk();
	}
	
	public EpicLocation(Location location, String name)
	{
		this.location = location;
		this.name = name;
	}
	
	public String getString()
	{
		String s = location.getWorld().getName() + "," + (location.getBlockX() + 0.5) + ","
	+ location.getBlockY() + "," +(location.getBlockZ() + 0.5) + "," + name;
		return s;
	}
	
	public void LoadChunk()
	{
		if(chunk != null)
			chunk.load();
	}
}
