package me.ThaH3lper.com.Items;

import java.util.List;

import org.bukkit.inventory.ItemStack;

public class EpicItems {
	
	public int id, data, amount;
	public String Display, cmdName, file, color, player;
	public List<String> Lores, Enchants;
	public double speed, damage, knock, health, range;
	
	public EpicItems(String file,String cmdName, int id, int data, int amount, String Display, List<String> Lores, List<String> Enchants, double health, double damage, double knock, double range, double speed, String color, String player)
	{
		this.id = id;
		this.data = data;
		this.amount = amount;
		this.Display = Display;
		this.Lores = Lores;
		this.Enchants = Enchants;
		this.cmdName = cmdName;
		this.speed = speed;
		this.damage = damage;
		this.range = range;
		this.knock = knock;
		this.health = health;
		this.file = file;
		this.color = color;
		this.player = player;
	}
	
	public ItemStack getItemStack()
	{
		return ItemHandler.getItemStack(this);
	}
}
