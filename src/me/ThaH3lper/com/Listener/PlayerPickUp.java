package me.ThaH3lper.com.Listener;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Drops.DropHandler;
import me.ThaH3lper.com.Drops.TempPlayer;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickUp implements Listener{

	@EventHandler(priority = EventPriority.HIGH)
	public void PlayerPickItem(PlayerPickupItemEvent e)
	{
		Item i = e.getItem();
		if(EpicBoss.plugin.fairItems.contains(i))
		{
			TempPlayer tp = DropHandler.getTempPlayer(e.getPlayer());
			if(tp == null)
			{
				tp = new TempPlayer(e.getPlayer());
				EpicBoss.plugin.listTempPlayer.add(tp);
			}
			if(tp.items.contains(i))
			{
				EpicBoss.plugin.fairItems.remove(i);
			}
			else
				e.setCancelled(true);
		}
		
	}
}
