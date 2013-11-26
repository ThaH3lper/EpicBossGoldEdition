package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Mobs.AllMobs;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;
import me.ThaH3lper.com.Mobs.MobHandler;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillSwarm {
	
	//- swarm type:amount:radious =HP chance
	// $boss
	
	public static void ExecuteSwarm(LivingEntity l, String skill, Player player)
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
				
				int amount = Integer.parseInt(data[1]);
				int radious = Integer.parseInt(data[2]);
				if(data[0].contains("$"))
				{
					String s = data[0].replace("$", "");
					EpicMobs em = MobCommon.getEpicMob(s);
					if(em != null)
					{
						for(int i = 1; i <= amount; i++)
						{
							double x = (l.getLocation().getX() - radious) + (EpicBoss.r.nextInt(radious * 2));
							double z = (l.getLocation().getZ() - radious) + (EpicBoss.r.nextInt(radious * 2));
							Location loc = new Location(l.getWorld(), x, l.getLocation().getY(), z);
							MobHandler.SpawnMob(s, loc);
						}
					}
				}
				else
				{
					for(int i = 0; i <= amount; i++)
					{
						double x = (l.getLocation().getX() - radious) + (EpicBoss.r.nextInt(radious * 2));
						double z = (l.getLocation().getZ() - radious) + (EpicBoss.r.nextInt(radious * 2));
						Location loc = new Location(l.getWorld(), x, l.getLocation().getY(), z);
						AllMobs.spawnMob(data[0], loc);
					}
				}
			}

		}
		
	}
}
