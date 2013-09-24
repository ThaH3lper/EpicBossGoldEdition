package me.ThaH3lper.com.Drops.Fair;

import org.bukkit.entity.Player;

public class FairPlayer {

	public Player player;
	public double damage;
	
	public FairPlayer(Player player, double damage)
	{
		this.player = player;
		this.damage = damage;
	}
}
