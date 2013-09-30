package me.ThaH3lper.com.Skills;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Mobs.MobCommon;
import me.ThaH3lper.com.SkillsCollection.SkillBossFirework;
import me.ThaH3lper.com.SkillsCollection.SkillCommand;
import me.ThaH3lper.com.SkillsCollection.SkillHeal;
import me.ThaH3lper.com.SkillsCollection.SkillLightning;
import me.ThaH3lper.com.SkillsCollection.SkillMsg;
import me.ThaH3lper.com.SkillsCollection.SkillPack;
import me.ThaH3lper.com.SkillsCollection.SkillPlayerFirework;
import me.ThaH3lper.com.SkillsCollection.SkillPotion;
import me.ThaH3lper.com.SkillsCollection.SkillPotionBoss;
import me.ThaH3lper.com.SkillsCollection.SkillRadiousFirework;
import me.ThaH3lper.com.SkillsCollection.SkillRadiusCommand;
import me.ThaH3lper.com.SkillsCollection.SkillSwarm;
import me.ThaH3lper.com.SkillsCollection.SkillThrow;

import org.bukkit.Bukkit;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
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
	
	public static void ExecutePackSkills(List<String> list, LivingEntity l, Player p)
	{
		List<String> DelayedSkills = new ArrayList<String>();
		boolean delayrest = false;
		int delayamount = 0;
		
		for(String line : list)
		{
			if(delayrest == false)	{
				String[] split = line.split(" ");
				if(split[0].equals("delay"))	{
					delayrest = true;
					delayamount = Integer.parseInt(split[1]);
					continue;
				} else	{
					ExecuteSkill(l, line, p);
				}				
			} else	{
				DelayedSkills.add(line);
			}
		}
		
		if(delayrest == true)	{
			DelayedSkill ds = new DelayedSkill(DelayedSkills, l, p);
			Bukkit.getScheduler().scheduleSyncDelayedTask(EpicBoss.plugin, ds, delayamount);
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
			if((split[0].equals("radiousfirework")) || (split[0].equals("radiusfirework")))
				SkillRadiousFirework.ExecuteRadiousFirework(l, skill);
			if(split[0].equals("pack"))
				SkillPack.ExecutePack(l, skill, p);
			if(split[0].equals("heal"))
				SkillHeal.ExecuteHeal(l, skill, p);
			if(split[0].equals("cmd"))
				SkillCommand.ExecuteCommand(l, skill, p);
			if((split[0].equals("radiouscmd")) || (split[0].equals("radiuscmd")))
				SkillRadiusCommand.ExecuteCommand(l, skill);
			if(split[0].equals("potion"))
				SkillPotion.ExecutePotion(l, skill, p);
			if(split[0].equals("potionboss"))
				SkillPotionBoss.ExecutePotionBoss(l, skill, p);
			if(split[0].equals("throw"))
				SkillThrow.ExecuteThrow(l, skill, p);
			if(split[0].equals("lightning"))
				SkillLightning.ExecuteLightning(l, skill, p);
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
		if(health.contains(">"))
		{
			health = health.replace(">", "");
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
		else if(health.contains("<"))
		{
			health = health.replace("<", "");
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
	
	private static class DelayedSkill implements Runnable { 
		private List<String> list;
		private LivingEntity boss;
		private Player player;
		private boolean cancelled;
		
		public DelayedSkill(List<String> list, LivingEntity l, Player p)	{
			this.list = list;
			this.boss = l;
			this.player = p;
			this.cancelled = false;
		}
		
		public void cancel() {
			this.list = null;
			this.cancelled = true;
		}
		
		@Override
        public void run() {
			if (!cancelled) {
				if (this.boss.isValid()) {
					if(this.player.isValid())	{
						ExecutePackSkills(this.list, this.boss, this.player);
						return;
					} else	{
						if(this.boss instanceof Creature)	{
							if(((Creature) this.boss).getTarget() instanceof Player)	{
								this.player = (Player)((Creature)this.boss).getTarget();
								ExecutePackSkills(this.list, this.boss, this.player);
								return;
							}
						} 
						List<Entity> list = this.boss.getNearbyEntities(16, 8, 16);
						
						for(Entity e : list)	{
							if(e instanceof Player)	{
								if(this.boss instanceof Creature) ((Creature)this.boss).setTarget((LivingEntity)e);
								ExecutePackSkills(this.list, this.boss, (Player)e);
								return;
							}
						}
						this.cancel();
					}
				}
			}
		}
	}
}
