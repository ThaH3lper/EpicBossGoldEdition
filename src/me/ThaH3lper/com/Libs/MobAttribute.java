package me.ThaH3lper.com.Libs;

import net.minecraft.server.v1_7_R1.AttributeInstance;
import net.minecraft.server.v1_7_R1.EntityInsentient;
import net.minecraft.server.v1_7_R1.GenericAttributes;

import org.bukkit.craftbukkit.v1_7_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class MobAttribute {
	
	  public static void setMaxHealth(Entity e, double health) {
	    AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.a);
	    attributes.setValue(health);
	    LivingEntity l = (LivingEntity) e;
	    l.setHealth(l.getMaxHealth());
	  }

	  public static void setFollowRange(Entity e, double range) {
	    AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.b);
	    if(attributes != null)
	    	attributes.setValue(range);
	  }

	  public static void setKnockBackResistance(Entity e, double knock) {
	    AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.c);
	    if(attributes != null)
	    	attributes.setValue(knock);
	  }

	  public static void setMobSpeed(Entity e, double speed) {
	    AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.d);
	    if(attributes != null)
	    	attributes.setValue(speed);
	  }

	  public static void setAttackDamage(Entity e, double damage) {
	    AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.e);
	    if(attributes != null)
	    	attributes.setValue(damage);
	  }
}
