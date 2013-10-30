package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;
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
				BossSkillEvent event = new BossSkillEvent(l, skill, player, false);
				Bukkit.getServer().getPluginManager().callEvent(event);
				if(event.isChanceled())
					return;
				
				int radious = Integer.parseInt(data[0]);
				
				if(msg.contains("$player"))
				{
					if(player!=null)	{
						msg = msg.replace("$player", player.getName());
					} else	{
						return;
					}
				}
				
				if(msg.contains("$bosshp"))
					msg = msg.replace("$bosshp", String.valueOf(l.getHealth()));
				
				if(msg.contains("$boss"))	{
					EpicMobs em = MobCommon.getEpicMob(l);
					msg = msg.replace("$boss", em.Display);
				}
				
				msg = ChatColor.translateAlternateColorCodes('&', msg);
					
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
