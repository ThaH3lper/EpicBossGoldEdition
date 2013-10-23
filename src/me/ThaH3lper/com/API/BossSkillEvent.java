package me.ThaH3lper.com.API;


import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BossSkillEvent extends Event {
	
	private String name;
	private Boolean custom, canceled = false;
	private LivingEntity l;
	private String[] skillData;
	private Player p;
	private static final HandlerList handlers = new HandlerList();
	 
	public BossSkillEvent(LivingEntity l, String full, Player p, Boolean custom)
	{
		this.l = l;
		this.custom = custom;
		this.p = p;
		String[] parts = full.split(" ");
		this.name = parts[0];
		this.skillData = parts[1].split(":");
	}
	
	public String getBossName(){
		EpicMobs em = MobCommon.getEpicMob(l);
		return em.cmdName;
	}
	
	public LivingEntity getLivingEntity(){
		return l;
	}
	
	public EpicMobs getEpicMobs(){
		EpicMobs em = MobCommon.getEpicMob(l);
		return em;
	}
		
	public String getSkillName(){
		return name;
	}
	public Player getTargetPlayer(){
		return p;
	}
	
	public boolean isCustomSkill(){
		return custom;
	}
	public void setChanceled(boolean bool){
		this.canceled = bool;
	}
	public boolean isChanceled(){
		return this.canceled;
	}
	
	public String[] getData(){
		return skillData;
	}
	 
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
}
