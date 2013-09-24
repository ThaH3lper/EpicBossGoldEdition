package me.ThaH3lper.com.Drops.Fair;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.Drops.EpicNormal;

import org.bukkit.entity.LivingEntity;

public class FairDrops {
	public List<FairPlayer> players = new ArrayList<FairPlayer>();
	public LivingEntity entity;
	public EpicNormal rest;
	public List<EpicNormal> loot;
	
	public FairDrops(LivingEntity entity, List<EpicNormal> loot, EpicNormal rest)
	{
		this.entity = entity;
		this.loot = loot;
		this.rest = rest;
	}
	
	public void Shout(String msg)
	{
		for(FairPlayer fp : players)
		{
			fp.player.sendMessage(msg);
		}
	}
}
