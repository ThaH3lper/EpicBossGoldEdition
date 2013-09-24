package me.ThaH3lper.com.Location;

import me.ThaH3lper.com.EpicBoss;

public class LocationHandler {

	public static EpicLocation getEpicLocation(String s)
	{
		for(EpicLocation el : EpicBoss.plugin.listLoc)
		{
			if(s.equals(el.name))
				return el;
		}
		return null;
	}
}
