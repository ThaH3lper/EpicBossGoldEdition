package me.ThaH3lper.com.SaveLoad;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.EpicBoss;

public class SaveLoadHandler {

	public static String getList(String s, File[] list)
	{
		for(File f : list)
		{
			s += f.getName() + ", ";
		}
		return s;
	}
	
	public static List<SaveLoad> getSaveLoad(File[] itemFiles, String s)
	{
		List<SaveLoad> list= new ArrayList<SaveLoad>();
		for(File f: itemFiles)
		{
			list.add(new SaveLoad(EpicBoss.plugin, f.getName(), s));
		}
		return list;
	}
}
