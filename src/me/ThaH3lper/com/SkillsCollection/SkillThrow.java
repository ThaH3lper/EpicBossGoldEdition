package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Skills.SkillHandler;
import org.bukkit.util.Vector;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillThrow {
	
	//- throw radius:horizontal_strength:vertical_strength =HP Chance
	
	public static void ExecuteThrow(LivingEntity l, String skill, Player player)
	{
		String[] base = skill.split(" ");
		String[] data = base[1].split(":");
		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{				
				int radius = Integer.parseInt(data[0]);
				float strength = Float.parseFloat(data[1]);
				float strengthY = Float.parseFloat(data[2]);
				Vector Bv = l.getLocation().toVector();
				Vector Pv, V;
				
				if(radius > 0)
				{
					for(Player p : SkillHandler.getRadious(l, radius))
					{
						Pv = p.getLocation().toVector();
						V = Pv.subtract(Bv).normalize().multiply((strength / 10));
						V.setY((strengthY / 10));

						p.setVelocity(V);
					}
				} else	{
					if(player == null) return;
					Pv = player.getLocation().toVector();
					V = Pv.subtract(Bv).normalize().multiply((strength / 10));
					V.setY((strengthY / 10));
					
					player.setVelocity(V);
				}
				
			}
		}
		
	}
}
