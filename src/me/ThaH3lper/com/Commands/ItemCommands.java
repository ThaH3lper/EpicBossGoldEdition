package me.ThaH3lper.com.Commands;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Items.EpicItems;
import me.ThaH3lper.com.Items.ItemHandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class ItemCommands {

	//eb item
	//eb item list
	//eb item list [search]
	//eb item info [item]
	//eb item get [item]
	//eb item show [filename]
	
	public static void getItem(CommandSender sender, Command cmd, String commandlabel, String[] args)
	{
		if(args.length == 1)
		{
			sender.sendMessage(EpicBoss.plugin.menu);
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb item list" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - List all items loaded");
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb item drop" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - drops all items on ground around you");
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb item list [word]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - List all items whit matching word");
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb item info [name]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - Show info about the item");
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb item get [item]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - get the item");
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb item give [item] [player]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - get player a item");
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb item show [Filename]" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - shows all items from that file [Example.yml]");
		}
		else if(args.length == 2)
		{
			if(args[1].equalsIgnoreCase("list"))
			{	
				String s = ChatColor.LIGHT_PURPLE + "All items: ";
				sender.sendMessage(ChatColor.GREEN + "Items dropped");
				for(EpicItems ei : EpicBoss.plugin.listItems)
				{
					s += ChatColor.RED + ei.cmdName + ChatColor.GRAY +", ";
				}
				sender.sendMessage(s);
			}
			else if(args[1].equalsIgnoreCase("drop"))
			{	
				if(sender instanceof Player)
				{
					sender.sendMessage(ChatColor.GREEN + "Items dropped");
					Player p = (Player) sender;
					for(EpicItems ei : EpicBoss.plugin.listItems)
					{
						p.getWorld().dropItem(p.getLocation(), ei.getItemStack());
					}
				}
				else
					sender.sendMessage(ChatColor.RED + "Can only be used from player!");
			}
		}
		else if(args.length == 3)
		{
			if(args[1].equalsIgnoreCase("list"))
			{	
				String s = ChatColor.LIGHT_PURPLE + "Items Found: ";
				for(EpicItems ei : EpicBoss.plugin.listItems)
				{
					if(ei.cmdName.contains(args[2]))
					{
						String[] parts = ei.cmdName.split(args[2]);
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
			else if(args[1].equalsIgnoreCase("info"))
			{	
				if(ItemHandler.getEpicItem(args[2]) != null)
				{
					EpicItems ei = ItemHandler.getEpicItem(args[2]);
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "File: " + ChatColor.RED + ei.file);
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "CmdName: " + ChatColor.RED + ei.cmdName);
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "Id: " + ChatColor.RED + ei.id + ChatColor.GRAY + " / " + ChatColor.RED + Material.getMaterial(ei.id));
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "Data: " + ChatColor.RED + ei.data + ChatColor.LIGHT_PURPLE + " Amount: " + ChatColor.RED + ei.amount);
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "Display: " + ChatColor.RED + ei.Display + ChatColor.GRAY + " / " + ChatColor.translateAlternateColorCodes('&', ei.Display));
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "There is no item called " + args[2]);
				}
			}
			else if(args[1].equalsIgnoreCase("get"))
			{	
				if(sender instanceof Player)
				{
					Player p = (Player) sender;
					if(ItemHandler.getEpicItem(args[2]) != null)
					{
						EpicItems ei = ItemHandler.getEpicItem(args[2]);
						p.getInventory().addItem(ei.getItemStack());
						sender.sendMessage(ChatColor.GREEN + "Item Given");
					}
					else
					{
						sender.sendMessage(ChatColor.RED + "There is no item called " + args[2]);
					}
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "This command needs to be executed by a Player");
				}
			}
			else if(args[1].equalsIgnoreCase("show"))
			{	
				if(sender instanceof Player)
				{
					Player p = (Player) sender;
					Inventory i = Bukkit.getServer().createInventory(null, InventoryType.CHEST);
					for(EpicItems ie : EpicBoss.plugin.listItems)
					{
						if(ie.file.equals(args[2]))
						{
							i.addItem(ie.getItemStack());
						}
					}
					if(i.firstEmpty() != 0)
						p.openInventory(i);
					else
						p.sendMessage(ChatColor.RED + args[2] +" file don't excist or has no Items!");
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "This command needs to be executed by a Player");
				}
			}
			
		}
		else if(args.length == 4)
		{
			if(args[1].equalsIgnoreCase("give"))
			{	
				if(ItemHandler.getEpicItem(args[2]) != null)
				{
					if(Bukkit.getPlayer(args[3]) != null)
					{
						Player p = Bukkit.getPlayer(args[3]);
						if(p.isOnline())
						{
							EpicItems ei = ItemHandler.getEpicItem(args[2]);
							p.getInventory().addItem(ei.getItemStack());
							sender.sendMessage(ChatColor.GREEN + "Item Given");
						}
						else
							sender.sendMessage(ChatColor.RED + "The player " + args[3] + " is not online!");
					}
					else
						sender.sendMessage(ChatColor.RED + "There is no player called " + args[3]);
				}
				else
					sender.sendMessage(ChatColor.RED + "There is no item called " + args[2]);
			}
		}
	}
}
