package me.ThaH3lper.com.Skills;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.Mobs.MobCommon;
import me.ThaH3lper.com.SkillsCollection.SkillBossFirework;
import me.ThaH3lper.com.SkillsCollection.SkillCommand;
import me.ThaH3lper.com.SkillsCollection.SkillHeal;
import me.ThaH3lper.com.SkillsCollection.SkillMsg;
import me.ThaH3lper.com.SkillsCollection.SkillPack;
import me.ThaH3lper.com.SkillsCollection.SkillPlayerFirework;
import me.ThaH3lper.com.SkillsCollection.SkillPotionBoss;
import me.ThaH3lper.com.SkillsCollection.SkillRadiousFirework;
import me.ThaH3lper.com.SkillsCollection.SkillSwarm;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

public class SkillHandler {

	public static void ExecuteSkills(List<String> list, LivingEntity l, Player p)
	{
		for(String line : list)
		{
			ExecuteSkill(l, line, p);
		}
	}
	
	public static void ExecuteSkill(LivingEntity l, String skill, Player p)
	{
		String[] split = skill.split(" ");
		if(split[0] != null)
		{
			if(split[0].equals("msg"))
				SkillMsg.ExecuteMsg(l, skill, p);
			if(split[0].equals("bossfirework"))
				SkillBossFirework.ExecuteBossFirework(l, skill);
			if(split[0].equals("playerfirework"))
				SkillPlayerFirework.ExecutePlayerFirework(l, skill, p);
			if(split[0].equals("radiousfirework"))
				SkillRadiousFirework.ExecuteRadiousFirework(l, skill);
			if(split[0].equals("pack"))
				SkillPack.ExecutePack(l, skill, p);
			if(split[0].equals("heal"))
				SkillHeal.ExecuteHeal(l, skill, p);
			if(split[0].equals("cmd"))
				SkillCommand.ExecuteCommand(l, skill, p);
			if(split[0].equals("potionboss"))
				SkillPotionBoss.ExecutePotionBoss(l, skill, p);
			if(split[0].equals("swarm"))
				SkillSwarm.ExecuteSwarm(l, skill, p);
		}
	}
	
	public static List<Player> getRadious(LivingEntity l, int i)
	{
		List<Player> list = new ArrayList<Player>();
		for(Player p : l.getWorld().getPlayers())
		{
			if(l.getLocation().distance(p.getLocation()) < i)
				list.add(p);
		}
		return list;
	}
	
	public static boolean CheckHealth(String health, LivingEntity l, String full)
	{
		if(health.contains("<"))
		{
			health = health.replace("<", "");
			double hp = Double.parseDouble(health);
			if(l.getHealth() > hp)
				return true;
		}
		else if(health.contains("="))
		{
			health = health.replace("=", "");
			double hp = Double.parseDouble(health);
			if(l.getHealth() <= hp && !hasUsed(full, l))
			{
				MobCommon.setMeta(l, full, full);
				return true;
			}
		}
		else if(health.contains(">"))
		{
			health = health.replace(">", "");
			double hp = Double.parseDouble(health);
			if(l.getHealth() < hp)
				return true;	
		}
		else if(health.contains("-"))
		{
			String[] hps = health.split("-");
			double hp1 = Double.parseDouble(hps[0]);
			double hp2 = Double.parseDouble(hps[1]);
			if(l.getHealth() > hp2 && l.getHealth() < hp1)
				return true;
		}
		return false;
	}
	
	public static boolean hasUsed(String full, LivingEntity l)
	{
		List<MetadataValue> list = l.getMetadata(full);
		for(MetadataValue mv : list)
		{
			if(mv.asString().equals(full))
				return true;
		}
		return false;
	}
}
