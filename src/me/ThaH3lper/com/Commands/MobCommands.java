package me.ThaH3lper.com.Commands;

import java.util.ArrayList;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Location.EpicLocation;
import me.ThaH3lper.com.Location.LocationHandler;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.EpicMobsList;
import me.ThaH3lper.com.Mobs.MobCommon;
import me.ThaH3lper.com.Mobs.MobHandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class MobCommands {
	
	public static void getMob(CommandSender sender, Command cmd, String commandlabel, String[] args)
	{
		//eb mob
		//eb mob list
		//eb mob list [search]
		//eb mob info [boss] --
		//eb mob spawn [boss]
		//eb mob spawn [boss] [amount]
		//eb mob spawn [boss] [location]
		//eb mob kill [contains]
		//eb mob killall
		
		if(args.length == 1)
		{
			sender.sendMessage(EpicBoss.plugin.menu);
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb mob list" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - list all mobs");
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb mob list [word]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - search for a mob");
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb mob info [MobName]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - show info about boss");
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb mob killall" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - remove all bosses");
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb mob kill [word]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - kill bosses that contains word");
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb mob spawn [MobName]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - Spawn a mob");
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb mob spawn [MobName] [Amount]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - spawn mobs");
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb mob spawn [MobName] [Amount] [world,x,y,z]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - spawn mobs at cords");
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb mob spawn [MobName] [Amount] [loc]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - spawn mobs at location");
		}
		else if(args.length == 2)
		{
			if(args[1].equalsIgnoreCase("list"))
			{
				String s = ChatColor.LIGHT_PURPLE + "Mobs Loaded: ";
				for(EpicMobs em : EpicBoss.plugin.listMobs)
				{
					s += ChatColor.RED + em.cmdName + ChatColor.GRAY +", ";
				}
				for(EpicMobsList el : EpicBoss.plugin.listMobslist)
				{
					s += ChatColor.RED + el.cmdName + ChatColor.GRAY +", ";
				}
				sender.sendMessage(s);
			}
			else if(args[1].equalsIgnoreCase("killall"))
			{
				for(LivingEntity l : EpicBoss.plugin.getMobsAll())
				{
					EntityDeathEvent event = new EntityDeathEvent(l, new ArrayList<ItemStack>(0));
					EpicBoss.plugin.getServer().getPluginManager().callEvent(event);
					l.remove();
				}
				sender.sendMessage(ChatColor.GREEN + "All bosses removed!");
			}
		}
		else if(args.length == 3)
		{
			if(args[1].equalsIgnoreCase("list"))
			{	
				String s = ChatColor.LIGHT_PURPLE + "Mobs Found: ";
				for(EpicMobs em : EpicBoss.plugin.listMobs)
				{
					if(em.cmdName.contains(args[2]))
					{
						String[] parts = em.cmdName.split(args[2]);
						if(parts.length == 2)
							s += ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.RED + parts[1] + ChatColor.GRAY + ", ";
						if(parts.length == 1)
							s += ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", ";
						if(parts.length == 0)
							s += ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", ";
					}
				}
				for(EpicMobsList el : EpicBoss.plugin.listMobslist)
				{
					if(el.cmdName.contains(args[2]))
					{
						String[] parts = el.cmdName.split(args[2]);
						if(parts.length == 2)
							s += ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.RED + parts[1] + ChatColor.GRAY + ", ";
						if(parts.length == 1)
							s += ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", ";
						if(parts.length == 0)
							s += ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", ";
					}
				}
				sender.sendMessage(s);
			}
			if(args[1].equalsIgnoreCase("spawn"))
			{
				if(sender instanceof Player)
				{
					Player p = (Player) sender;
					LivingEntity l = MobHandler.SpawnMob(args[2], p.getLocation());
					if(l != null)
						sender.sendMessage(ChatColor.GREEN + "Mob Spawned!");
					else
						sender.sendMessage(ChatColor.RED + "There is no mob called " + args[2]);
							
				}
				else
					sender.sendMessage(ChatColor.RED + "This Command is only for Players! not for Consoles!");
			}
			if(args[1].equalsIgnoreCase("kill"))
			{
				String s = ChatColor.GREEN + "Mobs Killed: ";
				for(LivingEntity l : EpicBoss.plugin.getMobsAll())
				{
					EpicMobs em = MobCommon.getEpicMob(l);
					if(em != null)						
					{
						if(em.cmdName.contains(args[2]))
						{
							EntityDeathEvent event = new EntityDeathEvent(l, new ArrayList<ItemStack>(0));
							EpicBoss.plugin.getServer().getPluginManager().callEvent(event);
							l.remove();
						}
					}
				}
				for(EpicMobs em : EpicBoss.plugin.listMobs)
				{
					if(em.cmdName.contains(args[2]))
					{
						String[] parts = em.cmdName.split(args[2]);
						if(parts.length == 2)
							s += ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.RED + parts[1] + ChatColor.GRAY + ", ";
						if(parts.length == 1)
							s += ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", ";
						if(parts.length == 0)
							s += ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", ";
					}
				}
				sender.sendMessage(s);
			}
			if(args[1].equalsIgnoreCase("info"))
			{
				if(MobCommon.getEpicMob(args[2]) != null)
				{
					EpicMobs em = MobCommon.getEpicMob(args[2]);
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "File: " + ChatColor.RED + em.file);
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "CmdName: " + ChatColor.RED + em.cmdName);
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "Display: " + ChatColor.RED + em.Display + ChatColor.GRAY + " / " + ChatColor.translateAlternateColorCodes('&', em.Display));
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "Type: " + ChatColor.RED + em.Mobtype);
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "MaxHealth: " + ChatColor.RED + em.health);
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "Damage: " + ChatColor.RED + em.damage);
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "Despawn: " + ChatColor.RED + em.despawn);
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "There is no mob called " + args[2]);
				}
			}
		}
		else if(args.length == 4)
		{
			if(args[1].equalsIgnoreCase("spawn"))
			{
				if(sender instanceof Player)
				{
					Player p = (Player) sender;
					int m = Integer.parseInt(args[3]);
					LivingEntity l = null;
					for(int i = 0; i < m; i++)
						l = MobHandler.SpawnMob(args[2], p.getLocation());
					if(l != null)
						sender.sendMessage(ChatColor.GREEN + "Mobs Spawned!");
					else
						sender.sendMessage(ChatColor.RED + "There is no mob called " + args[2]);
							
				}
			}	
		}
		else if(args.length == 5)
		{
			if(args[1].equalsIgnoreCase("spawn") && args[4].contains(","))
			{
				String[] part = args[4].split(",");
				World w = Bukkit.getWorld(part[0]);
				float x = Float.parseFloat(part[1]);
				float y = Float.parseFloat(part[2]);
				float z = Float.parseFloat(part[3]);
				if(w != null)
				{
					Location loc = new Location(w, x, y, z);
					int m = Integer.parseInt(args[3]);
					LivingEntity l = null;
					for(int i = 0; i < m; i++)
						l = MobHandler.SpawnMob(args[2], loc);
					if(l != null)
						sender.sendMessage(ChatColor.GREEN + "Mobs Spawned!");
					else
						sender.sendMessage(ChatColor.RED + "There is no mob called " + args[2]);
				}
				else
					sender.sendMessage(ChatColor.RED + "There is no World called " + part[0]);
			}
			else if(args[1].equalsIgnoreCase("spawn"))
			{
				EpicLocation el = LocationHandler.getEpicLocation(args[4]);
				if(el != null)
				{
					int m = Integer.parseInt(args[3]);
					LivingEntity l = null;
					for(int i = 0; i < m; i++)
						l = MobHandler.SpawnMob(args[2], el.location);
					if(l != null)
						sender.sendMessage(ChatColor.GREEN + "Mobs Spawned!");
					else
						sender.sendMessage(ChatColor.RED + "There is no mob called " + args[2]);
				}
				else
					sender.sendMessage(ChatColor.RED + "There is no Location called " + args[4]);
			}
		}
	}
}
