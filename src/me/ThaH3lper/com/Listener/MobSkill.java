package me.ThaH3lper.com.Listener;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;
import me.ThaH3lper.com.SaveLoad.LoadSetup;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MobSkill implements Listener{

	@EventHandler(priority = EventPriority.HIGH)
	public void MobSkillEvent(EntityDamageByEntityEvent e)
	{
		if(!(e.getEntity() instanceof LivingEntity))
			return;
		LivingEntity l = (LivingEntity) e.getEntity();
		if(!EpicBoss.plugin.allMobs.contains(l.getUniqueId()))
			return;
		EpicMobs em = MobCommon.getEpicMob(l);
		showHealth(l, em);
		
		if(e.getDamager() instanceof Player)	{
			if(em.maxCombatDistance > 0)	{
				if(l.getLocation().distanceSquared(e.getDamager().getLocation()) > em.maxCombatDistance*em.maxCombatDistance)	{
					e.setCancelled(true);
					return;
				}
			}
			
			SkillHandler.ExecuteSkills(em.skills, l, (Player) e.getDamager());
			return;
		} else	{
			if(e.getDamager() instanceof Projectile)	{
				if(em.maxCombatDistance > 0)	{
					if(l.getLocation().distanceSquared(((Projectile)e.getDamager()).getShooter().getLocation()) > em.maxCombatDistance*em.maxCombatDistance)	{
						e.setCancelled(true);
						return;
					}
				}
				if(((Projectile)e.getDamager()).getShooter() instanceof Player)	{
					SkillHandler.ExecuteSkills(em.skills, l, (Player) ((Projectile)e.getDamager()).getShooter());
					return;
				}
			}
			SkillHandler.ExecuteSkills(em.skills, l, null);
		}	
	}
	
	public void showHealth(LivingEntity l, EpicMobs em)
	{
		if(!em.showhp)
			return;
		int per = ((int)((l.getHealth()/l.getMaxHealth())*10))+1;
		if(per >= 10)
			return;
		if(!SkillHandler.hasUsed("percentage" + per, l))
		{
			MobCommon.setMeta(l, "percentage" + per, "percentage" + per);
			
			String string = LoadSetup.ShowHealth;
			string = string.replace("$percentage", per + "0");
			string = string.replace("$boss", l.getCustomName() + "");
			
			for(Player p : SkillHandler.getRadious(l, LoadSetup.ShowHealthRadius))
				p.sendMessage(string);
		}
		
		
	}
}
