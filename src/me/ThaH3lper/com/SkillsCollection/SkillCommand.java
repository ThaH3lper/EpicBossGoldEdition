package me.ThaH3lper.com.SkillsCollection;

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
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{
				if(msg.contains("$player"))
				{
					if(player!=null)
						msg = msg.replace("$player", player.getName());
				}
				if(msg.contains("$boss"))
					msg = msg.replace("$boss", l.getCustomName());
				
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), msg);
			}
		}
		
	}
}
