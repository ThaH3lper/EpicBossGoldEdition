package me.ThaH3lper.com.Listener;

import me.ThaH3lper.com.EpicBoss;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SlimeSplitEvent;

public class SlimeSplit implements Listener{

	@EventHandler(priority = EventPriority.HIGH)
	public void SplitEvent(SlimeSplitEvent e)
	{
		if(!(e.getEntity() instanceof LivingEntity))
			return;
		LivingEntity l = (LivingEntity) e.getEntity();
		if(!EpicBoss.plugin.allMobs.contains(l.getUniqueId()))
			return;
		e.setCount(0);
	}

}
