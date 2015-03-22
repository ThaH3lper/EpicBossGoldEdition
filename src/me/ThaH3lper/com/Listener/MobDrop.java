package me.ThaH3lper.com.Listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossDeathEvent;
import me.ThaH3lper.com.Drops.DropHandler;
import me.ThaH3lper.com.Drops.EpicNormal;
import me.ThaH3lper.com.Drops.Fair.FairDrops;
import me.ThaH3lper.com.Drops.Fair.FairPlayer;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.SaveLoad.LoadSetup;
import me.ThaH3lper.com.Mobs.MobCommon;
import me.ThaH3lper.com.Skills.SkillHandler;
import me.ThaH3lper.com.Timer.Timer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
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
			
			if(em.fair && DropHandler.getFairDrops(l) != null)
			{
				FairDrops fd = DropHandler.getFairDrops(l);
				List<FairPlayer> list = sort(fd.players);
				fd.Shout(EpicBoss.plugin.menu);
				fd.Shout(ChatColor.translateAlternateColorCodes('&', em.Display) + ChatColor.GREEN + " has Died! List off all Players involved:");
				for(FairPlayer fp : list)
				{
					fd.Shout(ChatColor.LIGHT_PURPLE + fp.player.getName() + ChatColor.GRAY + ": " + ChatColor.GOLD + "" + ChatColor.ITALIC + ((int)fp.damage)+ "dmg");
					int i = list.indexOf(fp);
					if(fd.loot.size() > i)
					{
						EpicNormal en = fd.loot.get(i);
						if(en != null)
						{
							DropHandler.dropPlayer(en, fp.player);
						}
					}
					else if(fd.rest != null)
					{
						EpicNormal en = fd.rest;
						if(en != null)
						{
							DropHandler.dropPlayer(en, fp.player);
						}	
					}
				}
				EpicBoss.plugin.listFair.remove(fd);
			}
			else if(!em.fair)
			{
				List<ItemStack> loot = new ArrayList<>();
				for(String s : em.loot)
				{
					EpicNormal en = DropHandler.getEpicNormal(s);
					if(en != null)
					{
						BossDeathEvent event = new BossDeathEvent(l, l.getKiller(), en.getDrops(), en.getExp());
						

						if (LoadSetup.lootCompatibility)	{
                             for (ItemStack iS : event.getDrops()){
                                 loot.add(iS);
                             }
                         } else {
                             Bukkit.getServer().getPluginManager().callEvent(event);
                             DropHandler.Drop(event.getLivingEntity().getLocation(), event.getExp(), event.getDrops());
                         }
					}
				}
				if (LoadSetup.lootCompatibility)	{
	                 for (ItemStack iS : loot){
	                     e.getDrops().add(iS);
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
