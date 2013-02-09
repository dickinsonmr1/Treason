package com.treason;

import com.badlogic.gdx.ApplicationListener;  
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;  
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.treason.ui.MainMenuScreen;

public class TreasonGame extends Game implements ApplicationListener {
	
    	public SpriteBatch spriteBatch;  
    	public OrthographicCamera camera;  
    
    	@Override public void create () {
			// TODO Auto-generated method stub
			setScreen(new MainMenuScreen(this));
        }

        public void render () {
        }

        public void resize (int width, int height) {
        }

        public void pause () {
        }

        public void resume () {
        }

        public void dispose () {
        }
}