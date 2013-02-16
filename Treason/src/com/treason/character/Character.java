package com.treason.character;

import com.badlogic.gdx.math.Vector2;

public class Character {
	public Vector2 pos;
	public boolean isSelected;
	public Vector2 destination;
	
	public Character(Vector2 pos)
	{
		this.pos = pos;
		this.destination = this.pos;
		this.isSelected = false;
	}
	
	public void SetDestination(Vector2 pos)
	{
		this.destination = pos;
	}
	
	
	public void Update()
	{
		if(this.pos.dst(this.destination) < 32)
		{
			this.pos.x++;
		}
	}
}
