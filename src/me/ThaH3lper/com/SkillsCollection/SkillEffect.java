package me.ThaH3lper.com.SkillsCollection;

import java.util.logging.Logger;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.API.BossSkillEvent;
import me.ThaH3lper.com.Skills.SkillHandler;
import me.ThaH3lper.com.SkillsCollection.EffectsCollection.EffectSound;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillEffect {
	
	public static Logger logger = Logger.getLogger("Minecraft");
	//- effect boss sound pitch:volume:'sound.sound'
	
	public static void ExecuteEffect(LivingEntity l, String skill, Player player)
	{
		String[] base = skill.split(" ");

		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{
				BossSkillEvent event = new BossSkillEvent(l, skill, null, false);
				Bukkit.getServer().getPluginManager().callEvent(event);
				if(event.isChanceled())
					return;
				
				String location = base[1];
				String effect = base[2];
				String effectdata = base[3];
								
				if(location.equals("boss"))	{
					EffectHandler(l.getLocation(), l, effect, effectdata);
				} else if(location.equals("player"))	{
					EffectHandler(player.getLocation(), l, effect, effectdata);
				}

			}
		}
	}
	
	public static void EffectHandler(Location Loc, LivingEntity l, String effect, String effectdata)	{
		
		if(effect.equals("sound"))	{
			EffectSound.PlaySound(Loc, effectdata);						
		}
	}
}
