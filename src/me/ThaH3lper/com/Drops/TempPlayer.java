package me.ThaH3lper.com.Drops;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class TempPlayer {

	public Player player;
	public List<Item> items = new ArrayList<Item>();
	
	public TempPlayer(Player player)
	{
		this.player= player;
	}
}
