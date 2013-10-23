package me.ThaH3lper.com.API;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Mobs.EpicMobs;
import me.ThaH3lper.com.Mobs.MobCommon;

import org.bukkit.entity.LivingEntity;

public class EpicBossAPI {
	public static boolean isBoss(LivingEntity l)
	{
		if(EpicBoss.plugin.allMobs.contains(l.getUniqueId()))
		{
			return true;
		}
		return false;
	}
	
	public static String getBossName(LivingEntity l)
	{
		EpicMobs em = MobCommon.getEpicMob(l);
		return em.cmdName;
	}
	
	public static String getBossDisplayName(LivingEntity l)
	{
		EpicMobs em = MobCommon.getEpicMob(l);
		return em.Display;
	}
	
	public static double getMaxHealth(LivingEntity l)
	{
		return l.getMaxHealth();
	}
	
	public static double getHealth(LivingEntity l)
	{
		return l.getHealth();
	}
	
	public static boolean isShowingHp(LivingEntity l)
	{
		EpicMobs em = MobCommon.getEpicMob(l);
		return em.showhp;
	}
	public static EpicMobs getEpicBoss(LivingEntity l)
	{
		EpicMobs em = MobCommon.getEpicMob(l);
		return em;
	}
}
