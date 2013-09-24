package me.ThaH3lper.com.Skills;

import java.util.List;

public class EpicSkill {

	public List<String> skills;
	public String cmdName, file; 
	
	public EpicSkill(String cmdName, String file, List<String> skills)
	{
		this.cmdName = cmdName;
		this.file = file;
		this.skills = skills;
	}
}
