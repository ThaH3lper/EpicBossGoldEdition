package me.ThaH3lper.com.SaveLoad.Load;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Location.EpicLocation;

public class LoadLocation {

	public static void loadAllLocations()
	{
		if(EpicBoss.plugin.savelist.getCustomConfig().contains("Locationlist"))
		{
			List<String> list = EpicBoss.plugin.savelist.getCustomConfig().getStringList("Locationlist");
			for(String s : list)
			{
				EpicBoss.plugin.listLoc.add(new EpicLocation(s));
			}
		}
	}
	
	public static void saveAllLocations()
	{
		List<String> list = new ArrayList<String>();
		for(EpicLocation el: EpicBoss.plugin.listLoc)
		{
			list.add(el.getString());
		}
		EpicBoss.plugin.savelist.getCustomConfig().set("Locationlist", list);
		EpicBoss.plugin.savelist.saveCustomConfig();
	}
}
