package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillHeal {
	
	//- msg amount =HP Chance
	// amount double
	
	public static void ExecuteHeal(LivingEntity l, String skill, Player player)
	{
		String[] base = skill.split(" ");
		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{
				double amount = Double.parseDouble(base[1]);
				double health = l.getHealth() + amount;
				if(health >= l.getMaxHealth())
					l.setHealth(l.getMaxHealth());
				else
					l.setHealth(health);
				
			}
		}
		
	}
}
