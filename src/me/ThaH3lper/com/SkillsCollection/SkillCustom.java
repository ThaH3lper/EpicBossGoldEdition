package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillCustom {
	
	// teleport!
		
	public static void ExecuteCustom(LivingEntity l, String skill, Player player)
	{
		String[] base = skill.split(" ");

		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{	
				BossSkillEvent event = new BossSkillEvent(l, skill, player, true);
				Bukkit.getServer().getPluginManager().callEvent(event);
			}
		}
		
	}
}
