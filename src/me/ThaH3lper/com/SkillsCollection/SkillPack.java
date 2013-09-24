package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Libs.FireWorkEffect;
import me.ThaH3lper.com.Skills.EpicSkill;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillPack {
	
	//- pack name hp= chance
	static FireWorkEffect effect = new FireWorkEffect();

	
	public static void ExecutePack(LivingEntity l, String skill, Player p)
	{
		String[] base = skill.split(" ");
		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{
				EpicSkill es = getPack(base[1]);
				if(es != null)
				{
					SkillHandler.ExecuteSkills(es.skills, l, p);
				}
			}
		}
		
	}
	public static EpicSkill getPack(String s)
	{
		for(EpicSkill es : EpicBoss.plugin.listSkills)
		{
			if(es.cmdName.equals(s))
				return es;
		}
		return null;
	}
}
