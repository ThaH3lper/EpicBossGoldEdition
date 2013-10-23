package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Libs.FireWorkEffect;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.LivingEntity;

public class SkillBossFirework {
	
	//- bossfirework ball:red:true:true =HP Chance
	// radious: 0 = All!
	//Type: BALL, BALL_LARGE, BURST, CREEPER, STAR
	static FireWorkEffect effect = new FireWorkEffect();

	
	public static void ExecuteBossFirework(LivingEntity l, String skill)
	{
		String[] base = skill.split(" ");
		String[] data = base[1].split(":");
		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{
				BossSkillEvent event = new BossSkillEvent(l, skill, null, false);
				Bukkit.getServer().getPluginManager().callEvent(event);
				if(event.isChanceled())
					return;
				
				boolean flicker = Boolean.valueOf(data[2]);
				boolean trail = Boolean.valueOf(data[3]);
				String[] c = data[1].split(",");
				Color color = Color.fromRGB(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]));
				Type type = Type.valueOf(data[0]);
				
				if(color != null && type != null)
				{
					try {
						effect.playFirework(l.getWorld(), l.getLocation(), FireworkEffect.builder().with(type).withColor(color).trail(trail).flicker(flicker).build());
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
			}
		}
		
	}
}
