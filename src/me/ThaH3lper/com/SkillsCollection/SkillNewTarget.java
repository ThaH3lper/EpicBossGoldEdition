package me.ThaH3lper.com.SkillsCollection;

import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillNewTarget {
	
	//- newtarget radius
	
	public static void ExecuteNewTarget(LivingEntity l, String skill)
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
				
				int radius = Integer.parseInt(data[0]);
								
				if(radius > 0)	{
					List<Player> players = SkillHandler.getRadious(l, radius);
					
					if(players.size() < 2) return;
					
					Player newtarget = players.get(EpicBoss.r.nextInt(players.size()));
					
					EpicMobs em = MobCommon.getEpicMob(l);
					
					((Creature)l).setTarget((LivingEntity)newtarget);
					em.targetChanger = newtarget;
				}
			}
		}
	}
}
