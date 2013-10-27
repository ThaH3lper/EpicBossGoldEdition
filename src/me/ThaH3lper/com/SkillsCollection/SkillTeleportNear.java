package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillTeleportNear {
	
	// teleportnear horizontal_radius:vertical_radius
		
	public static void ExecuteTeleportNear(LivingEntity l, String skill, Player player)
	{
		if(player == null) return;
		String[] base = skill.split(" ");
		String[] data = base[1].split(":");
		
		double radius_xz = Double.parseDouble(data[0]);
		double radius_y = Double.parseDouble(data[1]);

		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{	
				Location Loc;
				
				Loc = player.getLocation();
							
				double new_x = Loc.getX();
				double new_z = Loc.getZ();
				double new_y = Loc.getY();
				
				new_x = (EpicBoss.r.nextInt(2) == 1) ? (new_x + EpicBoss.r.nextInt((int)radius_xz + 1)) : (new_x - EpicBoss.r.nextInt((int)radius_xz + 1));
				new_z = (EpicBoss.r.nextInt(2) == 1) ? (new_z + EpicBoss.r.nextInt((int)radius_xz + 1)) : (new_z - EpicBoss.r.nextInt((int)radius_xz + 1));
				new_y = (EpicBoss.r.nextInt(2) == 1) ? (new_y + EpicBoss.r.nextInt((int)radius_y + 1)) : (new_y - EpicBoss.r.nextInt((int)radius_y + 1));
				
				Loc.setX(new_x);
				Loc.setZ(new_z);
				Loc.setY(new_y);
				
				l.teleport(Loc);
			}
		}
		
	}
}
