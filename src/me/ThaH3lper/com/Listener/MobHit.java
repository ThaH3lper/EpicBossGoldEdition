package me.ThaH3lper.com.Listener;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Drops.DropHandler;
import me.ThaH3lper.com.Drops.Fair.FairDrops;
import me.ThaH3lper.com.Drops.Fair.FairPlayer;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MobHit implements Listener{

	@EventHandler(priority = EventPriority.HIGH)
	public void MobDamageEvent(EntityDamageByEntityEvent e)
	{
		Entity entity = e.getEntity();
		Entity damager = e.getDamager();
		if(!(entity instanceof LivingEntity))
			return;
		if(!(damager instanceof Player))
			return;
		LivingEntity l = (LivingEntity) entity;
		if(!(EpicBoss.plugin.allMobs.contains(l.getUniqueId())))
			return;
		
		Player p = (Player) damager;
		FairDrops fd = DropHandler.getFairDrops(l);
		if(fd != null)
		{
			FairPlayer fp = DropHandler.getFairPlayer(fd, p);
			if(fp != null)
			{
				if(l.getNoDamageTicks() < 10)
				{
					fp.damage += e.getDamage();
				}
			}
			else
				fd.players.add(new FairPlayer(p, e.getDamage()));
		}
		else
		{
			EpicMobs em = MobCommon.getEpicMob(l);
			if(em.fair)
			{
				EpicBoss.plugin.listFair.add(new FairDrops(l, DropHandler.getFairDropList(em), DropHandler.getEpicNormalRest(em)));
			}
		}
	}
}
