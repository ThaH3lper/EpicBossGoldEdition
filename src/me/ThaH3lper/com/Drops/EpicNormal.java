package me.ThaH3lper.com.Drops;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.inventory.ItemStack;

public class EpicNormal {
	
	Random r = new Random();
	public String cmdName, file;
	public List<EpicItemStack> list = new ArrayList<EpicItemStack>();
	int Exp = 0;
	
	public EpicNormal(List<String> strings, String name, String file)
	{
		for(String s : strings)
		{
			if(s.contains("exp"))
			{
				String[] split = s.split(" ");
				Exp = Integer.parseInt(split[1]);
			}
			else
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
	public int getExp()
	{
		return Exp;
	}
}
