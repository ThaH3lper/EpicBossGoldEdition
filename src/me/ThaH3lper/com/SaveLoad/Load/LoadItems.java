package me.ThaH3lper.com.SaveLoad.Load;

import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Items.EpicItems;
import me.ThaH3lper.com.SaveLoad.SaveLoad;

public class LoadItems{

	public static void LoadAllItems()
	{
		for(SaveLoad sl :EpicBoss.plugin.saveItemList)
		{
			for(String s: sl.getCustomConfig().getConfigurationSection("").getKeys(false))
			{
				String cmdName = s;
				
				int Id = sl.getCustomConfig().getInt(s + ".Id");
				int Data = sl.getCustomConfig().getInt(s + ".Data");
				int Amount = sl.getCustomConfig().getInt(s + ".Amount");
				
				String Display = sl.getCustomConfig().getString(s + ".Display");
				List<String> Lores = sl.getCustomConfig().getStringList(s + ".Lores");
				List<String> Enchants = sl.getCustomConfig().getStringList(s + ".Enchants");
				
				double speed = sl.getCustomConfig().getDouble(s + ".Tags.Speed");
				double attack = sl.getCustomConfig().getDouble(s + ".Tags.Attack");
				double health = sl.getCustomConfig().getDouble(s + ".Tags.Health");
				double range = sl.getCustomConfig().getDouble(s + ".Tags.Followrange");
				double knock = sl.getCustomConfig().getDouble(s + ".Tags.KnockbackRes");
				String color = sl.getCustomConfig().getString(s + ".Tags.Color");
				String player = sl.getCustomConfig().getString(s + ".Tags.Player");
				
				EpicBoss.plugin.listItems.add(new EpicItems(sl.thefile.getName(), cmdName, Id, Data, Amount, Display, Lores, Enchants, health, attack, knock, range, speed, color, player));
			}
		}
	}
	
	public static boolean Check(SaveLoad sl, String s)
	{
		if(!sl.getCustomConfig().contains(s + ".Id"))
			return false;
		if(!sl.getCustomConfig().contains(s + ".Data"))
			return false;
		if(!sl.getCustomConfig().contains(s + ".Amount"))
			return false;
		
			return true;			
	}
}
