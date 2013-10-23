package me.ThaH3lper.com.Listener;

import me.ThaH3lper.com.Spawning.SpawningHandler;

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
		if(e.getSpawnReason() != SpawnReason.CUSTOM && e.getEntity() != null)
		{
			SpawningHandler.templist.add(e.getEntity());
		}
	}

}
