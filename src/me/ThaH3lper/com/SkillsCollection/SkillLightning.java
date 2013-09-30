package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class SkillLightning {
	//- lightning radius:damage =HP Chance
	
	public static void ExecuteLightning(LivingEntity l, String skill, Player player)
	{
		String[] base = skill.split(" ");
		String[] data = base[1].split(":");
		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{
				int radius = Integer.parseInt(data[0]);
				int damage = Integer.parseInt(data[1]);

				if(radius > 0)
				{
					for(Player p : SkillHandler.getRadious(l, radius))
					{
						p.getLocation().getWorld().strikeLightningEffect(p.getLocation());
						p.damage(damage);
					}
				} else	{
					player.getLocation().getWorld().strikeLightningEffect(player.getLocation());
					player.damage(damage);
				}
				
			}
		}
		
	}
}
