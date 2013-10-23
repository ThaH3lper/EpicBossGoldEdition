package me.ThaH3lper.com.Mobs;

import java.util.List;
import java.util.Random;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Drops.DropHandler;
import me.ThaH3lper.com.Drops.EpicItemStack;
import me.ThaH3lper.com.Drops.EpicNormal;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

public class MobCommon {

	public static Random r = new Random();
	public static EpicMobs getEpicMob(String s)
	{
		for(EpicMobs em : EpicBoss.plugin.listMobs)
		{
			if(em.cmdName.equals(s))
				return em;
		}
		return null;
	}
	public static EpicMobsList getEpicMobList(String s)
	{
		for(EpicMobsList el : EpicBoss.plugin.listMobslist)
		{
			if(el.cmdName.equals(s))
				return el;
		}
		return null;
	}
	public static EpicMobs getEpicMob(LivingEntity l)
	{
		List<MetadataValue> list = l.getMetadata("cmdname");
		for(EpicMobs em : EpicBoss.plugin.listMobs)
		{
			for(MetadataValue mv : list)
			{
				if(mv.asString().equals(em.cmdName))
					return em;
			}
		}
		return null;
	}
	public static LivingEntity setMeta(LivingEntity l, String s, String key)
	{
		l.setMetadata(key, new FixedMetadataValue(EpicBoss.plugin, s));
		return l;
	}
	
	
	public static void setEquipment(LivingEntity l, EpicMobs em)
	{
		EpicItemStack helmet = null, chest = null, leggings = null, boots = null, hand = null;
		
		EntityEquipment ee = l.getEquipment();
		ee.setItemInHandDropChance(0);
		ee.setHelmetDropChance(0);
		ee.setChestplateDropChance(0);
		ee.setLeggingsDropChance(0);
		ee.setBootsDropChance(0);
		
		for(String s : em.equipment)
		{
			String prefix = "";
			EpicNormal en;
			if(s.contains(":"))
			{
				String[] split = s.split(":");
				en = DropHandler.getEpicNormal(split[0]);
				prefix = split[1];
			}
			else
				en = DropHandler.getEpicNormal(s);
			for(EpicItemStack is : en.list)
			{
				if(is.slot != 5)
				{
					if(r.nextFloat()<= is.chance)
					{
						if(is.slot == 0)
							if(compair(is, hand))
							{
								hand = is;
								if(prefix.equals("!"))
									ee.setItemInHandDropChance(1f);
							}
						if(is.slot == 1)
							if(compair(is, boots))
							{
								boots = is;
								if(prefix.equals("!"))
									ee.setBootsDropChance(1f);
							}
						if(is.slot == 2)
							if(compair(is, leggings))
							{
								leggings = is;
								if(prefix.equals("!"))
									ee.setLeggingsDropChance(1f);
							}
						if(is.slot == 3)
							if(compair(is, chest))
							{
								chest = is;
								if(prefix.equals("!"))
									ee.setChestplateDropChance(1f);
							}
						if(is.slot == 4)
							if(compair(is, helmet))
							{
								helmet = is;
								if(prefix.equals("!"))
									ee.setHelmetDropChance(1f);
							}
					}
				}
			}
		}
		if(hand != null)
			ee.setItemInHand(hand.stack);
		if(helmet != null)
			ee.setHelmet(helmet.stack);
		if(chest != null)
			ee.setChestplate(chest.stack);
		if(leggings != null)
			ee.setLeggings(leggings.stack);
		if(boots != null)
			ee.setBoots(boots.stack);			
	}
	public static boolean compair(EpicItemStack stack, EpicItemStack old)
	{
		if(old == null)
			return true;
		else
		{
			if(old.chance >= stack.chance)
				return true;
		}
		return false;
	}
}
