package me.ThaH3lper.com.SkillsCollection;

import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class SkillConsume {
	
	//- consume radius_horizontal:radius_vertical:damage:heal mobtype,mobtype,mobtype
	
	@SuppressWarnings("deprecation")
	public static void ExecuteConsume(LivingEntity l, String skill)
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
				
				int radius_xz = Integer.parseInt(data[0]);
				int radius_y = Integer.parseInt(data[1]);
				int damage = Integer.parseInt(data[2]);
				int heal = Integer.parseInt(data[3]);
				
				String[] mobtypes = base[2].split(",");
				
				List<Entity> moblist = l.getNearbyEntities(radius_xz, radius_y, radius_xz);
					
				for(Entity e : moblist)	{
					if(e instanceof LivingEntity)	{
						for(String mob : mobtypes)	{
							if(e.getType() == EntityType.fromName(mob))	{
								ConsumeMob(l, (LivingEntity)e, damage, heal);
							} else	{
								if(EpicBoss.plugin.allMobs.contains(e.getUniqueId()))	{
									EpicMobs em = MobCommon.getEpicMob((LivingEntity)e);
									
									if(mob.equals(em.cmdName))	{
										ConsumeMob(l, (LivingEntity)e, damage, heal);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	private static void ConsumeMob(LivingEntity consumer, LivingEntity consumed, int damage, int heal)	{
		consumed.damage(damage);
		
		if((consumer.getHealth() + heal) >= consumer.getMaxHealth())	{
			consumer.setHealth(consumer.getMaxHealth());
		} else	{
			consumer.setHealth(consumer.getHealth() + heal);
		}
	}
}
