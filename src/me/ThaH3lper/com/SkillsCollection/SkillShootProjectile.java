package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Effect;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.metadata.FixedMetadataValue;

public class SkillShootProjectile {
	
	// Shoots a projectile
	// projectile type:damage:velocity
	// type can be arrow, snowball, egg, or enderpearl
			
	public static void ExecuteShoot(LivingEntity l, String skill, Player player)
	{
		String[] base = skill.split(" ");
		String[] data = base[1].split(":");
		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{
				String projectileType = data[0];
				int damage = Integer.parseInt(data[1]);
				float velocity = Float.parseFloat(data[2]);
	
				Projectile projectile;
                if (projectileType.equalsIgnoreCase("arrow")) {
                    projectile = l.launchProjectile(Arrow.class);
                    l.getWorld().playEffect(l.getLocation(), Effect.BOW_FIRE, 0);
	            } else if (projectileType.equalsIgnoreCase("snowball")) {
	            	projectile = l.launchProjectile(Snowball.class);
	            	l.getWorld().playEffect(l.getLocation(), Effect.BOW_FIRE, 0);
	            } else if (projectileType.equalsIgnoreCase("egg")) {
	            	projectile = l.launchProjectile(Egg.class);
	            	l.getWorld().playEffect(l.getLocation(), Effect.BOW_FIRE, 0);
	            } else if (projectileType.equalsIgnoreCase("enderpearl")) {
	            	projectile = l.launchProjectile(EnderPearl.class);
	            	((CraftWorld) l.getLocation().getWorld()).getHandle().makeSound(l.getLocation().getX(), l.getLocation().getY(), l.getLocation().getZ(), "mob.endermen.portal", 1, (float)0.5);
	            } else	{
	            	projectile = l.launchProjectile(Arrow.class);
	            }
			
                projectile.setVelocity(l.getLocation().getDirection().multiply(velocity));
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
