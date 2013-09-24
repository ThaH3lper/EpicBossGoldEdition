package me.ThaH3lper.com.Commands;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Location.EpicLocation;
import me.ThaH3lper.com.Location.LocationHandler;
import me.ThaH3lper.com.SaveLoad.Load.LoadLocation;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocationCommands {
	
	public static void getLoc(CommandSender sender, Command cmd, String commandlabel, String[] args)
	{
		//eb loc
		//eb loc list
		//eb loc list [word]
		//eb loc add [name]
		//eb loc remove [name]
		//eb loc teleport [name]
		
		if(args.length == 1)
		{
			sender.sendMessage(EpicBoss.plugin.menu);
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb loc list" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - list all locations");
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb loc list [word]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - search for location");
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb loc add [name]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - add new location");
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb loc remove [name]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - remove location");
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb loc teleport [name]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - teleport to location");
		}
		else if(args.length == 2)
		{
			if(args[1].equalsIgnoreCase("list"))
			{
				String s = ChatColor.LIGHT_PURPLE + "Locations: ";
				for(EpicLocation el : EpicBoss.plugin.listLoc)
				{
					s += ChatColor.RED + el.name + ChatColor.GRAY +", ";
				}
				sender.sendMessage(s);
			}
		}
		else if(args.length == 3)
		{
			if(args[1].equalsIgnoreCase("list"))
			{
				String s = ChatColor.LIGHT_PURPLE + "Locations Found: ";
				for(EpicLocation el : EpicBoss.plugin.listLoc)
				{
					if(el.name.contains(args[2]))
					{
						String[] parts = el.name.split(args[2]);
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
			else if(args[1].equalsIgnoreCase("add"))
			{
				if(sender instanceof Player)
				{
					Player p = (Player) sender;
					String name = args[2];
					Location l = p.getLocation();
					EpicBoss.plugin.listLoc.add(new EpicLocation(l, name));
					LoadLocation.saveAllLocations();
					p.sendMessage(ChatColor.GREEN + "Location Created!" + ChatColor.GRAY + " [" + ChatColor.RED + name + ChatColor.GRAY + "]");
				}
				else
					sender.sendMessage(ChatColor.RED + "This Command is only for Players! not for Consoles!");
				
			}
			else if(args[1].equalsIgnoreCase("remove"))
			{
				String name = args[2];
				EpicLocation el = LocationHandler.getEpicLocation(name);
				if(el != null)
				{
					EpicBoss.plugin.listLoc.remove(el);
					LoadLocation.saveAllLocations();
					sender.sendMessage(ChatColor.GREEN + "Location Removed!" + ChatColor.GRAY + " [" + ChatColor.RED + name + ChatColor.GRAY + "]");
				}
				else
					sender.sendMessage(ChatColor.RED + "Location " + name + " do not excist!");
			}
			else if(args[1].equalsIgnoreCase("teleport"))
			{
				if(sender instanceof Player)
				{
					Player p = (Player) sender;
					String name = args[2];
					EpicLocation el = LocationHandler.getEpicLocation(name);
					if(el != null)
					{
						p.teleport(el.location);
					}
					else
						sender.sendMessage(ChatColor.RED + "Location " + name + " do not excist!");
				}
			}
		}
	}
}
