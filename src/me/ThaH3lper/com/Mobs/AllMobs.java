package me.ThaH3lper.com.Mobs;

import org.bukkit.Location;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Horse;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;

public class AllMobs {
	public static LivingEntity spawnMob(String Mobtype, Location l)
	{
		if(Mobtype.equalsIgnoreCase("zombie"))
		{
			Zombie e = (Zombie)l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
			return e;
		}
		if(Mobtype.equalsIgnoreCase("babyzombie"))
		{
			Zombie e = (Zombie)l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
			e.setBaby(true);
			return e;
		}
		if(Mobtype.equalsIgnoreCase("villagezombie"))
		{
			Zombie e = (Zombie)l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
			e.setVillager(true);
			return e;
		}
		if(Mobtype.equalsIgnoreCase("babyvillagezombie"))
		{
			Zombie e = (Zombie)l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
			e.setBaby(true);
			e.setVillager(true);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("wolf"))
		{
			Wolf e = (Wolf)l.getWorld().spawnEntity(l, EntityType.WOLF);
			e.setBreed(false);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("babywolf"))
		{
			Wolf e = (Wolf)l.getWorld().spawnEntity(l, EntityType.WOLF);
			e.setBreed(false);
			e.setBaby();
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("angrywolf"))
		{
			Wolf e = (Wolf)l.getWorld().spawnEntity(l, EntityType.WOLF);
			e.setAngry(true);
			e.setBreed(false);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("angrybabywolf"))
		{
			Wolf e = (Wolf)l.getWorld().spawnEntity(l, EntityType.WOLF);
			e.setAngry(true);
			e.setBreed(false);
			e.setBaby();
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("wither"))
		{
			Wither e = (Wither)l.getWorld().spawnEntity(l, EntityType.WITHER);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("witch"))
		{
			Witch e = (Witch)l.getWorld().spawnEntity(l, EntityType.WITCH);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("villager"))
		{
			Villager e = (Villager)l.getWorld().spawnEntity(l, EntityType.VILLAGER);
			e.setAdult();
			e.setBreed(false);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("babyvillager"))
		{
			Villager e = (Villager)l.getWorld().spawnEntity(l, EntityType.VILLAGER);
			e.setBaby();
			e.setBreed(false);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("squid"))
		{
			Squid e = (Squid)l.getWorld().spawnEntity(l, EntityType.SQUID);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("spider"))
		{
			Spider e = (Spider)l.getWorld().spawnEntity(l, EntityType.SPIDER);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("snowman"))
		{
			Snowman e = (Snowman)l.getWorld().spawnEntity(l, EntityType.SNOWMAN);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("slime"))
		{
			Slime e = (Slime)l.getWorld().spawnEntity(l, EntityType.SLIME);
			//size
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("skeleton"))
		{
			Skeleton e = (Skeleton)l.getWorld().spawnEntity(l, EntityType.SKELETON);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("witherskeleton"))
		{
			Skeleton e = (Skeleton)l.getWorld().spawnEntity(l, EntityType.SKELETON);
			e.setSkeletonType(SkeletonType.WITHER);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("silverfish"))
		{
			Silverfish e = (Silverfish)l.getWorld().spawnEntity(l, EntityType.SILVERFISH);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("sheep"))
		{
			Sheep e = (Sheep)l.getWorld().spawnEntity(l, EntityType.SHEEP);
			e.setAdult();
			//color
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("babysheep"))
		{
			Sheep e = (Sheep)l.getWorld().spawnEntity(l, EntityType.SHEEP);
			e.setBaby();
			//color
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("pigzombie"))
		{
			PigZombie e = (PigZombie)l.getWorld().spawnEntity(l, EntityType.PIG_ZOMBIE);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("angrypigzombie"))
		{
			PigZombie e = (PigZombie)l.getWorld().spawnEntity(l, EntityType.PIG_ZOMBIE);
			e.setAngry(true);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("babypigzombie"))
		{
			PigZombie e = (PigZombie)l.getWorld().spawnEntity(l, EntityType.PIG_ZOMBIE);
			e.setBaby(true);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("angrybabypigzombie"))
		{
			PigZombie e = (PigZombie)l.getWorld().spawnEntity(l, EntityType.PIG_ZOMBIE);
			e.setAngry(true);
			e.setBaby(true);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("pig"))
		{
			Pig e = (Pig)l.getWorld().spawnEntity(l, EntityType.PIG);
			e.setAdult();
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("babypig"))
		{
			Pig e = (Pig)l.getWorld().spawnEntity(l, EntityType.PIG);
			e.setBaby();
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("ocelot"))
		{
			Ocelot e = (Ocelot)l.getWorld().spawnEntity(l, EntityType.OCELOT);
			e.setAdult();
			//black, red, sieamen, wild
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("babyocelot"))
		{
			Ocelot e = (Ocelot)l.getWorld().spawnEntity(l, EntityType.OCELOT);
			e.setBaby();
			//black, red, sieamen, wild
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("mushroomcow"))
		{
			MushroomCow e = (MushroomCow)l.getWorld().spawnEntity(l, EntityType.MUSHROOM_COW);
			e.setAdult();
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("babymushroomcow"))
		{
			MushroomCow e = (MushroomCow)l.getWorld().spawnEntity(l, EntityType.MUSHROOM_COW);
			e.setBaby();
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("magmacube"))
		{
			MagmaCube e = (MagmaCube)l.getWorld().spawnEntity(l, EntityType.MAGMA_CUBE);
			//size
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("irongolem"))
		{
			IronGolem e = (IronGolem)l.getWorld().spawnEntity(l, EntityType.IRON_GOLEM);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("horse"))
		{
			Horse e = (Horse)l.getWorld().spawnEntity(l, EntityType.HORSE);
			e.setAdult();
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("babyhorse"))
		{
			Horse e = (Horse)l.getWorld().spawnEntity(l, EntityType.HORSE);
			e.setBaby();
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("giant"))
		{
			Giant e = (Giant)l.getWorld().spawnEntity(l, EntityType.GIANT);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("ghast"))
		{
			Ghast e = (Ghast)l.getWorld().spawnEntity(l, EntityType.GHAST);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("enderman"))
		{
			Enderman e = (Enderman)l.getWorld().spawnEntity(l, EntityType.ENDERMAN);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("creeper"))
		{
			Creeper e = (Creeper)l.getWorld().spawnEntity(l, EntityType.CREEPER);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("poweredcreeper"))
		{
			Creeper e = (Creeper)l.getWorld().spawnEntity(l, EntityType.CREEPER);
			e.setPowered(true);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("cow"))
		{
			Cow e = (Cow)l.getWorld().spawnEntity(l, EntityType.COW);
			e.setAdult();
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("babycow"))
		{
			Cow e = (Cow)l.getWorld().spawnEntity(l, EntityType.COW);
			e.setBaby();
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("chicken"))
		{
			Chicken e = (Chicken)l.getWorld().spawnEntity(l, EntityType.CHICKEN);
			e.setAdult();
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("babychicken"))
		{
			Chicken e = (Chicken)l.getWorld().spawnEntity(l, EntityType.CHICKEN);
			e.setBaby();
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("cavespider"))
		{
			CaveSpider e = (CaveSpider)l.getWorld().spawnEntity(l, EntityType.CAVE_SPIDER);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("blaze"))
		{
			Blaze e = (Blaze)l.getWorld().spawnEntity(l, EntityType.BLAZE);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("bat"))
		{
			Bat e = (Bat)l.getWorld().spawnEntity(l, EntityType.BAT);
			return e;
		}
		else if(Mobtype.equalsIgnoreCase("enderdragon"))
		{
			EnderDragon e = (EnderDragon)l.getWorld().spawnEntity(l, EntityType.ENDER_DRAGON);
			return e;
		}
		
		return null;
		
	}

}
