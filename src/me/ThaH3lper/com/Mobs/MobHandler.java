package me.ThaH3lper.com.Mobs;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Libs.MobAttribute;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Ocelot.Type;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Wolf;

public class MobHandler {

	public static LivingEntity SpawnMob(String cmdName, Location l)
	{
		EpicMobs em = MobCommon.getEpicMob(cmdName);
		if(em != null)
			return SpawnEpicMob(em, l);
		else
		{
			EpicMobsList el = MobCommon.getEpicMobList(cmdName);
			if(el != null)
			{
				return SpawnEpicMobList(el, l);
			}
		}
		return null;
	}
	
	public static LivingEntity SpawnEpicMobList(EpicMobsList el, Location loc)
	{
		String[] part = el.bosslist.split(",");
		for(String mob : part)
		{
			if(MobCommon.getEpicMob(mob) == null)
				return null;
		}
		LivingEntity l1 = SpawnEpicMob(MobCommon.getEpicMob(part[0]), loc);
		LivingEntity orginal = l1;
		for(int i = 1; i<part.length; i++)
		{
			LivingEntity l2 = SpawnEpicMob(MobCommon.getEpicMob(part[i]), loc);
			l1.setPassenger(l2);
			l1 = l2;
		}
		return orginal;
	}
	public static LivingEntity SpawnEpicMob(EpicMobs em, Location loc)
	{
		LivingEntity l = AllMobs.spawnMob(em.Mobtype, loc);
		l = setDisplay(em, l);
		l = MobCommon.setMeta(l, em.cmdName, "cmdname");
		
		if(!em.despawn)
			l.setRemoveWhenFarAway(false);
		
		//Set Size of slimes and MagmaCube
		if(l instanceof Slime && em.size != 0)
			((Slime) l).setSize(em.size);		
		if(l instanceof MagmaCube && em.size != 0)
			((MagmaCube) l).setSize(em.size);
		
		//set color of wolf and sheep
		if(l instanceof Wolf || l instanceof Sheep)
			l = setColor(l, em);
		
		//set osolot type
		if(l instanceof Ocelot)
			l = setOcolot(l, em);
		
		//set horse type
		if(l instanceof Horse)
			l = setHorse(l, em);
		
		//setEquipment
		MobCommon.setEquipment(l, em);
		
		MobAttribute.setAttackDamage(l, em.damage);	
		
		MobAttribute.setMaxHealth(l, em.health);
		
		if(em.speed != 0)
		{
			if(em.follow == -1)
				MobAttribute.setMobSpeed(l, 0);
			else
				MobAttribute.setMobSpeed(l, em.speed);
		}
		
		if(em.follow != 0)
		{
			if(em.follow == -1)
				MobAttribute.setFollowRange(l, 0);
			else
				MobAttribute.setFollowRange(l, em.follow);
		}
		
		MobAttribute.setKnockBackResistance(l, em.knock);
		
		EpicBoss.plugin.allMobs.add(l.getUniqueId());
		SkillHandler.ExecuteSkills(em.skills, l, null);
		return l;		
	}
	
	public static LivingEntity setDisplay(EpicMobs em, LivingEntity l)
	{
		String s = em.Display;
		s = ChatColor.translateAlternateColorCodes('&', s);
		l.setCustomName(s);
		l.setCustomNameVisible(true);
		return l;
	}
	
	public static LivingEntity setColor(LivingEntity l, EpicMobs em)
	{
		if(l instanceof Wolf)
		{
			Wolf e = (Wolf) l;
			e.setCollarColor(DyeColor.getByDyeData((byte) em.color));
			return l;
		}
		else if(l instanceof Sheep)
		{
			Sheep e = (Sheep) l;
			e.setColor(DyeColor.getByDyeData((byte) em.color));
			return l;
		}
		return null;
	}
	public static LivingEntity setOcolot(LivingEntity l, EpicMobs em)
	{
		Ocelot e = (Ocelot) l;
		if(em.oso != null)
			e.setCatType(Type.valueOf(em.oso));
		return l;
	}
	public static LivingEntity setHorse(LivingEntity l, EpicMobs em)
	{
		Horse e = (Horse) l;
		if(em.horseStyle != null)
			e.setStyle(Style.valueOf(em.horseStyle));
		if(em.horseType != null)
			e.setVariant(Variant.valueOf(em.horseType));
		return l;
	}
}
