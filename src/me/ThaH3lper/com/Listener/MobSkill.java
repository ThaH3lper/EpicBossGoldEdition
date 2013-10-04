package me.ThaH3lper.com.Listener;

import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.MetadataValue;

public class MobSkill implements Listener{

	@EventHandler(priority = EventPriority.HIGH)
	public void MobSkillEvent(EntityDamageByEntityEvent e)
	{
		if(!(e.getEntity() instanceof LivingEntity))
			return;
		LivingEntity l = (LivingEntity) e.getEntity();
		if(!EpicBoss.plugin.allMobs.contains(l))
			return;
		EpicMobs em = MobCommon.getEpicMob(l);
		
		List<MetadataValue> list = l.getMetadata("skill");
		for(MetadataValue mv : list)
		{
			Bukkit.broadcastMessage(mv.asString());
		}
		 
		if(e.getDamager() instanceof Player)	{
			SkillHandler.ExecuteSkills(em.skills, l, (Player) e.getDamager());
		} else	{
			if(e.getDamager() instanceof Projectile)	{
				if(((Projectile)e.getDamager()).getShooter() instanceof Player)	{
					SkillHandler.ExecuteSkills(em.skills, l, (Player) ((Projectile)e.getDamager()).getShooter());
				}
			}
		}	
	}

}
