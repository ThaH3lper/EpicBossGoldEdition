package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;

public class SkillShootSkull {
	
	// Shoots a wither skull
	// shootskull power
	
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
	
				WitherSkull fireball;
				fireball = l.launchProjectile(WitherSkull.class);
				((CraftWorld) l.getLocation().getWorld()).getHandle().makeSound(l.getLocation().getX(), l.getLocation().getY(), l.getLocation().getZ(), "mob.wither.shoot", 1, 1);
				
				fireball.setBounce(false);
				fireball.setYield(power);
				fireball.setShooter(l);
			}
		}
		
	}
}
