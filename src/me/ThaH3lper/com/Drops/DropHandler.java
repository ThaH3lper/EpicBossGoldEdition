package me.ThaH3lper.com.Drops;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Drops.Fair.FairDrops;
import me.ThaH3lper.com.Drops.Fair.FairPlayer;
import me.ThaH3lper.com.Mobs.EpicMobs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DropHandler {

	public static EpicNormal getEpicNormal(String s)
	{
		for(EpicNormal en : EpicBoss.plugin.listLoots)
		{
			if(en.cmdName.equals(s))
			{
				return en;
			}
		}
		return null;
	}
	
	public static void Drop(Location loc, int Exp, List<ItemStack> drops)
	{
		for(ItemStack is : drops)
		{
			loc.getWorld().dropItemNaturally(loc, is);
		}
		if(Exp != 0)
		{
			int i = Exp % 4;
			int per = (Exp - (Exp % 4))/4;
			for(int y = 0; y < 4; y++)
			{
				ExperienceOrb eo = (ExperienceOrb)loc.getWorld().spawnEntity(loc, EntityType.EXPERIENCE_ORB);
				eo.setExperience(per);
			}
			if(i != 0)
			{
				ExperienceOrb eo = (ExperienceOrb)loc.getWorld().spawnEntity(loc, EntityType.EXPERIENCE_ORB);
				eo.setExperience(i);
			}
		}
	}
	
	public static FairDrops getFairDrops(LivingEntity l)
	{
		for(FairDrops fd : EpicBoss.plugin.listFair)
		{
			if(fd.entity.equals(l))
				return fd;
		}
		return null;
	}
	
	public static List<EpicNormal> getFairDropList(EpicMobs em)
	{
		List<EpicNormal> list = new ArrayList<EpicNormal>();
		int i = 0;
		for(String s : em.loot)
		{
			if(s.contains(":"))
			{
				String[] split = s.split(":");
				EpicNormal en = getEpicNormal(split[0]);
				if(!split[1].equals("rest"))
				{
					i = Integer.parseInt(split[1]);
					if(en != null && i != 0)
					{
						list.add(i - 1, en);
					}
				}
			}
		}
		return list;
		
	}
	
	public static EpicNormal getEpicNormalRest(EpicMobs em)
	{
		for(String s : em.loot)
		{
			if(s.contains(":"))
			{
				String[] split = s.split(":");
				if(split[1].equals("rest"))
				{
					EpicNormal en = getEpicNormal(split[0]);
					if(en != null)
						return en;
				}
			}
		}
		return null;
	}
	
	public static FairPlayer getFairPlayer(FairDrops fd, Player p)
	{
		for(FairPlayer fp : fd.players)
		{
			if(fp.player.equals(p))
				return fp;
		}
		return null;
	}
	
	public static void dropPlayer(EpicNormal en, Player p)
	{
		String name = ChatColor.GOLD + "Loot! " + ChatColor.GREEN + "Exp: " + ChatColor.GRAY + en.Exp;
		Inventory i = Bukkit.getServer().createInventory(null, 18, name);
		for(ItemStack s : en.getDrops())
			i.addItem(s);
		p.openInventory(i);
		p.giveExp(en.Exp);
	}
}
