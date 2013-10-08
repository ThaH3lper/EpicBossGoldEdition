package me.ThaH3lper.com.Timer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.LivingEntity;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Location.EpicLocation;
import me.ThaH3lper.com.Location.LocationHandler;
import me.ThaH3lper.com.Mobs.EpicMobs;

public class TimerHandler {

	public static List<EpicMobs> getMobs(String s)
	{
		List<EpicMobs> list = new ArrayList<EpicMobs>();
		for(EpicMobs em : EpicBoss.plugin.listMobs)
		{
			if(em.cmdName.contains(s))
				list.add(em);
		}
		return list;
	}
	
	public static EpicTimer getEpicTimer(String s)
	{
		for(EpicTimer et : EpicBoss.plugin.listTimers)
		{
			if(s.equals(et.cmdName))
				return et;
		}
		return null;
	}
	
	public static void SaveAllTimers()
	{
		List<String> save = new ArrayList<String>();
		for(Timer t : EpicBoss.plugin.allTimers)
		{
			//Location
			String loc = t.loc.getWorld().getName() + "," + t.loc.getX() + "," + t.loc.getY() + "," + t.loc.getZ();
			//Time
			String time = t.clock + "";
			//Mobs
			String mobs = "";
			for(UUID c : t.mobs)
				mobs += c + ",";
			if(mobs.equals(""))
				mobs = "null";
		
			save.add(loc + ":" + time + ":" + mobs);
		}
		EpicBoss.plugin.savelist.getCustomConfig().set("Timers", save);
		EpicBoss.plugin.savelist.saveCustomConfig();
	}
	
	public static void LoadAllTimers()
	{
		List<String> list = EpicBoss.plugin.savelist.getCustomConfig().getStringList("Timers");
		if(list == null)
			return;
		for(String s : list)
		{
			String[] parts = s.split(":");
			//Location
			String[] data = parts[0].split(",");
			Location loc = new Location(Bukkit.getWorld(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]));
			
			//Time
			int time = Integer.parseInt(parts[1]);
			
			//Mob
			List<UUID> mobslist = new ArrayList<UUID>();
			String[] mobs = parts[2].split(",");
			for(String m : mobs)
			{
				if(!m.equals("") && !m.equals("null"))
				{
					for(LivingEntity l : EpicBoss.plugin.getMobsAll())
					{
						UUID i = UUID.fromString(m);
						if(l.getUniqueId().compareTo(i) == 0)
						{
							mobslist.add(l.getUniqueId());
						}
					}
				}
			}
			
			if(loc.getBlock().getType() == Material.WALL_SIGN || loc.getBlock().getType() == Material.SIGN_POST)
			{
				Sign sign = (Sign) loc.getBlock().getState();
				EpicTimer et = TimerHandler.getEpicTimer(sign.getLine(1));
				EpicLocation el = LocationHandler.getEpicLocation(sign.getLine(2));
				
				Timer t = new Timer(loc, et, el, sign.getLines());
				t.clock = time;
				t.mobs = mobslist;
				EpicBoss.plugin.allTimers.add(t);
			}
			
		}
	}
}
