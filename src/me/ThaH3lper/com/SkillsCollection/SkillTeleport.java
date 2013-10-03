package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillTeleport {
	
	// teleport!
		
	public static void ExecuteTeleport(LivingEntity l, String skill, Player player)
	{
		String[] base = skill.split(" ");

		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{	
				l.teleport(player.getLocation());
			}
		}
		
	}
}
