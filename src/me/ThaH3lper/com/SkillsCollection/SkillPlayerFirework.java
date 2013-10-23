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
import org.bukkit.entity.Player;

public class SkillPlayerFirework {
	
	//- radiousfirework radious:ball:red:true:true =HP Chance
	// radious: 0 = All!
	//Type: BALL, BALL_LARGE, BURST, CREEPER, STAR
	static FireWorkEffect effect = new FireWorkEffect();

	
	public static void ExecutePlayerFirework(LivingEntity l, String skill, Player p)
	{
		String[] base = skill.split(" ");
		String[] data = base[1].split(":");
		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{
				BossSkillEvent event = new BossSkillEvent(l, skill, p, false);
				Bukkit.getServer().getPluginManager().callEvent(event);
				if(event.isChanceled())
					return;
				
				int radious = Integer.parseInt(data[0]);
				boolean flicker = Boolean.valueOf(data[3]);
				boolean trail = Boolean.valueOf(data[4]);
				String[] c = data[2].split(",");
				Color color = Color.fromRGB(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]));
				Type type = Type.valueOf(data[1]);
				
				if(color != null && type != null)
				{
					if(radious == 0)
					{
						if(p != null)
						{
							try {
								effect.playFirework(p.getWorld(), p.getLocation(), FireworkEffect.builder().with(type).withColor(color).trail(trail).flicker(flicker).build());
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					else
					{
						for(Player pla : SkillHandler.getRadious(l, radious))
						{
							try {
								effect.playFirework(pla.getWorld(), pla.getLocation(), FireworkEffect.builder().with(type).withColor(color).trail(trail).flicker(flicker).build());
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}					
				}
			}
		}
		
	}
}
