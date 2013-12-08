package me.ThaH3lper.com.SkillsCollection.EffectsCollection;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R1.CraftWorld;

public class EffectSound {
	
	//- effect boss sound sound.sound:volume:pitch
	
	public static void PlaySound(Location location, String ed)	{
		
		String[] data = ed.split(":");
		
		String sound = data[0];
		float volume = 	(data.length > 1) 	? Float.parseFloat(data[1]) : 1;
		float pitch  =	(data.length > 2) 	? Float.parseFloat(data[2]) : 1;
		
		if (sound.equals("random.wood_click")) {
            sound = "random.wood click";
	    } else if (sound.equals("mob.ghast.affectionate_scream")) {
	            sound = "mob.ghast.affectionate scream";
	    }
		
		((CraftWorld)location.getWorld()).getHandle().makeSound(location.getX(), location.getY(), location.getZ(), sound, volume, pitch);
	}
}
