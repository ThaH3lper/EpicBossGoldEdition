package me.ThaH3lper.com.Mobs;

import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.LivingEntity;

public class EpicMobs {
	
	public String Mobtype, Display, file, cmdName, oso, horseStyle, horseType;
	public double health, damage, speed, knock, follow;
	public List<String> skills, loot, equipment;
	public HashMap<String,Long> cooldowns;
	public boolean despawn, showhp, fair = false;
	int size, color;
	
	public EpicMobs(String file, String cmdName, String Mobtype, String Display, List<String> loot, List<String> equipment, double health, double damage, double speed, double knock, double follow, 
			List<String> skills, boolean despawn, boolean showhp, int size, int color, String oso, String horseStyle, String horseType)
	{
		this.file = file;
		this.cmdName = cmdName;
		this.Mobtype = Mobtype;
		this.Display = Display;
		this.health = health;
		this.damage = damage;
		this.speed = speed;
		this.knock = knock;
		this.follow = follow;
		this.skills = skills;
		this.despawn = despawn;
		this.showhp = showhp;
		this.size = size;
		this.color = color;
		this.oso = oso;
		this.horseStyle = horseStyle;
		this.horseType = horseType;
		this.loot = loot;
		if(loot != null && loot.size() >= 1)
		{
			if(loot.get(0) != null)
			{
				if(loot.get(0).contains(":"))
					fair = true;
			}
		}
		this.equipment = equipment;
		this.cooldowns = new HashMap<String,Long>();
	}
	
	public LivingEntity getLivingenEntity()
	{
		return null;
	}
}
