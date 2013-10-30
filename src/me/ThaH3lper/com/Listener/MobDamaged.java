package me.ThaH3lper.com.Listener;

import me.ThaH3lper.com.EpicBoss;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class MobDamaged implements Listener{

	@EventHandler(priority = EventPriority.HIGH)
	public void MobDamagedEvent(EntityDamageEvent e)
	{
		if(!(e.getEntity() instanceof LivingEntity))
			return;
		LivingEntity l = (LivingEntity) e.getEntity();
		if(!EpicBoss.plugin.allMobs.contains(l.getUniqueId()))
			return;
		
		if(e.getCause() == DamageCause.SUFFOCATION)	{
			Location Loc = l.getLocation();
			Loc.setY(Loc.getY() + 2);
			
			l.teleport(Loc);
			e.setCancelled(true);
			return;
		}
	}
}