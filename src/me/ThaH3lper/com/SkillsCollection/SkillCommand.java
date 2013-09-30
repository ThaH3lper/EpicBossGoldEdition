package me.ThaH3lper.com.SkillsCollection;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillCommand {
	
	//- cmd 'command' =HP Chance
	
	public static void ExecuteCommand(LivingEntity l, String skill, Player player)
	{
		String[] temp = skill.split("'");
		String msg = temp[1];
		String[] base = skill.split(" ");
		float chance = Float.parseFloat(base[base.length-1]);
		Pattern Rpattern;
		Matcher Rmatcher;
		int rand = 0;
		
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{			
				if(msg.contains("$player_x"))
				{
					if(player!=null)	{	
						if(msg.substring(msg.indexOf("$player_x")+9, msg.indexOf("$player_x")+10).matches("%"))	{
							Rpattern = Pattern.compile("player_x%([0-9]+)");
							Rmatcher = Rpattern.matcher(msg);
							Rmatcher.find();
							rand = EpicBoss.r.nextInt(2);
							rand = (rand == 1) ? EpicBoss.r.nextInt(1+Integer.parseInt(Rmatcher.group(1))) : 0 - EpicBoss.r.nextInt(Integer.parseInt(Rmatcher.group(1)));
							
							msg = msg.replace("$player_x%" + Rmatcher.group(1), Integer.toString(player.getLocation().getBlockX() + rand));
						} else	{
							msg = msg.replace("$player_x", Integer.toString(player.getLocation().getBlockX()));
						}
					}
				}
				
				if(msg.contains("$player_y"))
				{
					if(player!=null)	{	
						if(msg.substring(msg.indexOf("$player_y")+9, msg.indexOf("$player_y")+10).matches("%"))	{
							Rpattern = Pattern.compile("player_y%([0-9]+)");
							Rmatcher = Rpattern.matcher(msg);
							Rmatcher.find();
							rand = EpicBoss.r.nextInt(2);
							rand = (rand == 1) ? EpicBoss.r.nextInt(1+Integer.parseInt(Rmatcher.group(1))) : 0 - EpicBoss.r.nextInt(Integer.parseInt(Rmatcher.group(1)));
							
							msg = msg.replace("$player_y%" + Rmatcher.group(1), Integer.toString(player.getLocation().getBlockY() + rand));
						} else	{
							msg = msg.replace("$player_y", Integer.toString(player.getLocation().getBlockY()));
						}
					}
				}
				
				if(msg.contains("$player_z"))
				{
					if(player!=null)	{	
						if(msg.substring(msg.indexOf("$player_z")+9, msg.indexOf("$player_z")+10).matches("%"))	{
							Rpattern = Pattern.compile("player_z%([0-9]+)");
							Rmatcher = Rpattern.matcher(msg);
							Rmatcher.find();
							rand = EpicBoss.r.nextInt(2);
							rand = (rand == 1) ? EpicBoss.r.nextInt(1+Integer.parseInt(Rmatcher.group(1))) : 0 - EpicBoss.r.nextInt(Integer.parseInt(Rmatcher.group(1)));
							
							msg = msg.replace("$player_z%" + Rmatcher.group(1), Integer.toString(player.getLocation().getBlockZ() + rand));
						} else	{
							msg = msg.replace("$player_z", Integer.toString(player.getLocation().getBlockZ()));
						}
					}
				}
				
				if(msg.contains("$player"))
				{
					if(player!=null)
						msg = msg.replace("$player", player.getName());
				}
								
				if(msg.contains("$boss_x"))	{
					if(msg.substring(msg.indexOf("$boss_x")+7, msg.indexOf("$boss_x")+8).matches("%"))	{
						Rpattern = Pattern.compile("boss_x%([0-9]+)");
						Rmatcher = Rpattern.matcher(msg);
						Rmatcher.find();
						rand = EpicBoss.r.nextInt(2);
						rand = (rand == 1) ? EpicBoss.r.nextInt(1+Integer.parseInt(Rmatcher.group(1))) : 0 - EpicBoss.r.nextInt(Integer.parseInt(Rmatcher.group(1)));
						
						msg = msg.replace("$boss_x%" + Rmatcher.group(1), Integer.toString(l.getLocation().getBlockX() + rand));
					} else	{
						msg = msg.replace("$boss_x", Integer.toString(l.getLocation().getBlockX()));
					}	
				}
				
				if(msg.contains("$boss_y"))	{
					if(msg.substring(msg.indexOf("$boss_y")+7, msg.indexOf("$boss_y")+8).matches("%"))	{
						Rpattern = Pattern.compile("boss_y%([0-9]+)");
						Rmatcher = Rpattern.matcher(msg);
						Rmatcher.find();
						rand = EpicBoss.r.nextInt(2);
						rand = (rand == 1) ? EpicBoss.r.nextInt(1+Integer.parseInt(Rmatcher.group(1))) : 0 - EpicBoss.r.nextInt(Integer.parseInt(Rmatcher.group(1)));
						
						msg = msg.replace("$boss_y%" + Rmatcher.group(1), Integer.toString(l.getLocation().getBlockY() + rand));
					} else	{
						msg = msg.replace("$boss_y", Integer.toString(l.getLocation().getBlockY()));
					}	
				}
				
				if(msg.contains("$boss_z"))	{
					if(msg.substring(msg.indexOf("$boss_z")+7, msg.indexOf("$boss_z")+8).matches("%"))	{
						Rpattern = Pattern.compile("boss_z%([0-9]+)");
						Rmatcher = Rpattern.matcher(msg);
						Rmatcher.find();
						rand = EpicBoss.r.nextInt(2);
						rand = (rand == 1) ? EpicBoss.r.nextInt(1+Integer.parseInt(Rmatcher.group(1))) : 0 - EpicBoss.r.nextInt(Integer.parseInt(Rmatcher.group(1)));
						
						msg = msg.replace("$boss_z%" + Rmatcher.group(1), Integer.toString(l.getLocation().getBlockZ() + rand));
					} else	{
						msg = msg.replace("$boss_z", Integer.toString(l.getLocation().getBlockZ()));
					}	
				}
				
				if(msg.contains("$boss"))
					msg = msg.replace("$boss", l.getCustomName());
				
				if(msg.contains("$world"))
					msg = msg.replace("$world", l.getWorld().getName());
				
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), msg);
			}
		}
		
	}
}
