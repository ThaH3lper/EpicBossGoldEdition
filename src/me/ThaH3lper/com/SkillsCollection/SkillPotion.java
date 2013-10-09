package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SkillPotion {
	
	// - potion radius:type:duraction:lvl <HP chance(float)
	
	public static void ExecutePotion(LivingEntity l, String skill, Player player)
	{
		String[] base = skill.split(" ");
		String[] data = base[1].split(":");
		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{
				int radius = Integer.parseInt(data[0]);
				String pType = data[1];
				float pDuration = Float.parseFloat(data[2]);
				int pLevel = Integer.parseInt(data[3]) - 1;

				PotionEffect pe = new PotionEffect(PotionEffectType.getByName(pType), (int) (pDuration * 20), pLevel);
				if(pe != null)	{
					if(radius > 0)
					{
						for(Player p : SkillHandler.getRadious(l, radius))
						{
							p.addPotionEffect(pe);
						}
					} else	{
						if(player == null) return;
						player.addPotionEffect(pe);
					}
				}
			}
		}
	}
}
