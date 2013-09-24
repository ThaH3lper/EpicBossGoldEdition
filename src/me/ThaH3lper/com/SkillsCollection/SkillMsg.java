package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillMsg {
	
	//- msg radious:'Here is message' =HP Chance
	// radious: 0 = All!
	
	public static void ExecuteMsg(LivingEntity l, String skill, Player player)
	{
		String[] temp = skill.split("'");
		String msg = temp[1];
		String[] base = skill.split(" ");
		String[] data = base[1].split(":");
		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{
				int radious = Integer.parseInt(data[0]);
				
				msg = ChatColor.translateAlternateColorCodes('&', msg);
				if(msg.contains("$player"))
				{
					if(player!=null)
						msg = msg.replace("$player", player.getName());
				}
				if(msg.contains("$boss"))
					msg = msg.replace("$boss", l.getCustomName());
					
				if(radious != 0)
				{
					for(Player p : SkillHandler.getRadious(l, radious))
						p.sendMessage(msg);
				}
				else
					Bukkit.broadcastMessage(msg);
			}
		}
		
	}
}
