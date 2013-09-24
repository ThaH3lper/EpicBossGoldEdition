package me.ThaH3lper.com.Drops;

import me.ThaH3lper.com.Items.EpicItems;
import me.ThaH3lper.com.Items.ItemHandler;

import org.bukkit.inventory.ItemStack;

public class EpicItemStack {

	public ItemStack stack;
	public float chance;
	public int slot = 5;
	
	public EpicItemStack(ItemStack stack, float chance)
	{
		this.stack = stack;
		this.chance = chance;
	}
	
	public EpicItemStack(String s)
	{
		String[] part = s.split(" ");
		this.chance = Float.parseFloat(part[1]);
		
		if(!part[0].contains(":"))
		{
			EpicItems ei = ItemHandler.getEpicItem(part[0]);
			if(ei != null)
				this.stack = ei.getItemStack();
		}
		else
		{
			String[] split = part[0].split(":");
			if(split.length == 2)
			{
				EpicItems ei = ItemHandler.getEpicItem(split[0]);
				if(ei != null)
					this.stack = ei.getItemStack();
				slot = Integer.parseInt(split[1]);
			}
			else
			{
				int id = Integer.parseInt(split[0]);
				short data = Short.parseShort(split[1]);
				int amount = Integer.parseInt(split[2]);
				if(split.length == 4)
					slot = Integer.parseInt(split[3]);
				this.stack = new ItemStack(id, amount, data);
			}
		}
	}
}
