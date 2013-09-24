package me.ThaH3lper.com.Drops;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.inventory.ItemStack;

public class EpicNormal {
	
	Random r = new Random();
	public String cmdName, file;
	public List<EpicItemStack> list = new ArrayList<EpicItemStack>();
	
	public EpicNormal(List<String> strings, String name, String file)
	{
		for(String s : strings)
		{
			list.add(new EpicItemStack(s));
		}
		this.cmdName = name;
		this.file = file;
	}
	
	public List<ItemStack> getDrops()
	{
		List<ItemStack> stack = new ArrayList<ItemStack>();
		for(EpicItemStack is : list)
		{
			if(r.nextFloat()<= is.chance)
			{
				stack.add(is.stack);
			}
		}
		return stack;
	}
}
