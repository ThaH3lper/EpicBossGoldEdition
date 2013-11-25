package me.ThaH3lper.com.SkillsCollection;

import java.lang.reflect.Array;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;
import me.ThaH3lper.com.Skills.EpicSkill;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillRandomPack {
	
	//- randompack name,name,name hp= chance
	// Executes a random pack skill from the list that is not on cooldown
	public static void ExecuteRandomPack(LivingEntity l, String skill, Player p)
	{
		String[] base = skill.split(" ");
		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{
				BossSkillEvent event = new BossSkillEvent(l, skill, null, false);
				Bukkit.getServer().getPluginManager().callEvent(event);
				if(event.isChanceled())
					return;
				
				String[] packs = base[1].split(",");
				
				int num = Array.getLength(packs);
				
				String pack = packs[EpicBoss.r.nextInt(num)];
				
				EpicSkill es = getPack(pack);
				
				if(es != null)
				{
					if(onCooldown(l, pack) == true)	{
						String packsNew = "randompack ";
						
						for(String pN : packs)	{
							if(pN != pack)	{
								packsNew = packsNew + pN + ",";
							}
						}
						packsNew = packsNew + " >0 1";
						ExecuteRandomPack(l, packsNew, p);
					} else	{
						SkillHandler.ExecutePackSkills(es.skills, l, p);
						setCooldown(l, pack, es.cooldown);
					}
				}
			}
		}
		
	}
	public static EpicSkill getPack(String s)
	{
		for(EpicSkill es : EpicBoss.plugin.listSkills)
		{
			if(es.cmdName.equals(s))
				return es;
		}
		return null;
	}
	public static boolean onCooldown(LivingEntity l, String skill)	{
		EpicMobs em = MobCommon.getEpicMob(l);
		if(em == null) return true;
		
		Long next = em.cooldowns.get(skill + l.getEntityId());
		if(next != null)	{
			if (next > System.currentTimeMillis()) {
                return true;
			}
		}
		
		return false;
	}
	public static void setCooldown(LivingEntity l, String skill, int cooldown)	{
		EpicMobs em = MobCommon.getEpicMob(l);
		if(em == null) return;
		
		if(cooldown > 0)	{
			em.cooldowns.put(skill + l.getEntityId(), System.currentTimeMillis() + (int)(cooldown * 1000));
		} else	{
			em.cooldowns.remove(skill + l.getEntityId());
		}
	}
}











