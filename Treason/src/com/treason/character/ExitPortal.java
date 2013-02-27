package com.treason.character;

import com.badlogic.gdx.math.Vector2;

public class ExitPortal 
{
	public Vector2 pos;
	private int numberRequired;
	private int numberAccepted;
	
	public int GetNumberRequired()
	{
		return this.numberRequired;
	}

	public int GetNumberAccepted()
	{
		return this.numberAccepted;
	}
	
	public void IncreaseAccepted()
	{
		this.numberAccepted++;
	}
	
	public ExitPortal(Vector2 pos, int numberRequired)
	{
		this.pos = pos;
		this.numberRequired = numberRequired;
		this.numberAccepted = 0;
	}

}
