package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillForcePullNear {
	
	// forcepullnear pull_radius:new_horizontal_radius:new_vertical_radius
	// Teleports all players in pull_radius to a random location within new_horizontal_radius:new_vertical_radius of the boss
		
	public static void ExecuteForcePullNear(LivingEntity l, String skill, Player player)
	{
		if(player == null) return;
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
				
				int radius = Integer.parseInt(data[0]);
				double new_radius_xz = Double.parseDouble(data[1]);
				double new_radius_y = Double.parseDouble(data[2]);
				
				Location Loc;
				
				if(radius > 0)
				{
					for(Player p : SkillHandler.getRadious(l, radius))
					{
						Loc = l.getLocation();
						
						double new_x = Loc.getX();
						double new_z = Loc.getZ();
						double new_y = Loc.getY();
						
						new_x = (EpicBoss.r.nextInt(2) == 1) ? (new_x + EpicBoss.r.nextInt((int)new_radius_xz + 1)) : (new_x - EpicBoss.r.nextInt((int)new_radius_xz + 1));
						new_z = (EpicBoss.r.nextInt(2) == 1) ? (new_z + EpicBoss.r.nextInt((int)new_radius_xz + 1)) : (new_z - EpicBoss.r.nextInt((int)new_radius_xz + 1));
						new_y = (EpicBoss.r.nextInt(2) == 1) ? (new_y + EpicBoss.r.nextInt((int)new_radius_y + 1)) : (new_y - EpicBoss.r.nextInt((int)new_radius_y + 1));
						
						Loc.setX(new_x);
						Loc.setZ(new_z);
						Loc.setY(new_y);
						
						p.teleport(Loc);
					}
				} else	{
					Loc = l.getLocation();
					
					double new_x = Loc.getX();
					double new_z = Loc.getZ();
					double new_y = Loc.getY();
					
					new_x = (EpicBoss.r.nextInt(2) == 1) ? (new_x + EpicBoss.r.nextInt((int)new_radius_xz + 1)) : (new_x - EpicBoss.r.nextInt((int)new_radius_xz + 1));
					new_z = (EpicBoss.r.nextInt(2) == 1) ? (new_z + EpicBoss.r.nextInt((int)new_radius_xz + 1)) : (new_z - EpicBoss.r.nextInt((int)new_radius_xz + 1));
					new_y = (EpicBoss.r.nextInt(2) == 1) ? (new_y + EpicBoss.r.nextInt((int)new_radius_y + 1)) : (new_y - EpicBoss.r.nextInt((int)new_radius_y + 1));
					
					Loc.setX(new_x);
					Loc.setZ(new_z);
					Loc.setY(new_y);
					
					player.teleport(Loc);
				}
			}
		}		
	}
}
