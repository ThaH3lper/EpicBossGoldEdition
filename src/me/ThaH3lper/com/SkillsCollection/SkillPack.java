package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Libs.FireWorkEffect;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;
import me.ThaH3lper.com.Skills.EpicSkill;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillPack {
	
	//- pack name hp= chance 
	static FireWorkEffect effect = new FireWorkEffect();
	
	public static void ExecutePack(LivingEntity l, String skill, Player p)
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
				
				EpicSkill es = getPack(base[1]);
				
				if(es != null)
				{
					if(onCooldown(l, base[1])) return;
	
					SkillHandler.ExecutePackSkills(es.skills, l, p);
					setCooldown(l, base[1], es.cooldown);
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











