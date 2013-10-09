package me.ThaH3lper.com.Timer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Location.EpicLocation;
import me.ThaH3lper.com.Mobs.MobHandler;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.LivingEntity;

public class Timer {

	public EpicTimer et;
	public List<UUID> mobs = new ArrayList<UUID>();
	public EpicLocation el;
	public Location loc;
	public Sign sign;
	
	int clock;
	
	public Timer(Location loc, EpicTimer et, EpicLocation el, String[] lines)
	{
		this.loc = loc;
		this.et = et;
		this.el = el;
		this.clock = et.interval;
		
		sign = (Sign)loc.getBlock().getState();
		sign.setLine(0, ChatColor.GREEN + "[EpicTimer]");
		sign.setLine(1, lines[1]);
		sign.setLine(2, lines[2]);
		sign.update();
	}
	public void Update()
	{
		sign.setLine(3, clock + "");
		sign.update();
		sign = (Sign)loc.getBlock().getState();
	}
	public boolean tick(int sec)
	{
		if(mobs.size() >= et.amount)
			return false;
		
		clock -= sec;
		Update();
		
		if(clock <= 0)
		{
			clock = et.interval;
			if(mobs.size() < et.amount)
			{
				el.LoadChunk();
				mobs.add(MobHandler.SpawnMob(et.bosses.get(EpicBoss.r.nextInt(et.bosses.size())).cmdName, el.location).getUniqueId());
				return true;
			}
		}
		return false;
	}
	public void WalkCheck()
	{
		if(et.walk == 0)
			return;
		for(LivingEntity l : loc.getWorld().getLivingEntities())
		{
			if(mobs.contains(l.getUniqueId()))
			{
				if(el.location.distance(l.getLocation()) >= et.walk)
					l.teleport(el.location);
			}
		}

	}
}
