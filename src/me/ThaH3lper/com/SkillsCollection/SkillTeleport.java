package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillTeleport {
	
	// teleport [maxdistance] >0 1
		
	public static void ExecuteTeleport(LivingEntity l, String skill, Player player)
	{
		if(player == null) return;
		String[] base = skill.split(" ");

		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{	
				if(base.length > 3)	{
					int max_distance = Integer.parseInt(base[2]);
					
					if(l.getLocation().distanceSquared(player.getLocation()) > max_distance*max_distance)	{
						return;
					}
				}
				
				BossSkillEvent event = new BossSkillEvent(l, skill, player, false);
				Bukkit.getServer().getPluginManager().callEvent(event);
				if(event.isChanceled())
					return;
				
				l.teleport(player.getLocation());
			}
		}
		
	}
}
