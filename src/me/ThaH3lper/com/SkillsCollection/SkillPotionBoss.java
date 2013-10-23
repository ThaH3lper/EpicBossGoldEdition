package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SkillPotionBoss {
	
	//- potionboss Type:Duration:lvl =HP Chance
	
	public static void ExecutePotionBoss(LivingEntity l, String skill, Player player)
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
				
				String pType = data[0];
				float pDuration = Float.parseFloat(data[1]);
				int pLevel = Integer.parseInt(data[2]) - 1;
				
				PotionEffect pe = new PotionEffect(PotionEffectType.getByName(pType), (int) (pDuration * 20), pLevel);
				l.addPotionEffect(pe);
			}
		}
		
	}
}
