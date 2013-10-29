package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SkillPotionMobs {
	
	// - potionmobs radius:type:duration:lvl mobtype,mobtype,mobtype <HP chance
	
	@SuppressWarnings("deprecation")
	public static void ExecutePotionMobs(LivingEntity l, String skill)
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
				String pType = data[1];
				float pDuration = Float.parseFloat(data[2]);
				int pLevel = Integer.parseInt(data[3]) - 1;
				String[] mobtypes = base[2].split(",");
				
				PotionEffect pe = new PotionEffect(PotionEffectType.getByName(pType), (int) (pDuration * 20), pLevel);
				
				if(pe != null)	{
					for(Entity e : l.getNearbyEntities(radius, radius, radius))
					{
						if(e instanceof LivingEntity)	{
							for(String mob : mobtypes)	{
								if(e.getType() == EntityType.fromName(mob))	{
									((LivingEntity)e).addPotionEffect(pe);
								} else	{
									if(EpicBoss.plugin.allMobs.contains(e.getUniqueId()))	{
										EpicMobs em = MobCommon.getEpicMob((LivingEntity)e);
										
										if(mob.equals(em.cmdName))	{
											((LivingEntity)e).addPotionEffect(pe);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
