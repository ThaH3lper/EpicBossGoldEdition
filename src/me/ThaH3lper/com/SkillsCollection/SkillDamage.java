package me.ThaH3lper.com.SkillsCollection;

import java.lang.reflect.Array;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class SkillDamage {
	//- damage radius:damage:<ignoresarmor(true/false)> =HP Chance
	
	public static void ExecuteDamage(LivingEntity l, String skill, Player player)
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
				double damage = Double.parseDouble(data[1]);
				boolean ignorearmor = (Array.getLength(data) > 2) ? Boolean.parseBoolean(data[2]) : false;

				if(radius > 0)
				{
					for(Player p : SkillHandler.getRadious(l, radius))
					{
						DoDamage(l, p, damage, ignorearmor);
					}
				} else	{
					if(player == null) return;
					DoDamage(l, player, damage, ignorearmor);
				}
				
			}
		}
		
	}

	private static void DoDamage(LivingEntity l, Player p, double damage, boolean ignorearmor)	{
		if(p.isDead()) return;
		
		EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(l, p, DamageCause.ENTITY_ATTACK, damage);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if (event.isCancelled()) {
                return;
        }
        double edamage = event.getDamage();
		
		if(ignorearmor == true)	{
			if((p.getHealth() - edamage) < 1)	{
				p.setLastDamageCause(event);
				p.setHealth(0);
				p.damage(1);
			} else	{
				p.setHealth(p.getHealth() - edamage);
				p.playEffect(EntityEffect.HURT);
			}
		} else	{
			p.damage(edamage);
			p.setLastDamageCause(event);
		}
	}
}
