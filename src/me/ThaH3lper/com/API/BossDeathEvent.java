package me.ThaH3lper.com.API;

import java.util.List;

import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class BossDeathEvent extends Event {
	
	private LivingEntity l, killer;
	private List<ItemStack> drops;
	private int exp;
	private static final HandlerList handlers = new HandlerList();
	 
	public BossDeathEvent(LivingEntity l, LivingEntity killer, List<ItemStack> drops, int exp)
	{
		this.exp = exp;
		this.killer = killer;
		this.l = l;
		this.drops = drops;
	}
	
	public LivingEntity getLivingEntity(){
		return l;
	}
	
	public EpicMobs getEpicMobs()
	{
		EpicMobs em = MobCommon.getEpicMob(l);
		return em;
	}
		
	public LivingEntity getKiller(){
		return killer;
	}
	
	public List<ItemStack> getDrops(){
		return drops;
	}
	public void setDrops(List<ItemStack> list){
		this.drops = list;
	}
	
	public int getExp(){
		return exp;
	}
	public void setExp(int amount){
		exp = amount;
	}
	 
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
}
