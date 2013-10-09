package me.ThaH3lper.com.Listener;

import me.ThaH3lper.com.EpicBoss;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerLeashEntityEvent;

public class LeashEvent implements Listener{

	@EventHandler(priority = EventPriority.HIGH)
	public void LeashEntity(PlayerLeashEntityEvent e)
	{
		if(!(e.getEntity() instanceof LivingEntity))
			return;
		LivingEntity l = (LivingEntity) e.getEntity();
		if(EpicBoss.plugin.allMobs.contains(l.getUniqueId()))
		{
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "You can't leash this mob");
		}
	}
}
