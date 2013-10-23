package me.ThaH3lper.com.Commands;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.SaveLoad.LoadSetup;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandInput implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args)
	{
		if(sender instanceof Player)
		{
			Player p = (Player) sender;
			if(!p.hasPermission("epicboss.admin") || !p.hasPermission("epicboss.item") || !p.hasPermission("epicboss.location") || !p.hasPermission("epicboss.mob") || !p.hasPermission("epicboss.timer") || !p.hasPermission("epicboss.reload"))
				return false;
		}
		if(args.length == 0)
		{
			sender.sendMessage(EpicBoss.plugin.menu);
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb item" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - Show Item Commands");
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb mob" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - Show Mob Commands");
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb loc" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - Show Location Commands");
			sender.sendMessage(ChatColor.LIGHT_PURPLE+ "/eb reload" + ChatColor.GREEN + "" + ChatColor.ITALIC+ " - Reload all files");
		}
		else if(args.length > 0)
		{
			if(args[0].equalsIgnoreCase("item"))
			{
				if(sender instanceof Player)
				{
					Player p = (Player) sender;
					if(p.hasPermission("epicboss.admin") || p.hasPermission("epicboss.item"))
						ItemCommands.getItem(sender, cmd, commandlabel, args);		
				}
				else
					ItemCommands.getItem(sender, cmd, commandlabel, args);
			}
			else if(args[0].equalsIgnoreCase("mob"))
			{
				if(sender instanceof Player)
				{
					Player p = (Player) sender;
					if(p.hasPermission("epicboss.admin") || p.hasPermission("epicboss.mob"))
						MobCommands.getMob(sender, cmd, commandlabel, args);	
				}
				else
					MobCommands.getMob(sender, cmd, commandlabel, args);
			}
			else if(args[0].equalsIgnoreCase("loc"))
			{
				if(sender instanceof Player)
				{
					Player p = (Player) sender;
					if(p.hasPermission("epicboss.admin") || p.hasPermission("epicboss.location"))
						LocationCommands.getLoc(sender, cmd, commandlabel, args);
				}
				else
					LocationCommands.getLoc(sender, cmd, commandlabel, args);
			}
			else if(args[0].equalsIgnoreCase("reload"))
			{
				if(sender instanceof Player)
				{
					Player p = (Player) sender;
					if(p.hasPermission("epicboss.admin") || p.hasPermission("epicboss.reload"))
					{
						LoadSetup.SaveAll();
						LoadSetup.ResetAll();
						LoadSetup.LoadAll(false);
						sender.sendMessage(ChatColor.GREEN + "EpicBoss Reloded!");
					}		
				}
				else
				{
					LoadSetup.SaveAll();
					LoadSetup.ResetAll();
					LoadSetup.LoadAll(true);
					sender.sendMessage(ChatColor.GREEN + "EpicBoss Reloded!");
				}
			}
		}
		return false;
	}

}
