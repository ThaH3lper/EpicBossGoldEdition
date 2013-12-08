package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R1.CraftWorld;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.util.Vector;

public class SkillShootSkull {
	
	// Shoots a wither skull
	// shootskull power
	
	public static void ExecuteShoot(LivingEntity l, String skill, Player player)
	{
		if(player == null) return;
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
				
				float power = Float.parseFloat(data[0]);
	
				WitherSkull fireball;
				
				if((l instanceof Creature) && ((Creature)l).getTarget() == player)	{
					fireball = l.launchProjectile(WitherSkull.class);
				} else	{
					Vector facing = player.getLocation().toVector().subtract(l.getLocation().toVector()).normalize();
					
					Location loc = l.getLocation().clone();
	                
	                double yaw = Math.toDegrees(Math.atan2(-facing.getX(), facing.getZ()));
	                double pitch = Math.toDegrees(-Math.asin(facing.getY()));                               
	                loc.setYaw((float)yaw);
	                loc.setPitch((float)pitch);
	                
	                loc.add(facing.multiply(2));
	                
	                fireball = l.getLocation().getWorld().spawn(loc, WitherSkull.class);
				}
				
				((CraftWorld) l.getLocation().getWorld()).getHandle().makeSound(l.getLocation().getX(), l.getLocation().getY(), l.getLocation().getZ(), "mob.wither.shoot", 1, 1);
				
				fireball.setBounce(false);
				fireball.setYield(power);
				fireball.setShooter(l);
			}
		}
		
	}
}
