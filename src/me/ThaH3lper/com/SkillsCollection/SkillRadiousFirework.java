package me.ThaH3lper.com.SkillsCollection;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Libs.FireWorkEffect;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SkillRadiousFirework {
	
	//- bossfirework amount:radious:ball:red:true:true =HP Chance
	//Type: BALL, BALL_LARGE, BURST, CREEPER, STAR
	static FireWorkEffect effect = new FireWorkEffect();

	
	public static void ExecuteRadiousFirework(LivingEntity l, String skill)
	{
		String[] base = skill.split(" ");
		String[] data = base[1].split(":");
		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{
				BossSkillEvent event = new BossSkillEvent(l, skill, null, false);
				Bukkit.getServer().getPluginManager().callEvent(event);
				if(event.isChanceled())
					return;
				
				int amount = Integer.parseInt(data[0]);
				int radious = Integer.parseInt(data[1]);
				boolean flicker = Boolean.valueOf(data[4]);
				boolean trail = Boolean.valueOf(data[5]);
				String[] c = data[3].split(",");
				Color color = Color.fromRGB(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]));
				Type type = Type.valueOf(data[2]);
				
				if(color != null && type != null)
				{
					for(Location loc : getLocations(amount, radious, l))
					try {
						effect.playFirework(loc.getWorld(), loc, FireworkEffect.builder().with(type).withColor(color).trail(trail).flicker(flicker).build());
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
			}
		}
		
	}
	public static List<Location> getLocations(int amount, int radious, LivingEntity entity)
	{
		List<Location> list = new ArrayList<Location>();
		double Sangle = 360/amount;
		for(int i = 0; i<amount; i++)
		{
			double x = Math.cos(Sangle * i);
			double z = Math.sin(Sangle * i);
			Location l = new Location(entity.getWorld(), entity.getLocation().getX() + (x * radious), entity.getLocation().getY(), entity.getLocation().getZ() + (z * radious));
			list.add(l);
		}
		return list;
	}
}
