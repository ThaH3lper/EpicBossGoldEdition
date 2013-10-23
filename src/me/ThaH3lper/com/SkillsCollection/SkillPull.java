package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class SkillPull {
	
	// pull radius:velocity
		
	public static void ExecutePull(LivingEntity l, String skill, Player player)
	{
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
				float velocity = Float.parseFloat(data[1]);

				if(radius > 0)
				{
					for(Player p : SkillHandler.getRadious(l, radius))
					{
						if(p.hasLineOfSight(l))	{
							double distance = l.getLocation().distance(p.getLocation());
							double modxz = (distance*0.5)*velocity;
							double mody = ((distance*0.34)*velocity);
							mody = ((l.getLocation().getY() - p.getLocation().getY()) != 0) ? mody * (Math.abs(l.getLocation().getY() - p.getLocation().getY())*0.5) : mody;
							
							Vector direction = p.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(velocity);
							direction.setX( (direction.getX()*-1)*modxz );
							direction.setZ( (direction.getZ()*-1)*modxz );
							direction.setY( (direction.getY()*-1)*mody );
							p.setVelocity(direction);
						}
					}
				} else	{
					Vector direction = player.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(velocity);
					direction.setX( direction.getX()*-1 );
					direction.setZ( direction.getZ()*-1 );
					player.setVelocity(direction);
				}		
			}
		}
		
	}
}
