package me.ThaH3lper.com.Listener;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Location.EpicLocation;
import me.ThaH3lper.com.Location.LocationHandler;
import me.ThaH3lper.com.Timer.EpicTimer;
import me.ThaH3lper.com.Timer.Timer;
import me.ThaH3lper.com.Timer.TimerHandler;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;

public class SignPlace implements Listener{

	@EventHandler(priority = EventPriority.HIGH)
	public void SignChange(SignChangeEvent e)
	{
		if(e.getLine(0).equalsIgnoreCase("epictimer"))
		{
			EpicTimer et = TimerHandler.getEpicTimer(e.getLine(1));
			EpicLocation el = LocationHandler.getEpicLocation(e.getLine(2));
			if(et != null && el != null)
			{
				if(e.getPlayer().hasPermission("epicboss.admin") || e.getPlayer().hasPermission("epicboss.timer"))
				{
					EpicBoss.plugin.allTimers.add(new Timer(e.getBlock().getLocation(), et, el, e.getLines()));
					e.getPlayer().sendMessage(ChatColor.GREEN + "Timer Created!");
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void RemoveTimer(BlockBreakEvent e)
	{
		if(e.getBlock().getType() == Material.SIGN_POST || e.getBlock().getType() == Material.WALL_SIGN)
		{
			Timer timer = null;
			for(Timer t : EpicBoss.plugin.allTimers)
			{
				if(t.loc.equals(e.getBlock().getLocation()))
					timer = t;
					
			}
			if(timer != null)
			{
				if(e.getPlayer().hasPermission("epicboss.admin") || e.getPlayer().hasPermission("epicboss.timer"))
				{
					EpicBoss.plugin.allTimers.remove(timer);
					e.getPlayer().sendMessage(ChatColor.RED + "Timer Removed!");
				}
				else
					e.setCancelled(true);
			}
		}
	}
}
