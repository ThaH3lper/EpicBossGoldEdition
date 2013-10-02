package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Effect;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillShootFireball {
	
	// shootfireball power:incendiary(true/false):fire_ticks
	
	public static void ExecuteShoot(LivingEntity l, String skill, Player player)
	{
		String[] base = skill.split(" ");
		String[] data = base[1].split(":");
		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{
				float power = Float.parseFloat(data[0]);
				boolean incendiary = Boolean.parseBoolean(data[1]);
				int fireticks = Integer.parseInt(data[2]);
	
				Fireball fireball;
				fireball = l.launchProjectile(Fireball.class);
				l.getWorld().playEffect(l.getLocation(), Effect.GHAST_SHOOT, 0);
				
				fireball.setBounce(false);
				fireball.setIsIncendiary(incendiary);
				fireball.setFireTicks(fireticks);
				fireball.setYield(power);
				fireball.setShooter(l);
			}
		}
		
	}
}
