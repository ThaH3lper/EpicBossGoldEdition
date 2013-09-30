package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillRadiusCommand {

	// Executes a command for all players within a certain radius around the boss.
	//- radiuscmd radius:'command' =HP Chance
	
	public static void ExecuteCommand(LivingEntity l, String skill)
	{
		String[] base = skill.split(" ");
		String[] data = base[1].split(":");
		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{
				int radious = Integer.parseInt(data[0]);
					
				if(radious != 0)
				{
					for(Player p : SkillHandler.getRadious(l, radious))
						SkillCommand.ExecuteCommand(l, skill, p);
				}
			}
		}
		
	}
	
}
