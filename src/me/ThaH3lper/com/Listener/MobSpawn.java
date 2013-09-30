package me.ThaH3lper.com.Listener;

import me.ThaH3lper.com.Spawning.SpawningHandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class MobSpawn implements Listener{

	@EventHandler(priority = EventPriority.HIGH)
	public void MobSpawnEvent(CreatureSpawnEvent e)
	{
		//nee a better way for a spawn event :S
		/*Bukkit.broadcastMessage(e.isCancelled() + "");
		if(e.getSpawnReason() != SpawnReason.CUSTOM && e.getEntity() != null)
		{
			Bukkit.broadcastMessage(ChatColor.RED + "" + e.getEntity().getType());
			//boolean b = SpawningHandler.Spawn(e.getEntity());
			//if(b) e.getEntity().remove();
		}*/
	}

}
