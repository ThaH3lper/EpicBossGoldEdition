package me.ThaH3lper.com.Listener;

import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.SkillsCollection.SkillShootProjectile.ProjectileData;

import org.bukkit.craftbukkit.v1_7_R1.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.MetadataValue;

public class SkillShootProjectileListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGH)
    public void onProjectileHit(EntityDamageByEntityEvent event) {	 
    	if (!(event.getDamager() instanceof Projectile)) return;
    	if (!(event.getEntity() instanceof LivingEntity)) return;
    	
        final Projectile projectile = (Projectile)event.getDamager();
        
        List<MetadataValue> metas = projectile.getMetadata("EpicBossProjectile");
        if (metas == null || metas.size() == 0) return;

        for (MetadataValue meta : metas) {
            final ProjectileData data = (ProjectileData)meta.value();
            event.setDamage(data.damage);
            if(event.getDamager().getType() == EntityType.ENDER_PEARL)	{
            	((CraftWorld) event.getEntity().getLocation().getWorld()).getHandle().makeSound(event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), "mob.wither.hurt", 1, (float)0.5);
            }
            break;
        }
        projectile.removeMetadata("EpicBossProjectile", EpicBoss.plugin);
        projectile.remove();
    }
}

