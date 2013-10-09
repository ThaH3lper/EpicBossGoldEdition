package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.Material;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SkillShootPotion {
	
	// Shoots a splash potion
	// shootpotion type:duration:level:velocity
		
	public static void ExecuteShoot(LivingEntity l, String skill, Player player)
	{
		if(player == null) return;
		String[] base = skill.split(" ");
		String[] data = base[1].split(":");
		float chance = Float.parseFloat(base[base.length-1]);
		if(EpicBoss.r.nextFloat() < chance)
		{
			if(SkillHandler.CheckHealth(base[base.length-2], l, skill))
			{	
            	String pType = data[0];
            	float pDuration = Float.parseFloat(data[1]);
            	int pLevel = Integer.parseInt(data[2]) - 1;
				float velocity = Float.parseFloat(data[3]);
            		            	
            	ItemStack potion = new ItemStack(Material.POTION);
            	PotionMeta pm = (PotionMeta) potion.getItemMeta();
            	pm.addCustomEffect(new PotionEffect(PotionEffectType.getByName(pType), (int) (pDuration * 20), pLevel), true);
            	potion.setItemMeta(pm);
            	
            	Projectile projectile = l.launchProjectile(ThrownPotion.class);
            	((ThrownPotion)projectile).setItem(potion);

                projectile.setVelocity(l.getLocation().getDirection().multiply(velocity));
				projectile.setBounce(false);
				projectile.setShooter(l);
			}
		}
		
	}
}
