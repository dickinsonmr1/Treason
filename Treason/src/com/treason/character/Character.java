package com.treason.character;

import com.badlogic.gdx.math.Vector2;

public class Character {
	public Vector2 pos;
	public boolean isSelected;
	public boolean isDraggable;
	public boolean isTalkable;
	public Vector2 destination;
	
	public Character(Vector2 pos)
	{
		this.pos = pos;
		this.destination = this.pos;
		this.isSelected = false;
		this.isDraggable = false;
		this.isTalkable = false;
	}
	
	public void SetDestination(Vector2 pos)
	{
		this.destination = pos;
	}
	
	
	public void Update()
	{
		if(this.pos.dst(this.destination) > 2)
		{
			if(this.pos.x < this.destination.x)
			{
				this.pos.x++;
			}
			if(this.pos.x > this.destination.x)
			{
				this.pos.x--;
			}
			if(this.pos.y < this.destination.y)
			{
				this.pos.y++;
			}
			if(this.pos.y > this.destination.y)
			{
				this.pos.y--;
			}
		}
	}
}
