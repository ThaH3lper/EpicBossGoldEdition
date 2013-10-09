package me.ThaH3lper.com.Listener;

import java.util.Collections;
import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Drops.DropHandler;
import me.ThaH3lper.com.Drops.EpicNormal;
import me.ThaH3lper.com.Drops.TempPlayer;
import me.ThaH3lper.com.Drops.Fair.FairDrops;
import me.ThaH3lper.com.Drops.Fair.FairPlayer;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;
import me.ThaH3lper.com.Skills.SkillHandler;
import me.ThaH3lper.com.Timer.Timer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class MobDrop implements Listener{

	@EventHandler(priority = EventPriority.HIGH)
	public void MobDeathEvent(EntityDeathEvent e)
	{
		LivingEntity l = e.getEntity();
		if(EpicBoss.plugin.allMobs.contains(l.getUniqueId()))
		{
			removeTimer(l);
			e.getDrops().clear();
			EpicMobs em = MobCommon.getEpicMob(l);
			
			SkillHandler.ExecuteSkills(em.skills, l, getKiller(e));
			
			if(em.fair)
			{
				FairDrops fd = DropHandler.getFairDrops(l);
				List<FairPlayer> list = sort(fd.players);
				fd.Shout(EpicBoss.plugin.menu);
				fd.Shout(ChatColor.translateAlternateColorCodes('&', em.Display) + ChatColor.GREEN + " has Died! List off all Players involved:");
				for(FairPlayer fp : list)
				{
					fd.Shout(ChatColor.LIGHT_PURPLE + fp.player.getName() + ChatColor.GRAY + ": " + ChatColor.GOLD + "" + ChatColor.ITALIC + ((int)fp.damage)+ "dmg");
					TempPlayer tp = DropHandler.getTempPlayer(fp.player);
					if(tp == null)
					{
						tp = new TempPlayer(fp.player);
						EpicBoss.plugin.listTempPlayer.add(tp);
					}
					tp.items.clear();
					int i = list.indexOf(fp);
					if(fd.loot.size() > i)
					{
						EpicNormal en = fd.loot.get(i);
						if(en != null)
						{
							for(ItemStack is : en.getDrops())
							{
								Location loc = e.getEntity().getLocation();
								//event
								Item item = (Item)loc.getWorld().dropItemNaturally(loc, is);
								EpicBoss.plugin.fairItems.add(item);
								tp.items.add(item);
							}
						}
					}
					if(fd.rest != null)
					{
						EpicNormal en = fd.rest;
						for(ItemStack is : en.getDrops())
						{
							Location loc = e.getEntity().getLocation();
							//event
							Item item = (Item)loc.getWorld().dropItemNaturally(loc, is);
							EpicBoss.plugin.fairItems.add(item);
							tp.items.add(item);
						}
					
					}
					EpicBoss.plugin.listFair.remove(fd);
				}					
			}
			else
			{
				for(String s : em.loot)
				{
					EpicNormal en = DropHandler.getEpicNormal(s);
					if(en != null)
					{
						List<ItemStack> drops = en.getDrops();
						Location loc = e.getEntity().getLocation();
						//event
						DropHandler.Drop(loc, en, drops);
					}
				}
			}
			
		}
	}
	
	public static List<FairPlayer> sort(List<FairPlayer> orginal)
	{
		  int lenD = orginal.size();
		
		  for(int i = 0;i<lenD-1;i++)
		  {
			  for(int k = i;k<lenD-1;k++)
			  {
				  if(orginal.get(k).damage < orginal.get(k+1).damage)
				  {
					  Collections.swap(orginal, k, k+1);
					  Bukkit.broadcastMessage(orginal.get(k).damage + " : " + orginal.get(k+1).damage);
				  }
			  }
		  }
		  return orginal;
	}
	
	public static void removeTimer(LivingEntity l)
	{
		for(Timer t : EpicBoss.plugin.allTimers)
		{
			if(t.mobs.contains(l.getUniqueId()))
				t.mobs.remove(l.getUniqueId());
		}
	}
	
	public static Player getKiller(EntityDeathEvent event) {
	    EntityDamageEvent entityDamageEvent = event.getEntity().getLastDamageCause();
	    if (entityDamageEvent != null && !entityDamageEvent.isCancelled() && (entityDamageEvent instanceof EntityDamageByEntityEvent)) {
	        Entity damager = ((EntityDamageByEntityEvent) entityDamageEvent).getDamager();
	 
	        if (damager instanceof Projectile) {
	            LivingEntity shooter = ((Projectile) damager).getShooter();
	            if (shooter != null)
	            {
	            	if(shooter instanceof Player)
	            		return (Player) shooter;
	            }
	        }
	        if(damager instanceof Player)
	        	return (Player) damager;
	    }
	    return null;
	}
}
