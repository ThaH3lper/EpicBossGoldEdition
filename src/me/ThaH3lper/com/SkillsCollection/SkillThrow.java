package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.util.Vector;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillThrow {
	
	//- throw radius:horizontal_strength:vertical_strength =HP Chance
	
	public static void ExecuteThrow(LivingEntity l, String skill, Player player)
	{
		String[] base = skill.split(" ");
		String[] data = base[1].split(":");
		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{		
				BossSkillEvent event = new BossSkillEvent(l, skill, player, false);
				Bukkit.getServer().getPluginManager().callEvent(event);
				if(event.isChanceled())
					return;
				
				int radius = Integer.parseInt(data[0]);
				float strength = Float.parseFloat(data[1]) / 10;
				float strengthY = Float.parseFloat(data[2]) / 10;
				Vector V;
				
				if(radius > 0)
				{
					for(Player p : SkillHandler.getRadious(l, radius))
					{
						V = p.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(strength);
						
						if(strength == 0)	{
							V.setY(strengthY);
						} else	{
							V.setY(strengthY + V.getY());
						}

						p.setVelocity(V);
					}
				} else	{
					if(player == null) return;
					V = player.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(strength);
					
					if(strength == 0)	{
						V.setY(strengthY);
					} else	{
						V.setY(strengthY + V.getY());
					}
					
					player.setVelocity(V);
				}
				
			}
		}
		
	}
}
