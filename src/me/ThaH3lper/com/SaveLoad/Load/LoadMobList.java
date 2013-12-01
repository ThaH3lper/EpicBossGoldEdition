package me.ThaH3lper.com.SaveLoad.Load;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Mobs.MobCommon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class LoadMobList {
	
	public static void LoadMobsList()
	{
		if(EpicBoss.plugin.savelist.getCustomConfig().contains("Mobslist"))
		{
			String s = EpicBoss.plugin.savelist.getCustomConfig().getString("Mobslist");
			List<UUID> list = StringToList(s);
			EpicBoss.plugin.allMobs = list;
		}
	}
	
	public static void SaveMobsList()
	{
		String s = ListToString(EpicBoss.plugin.getMobsAll());
		EpicBoss.plugin.savelist.getCustomConfig().set("Mobslist", s);
		EpicBoss.plugin.savelist.saveCustomConfig();
	}
	
	public static String ListToString(List<LivingEntity> list)
	{
		String s = "";
		for(LivingEntity i : list)
		{
			if(i.isDead() != true)
			{
				//Bukkit.broadcastMessage(ChatColor.GREEN + "" + i.getUniqueId());
				s += i.getUniqueId() + ":" + MobCommon.getEpicMob(i).cmdName + ":" + i.getWorld().getName() + ":" + i.getWorld().getChunkAt(i.getLocation()).getX() + ":" + i.getWorld().getChunkAt(i.getLocation()).getZ() + ",";
			} else	{
				//Bukkit.broadcastMessage(ChatColor.RED + "" + i.getUniqueId());
			}
		}
		return s;
	}
	
	public static List<UUID> StringToList(String s)
	{
		List<UUID> list = new ArrayList<UUID>();
		String[] parts = s.split(",");
		for(String ent : parts)
		{
			if(ent.contains(":"))
			{
				String[] data = ent.split(":");
				World w = Bukkit.getWorld(data[2]);
				for(Entity e : w.getChunkAt(Integer.parseInt(data[3]), Integer.parseInt(data[4])).getEntities())
				{
					if(e instanceof LivingEntity)
					{
						LivingEntity l = (LivingEntity) e;
						UUID i = UUID.fromString(data[0]);
						if(l.getUniqueId().compareTo(i) == 0)
						{
							l = MobCommon.setMeta(l, data[1], "cmdname");
							list.add(l.getUniqueId());
						}
					}
				}
			}
		}
		return list;
	}
	
	public static List<UUID> Refresh(List<LivingEntity> list)
	{
		List<UUID> newList = new ArrayList<UUID>();
		for(LivingEntity i : list)
		{
			if(i.isDead() != true)
				newList.add(i.getUniqueId());
		}
		return newList;
	}
}