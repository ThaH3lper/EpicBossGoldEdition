package me.ThaH3lper.com.SkillsCollection;

import java.lang.reflect.Array;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.craftbukkit.v1_7_R1.CraftWorld;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class SkillShootProjectile {
	
	// Shoots a projectile
	// projectile type:damage:velocity
	// type can be arrow, snowball, egg, or enderpearl
	
	private static Class<? extends Projectile> projectileClass;
			
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
				
				String projectileType = (Array.getLength(data) > 0) ? data[0] : "arrow";
				int damage = (Array.getLength(data) > 1) ? Integer.parseInt(data[1]) : 1;
				float velocity = (Array.getLength(data) > 2) ? Float.parseFloat(data[2]) : 1;
				int maxdistance = (Array.getLength(data) > 3) ? Integer.parseInt(data[3]) : 64;
	
				if(l.getLocation().distance(player.getLocation()) > maxdistance) return;
				
				Projectile projectile;
                if (projectileType.equalsIgnoreCase("arrow")) {
                	projectileClass = Arrow.class;
                    l.getWorld().playEffect(l.getLocation(), Effect.BOW_FIRE, 0);
	            } else if (projectileType.equalsIgnoreCase("snowball")) {
	            	projectileClass = Snowball.class;
	            	l.getWorld().playEffect(l.getLocation(), Effect.BOW_FIRE, 0);
	            } else if (projectileType.equalsIgnoreCase("egg")) {
	            	projectileClass = Egg.class;
	            	l.getWorld().playEffect(l.getLocation(), Effect.BOW_FIRE, 0);
	            } else if (projectileType.equalsIgnoreCase("enderpearl")) {
	            	projectileClass = EnderPearl.class;
	            	((CraftWorld) l.getLocation().getWorld()).getHandle().makeSound(l.getLocation().getX(), l.getLocation().getY(), l.getLocation().getZ(), "mob.endermen.portal", 1, (float)0.5);
	            } else	{
	            	projectileClass = Arrow.class;
	            }
			
                if((l instanceof Creature) && ((Creature)l).getTarget() == player)	{
                    projectile = l.launchProjectile(projectileClass);
                	projectile.setVelocity(l.getLocation().getDirection().multiply(velocity));
                	//EpicBoss.plugin.logger.info("Fired!");
                }	else	{
                	projectile = l.launchProjectile(projectileClass);
                	Vector facing = player.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(velocity);                
                	projectile.setVelocity(facing);
                }
            
				projectile.setBounce(false);
				projectile.setShooter(l);
				projectile.setMetadata("EpicBossProjectile", new FixedMetadataValue(EpicBoss.plugin, new ProjectileData(damage)));
			}
		}
	}
		
    public static class ProjectileData {
        public int damage;
        public ProjectileData(int damage) {
                this.damage = damage;
        }
    }
}
