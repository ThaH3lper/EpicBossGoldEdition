package me.ThaH3lper.com.Items;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Libs.AttributeHandler;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemHandler {

	public static ItemStack getItemStack(EpicItems ei)
	{
		int id = ei.id;
		short data = (short) ei.data;
		int amount = ei.amount;
		
		ItemStack stack = new ItemStack(Material.getMaterial(id), amount, data);
		ItemMeta im = stack.getItemMeta();
		
		if(ei.Lores != null)
			im = setLores(im, ei.Lores);
		
		if(ei.Enchants != null)
			im = setEnchants(im, ei.Enchants);
		
		if(ei.Display != null)
			im = setDisplay(im, ei.Display);
		
		stack.setItemMeta(im);
		
		if(ei.color != null)
			stack = setLeatherColor(stack, ei);
		
		if(ei.player != null)
			stack = setPlayerName(stack, ei);
		
		stack = AttributeHandler.addHealth(stack, ei.health);
		stack = AttributeHandler.addDamage(stack, ei.damage);
		stack = AttributeHandler.addSpeed(stack, ei.speed);
		stack = AttributeHandler.addKnockBackRes(stack, ei.knock);
		stack = AttributeHandler.addFollowRange(stack, ei.range);
		
		return stack;
	}
	
	public static ItemMeta setEnchants(ItemMeta im, List<String> enchants)
	{
		for(String s: enchants)
		{
			if(s.contains(":"))
			{
				String[] part = s.split(":");
				im.addEnchant(Enchantment.getByName(part[0]), Integer.parseInt(part[1]), true);
			}
		}
		return im;
	}
	public static ItemMeta setLores(ItemMeta im, List<String> lores)
	{
		List<String> list = new ArrayList<String>();
		for(String s: lores)
		{
			s = ChatColor.translateAlternateColorCodes('&', s);
			list.add(s);
		}
		im.setLore(list);
		return im;
	}
	
	public static ItemMeta setDisplay(ItemMeta im, String s)
	{

		s = ChatColor.translateAlternateColorCodes('&', s);
		im.setDisplayName(s);
		return im;
	}
	//SetColor, Tag!, Red:Green:Blue
	public static ItemStack setLeatherColor(ItemStack item, EpicItems ei)
	{
		if(item.getType().equals(Material.LEATHER_CHESTPLATE) || item.getType().equals(Material.LEATHER_BOOTS) || item.getType().equals(Material.LEATHER_LEGGINGS) || item.getType().equals(Material.LEATHER_HELMET))
		{
			String[] rgb = ei.color.split(",");
			int r = Integer.parseInt(rgb[0]);
			int g = Integer.parseInt(rgb[1]);
			int b = Integer.parseInt(rgb[2]);
			ItemMeta im = item.getItemMeta();
			LeatherArmorMeta la = (LeatherArmorMeta) im;
			la.setColor(Color.fromRGB(r, g, b));
			item.setItemMeta(la);
		}
		return item;
	}
	public static ItemStack setPlayerName(ItemStack item, EpicItems ei)
	{
		if(item.getType().equals(Material.SKULL) || item.getType().equals(Material.SKULL_ITEM))
		{
			SkullMeta meta = (SkullMeta) item.getItemMeta();
			meta.setOwner(ei.player);
			item.setItemMeta(meta);
		}
		return item;
	}
	public static EpicItems getEpicItem(String s)
	{
		for(EpicItems ei : EpicBoss.plugin.listItems)
		{
			if(ei.cmdName.equals(s))
			{
				return ei;
			}
		}
		return null;
	}
}
