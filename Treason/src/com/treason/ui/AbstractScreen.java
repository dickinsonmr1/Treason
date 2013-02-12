package com.treason.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.treason.TreasonGame;

public class AbstractScreen implements Screen {
	
	//protected Stage stage;
	//protected static Skin skin;
	//protected final T game;
	public TreasonGame game;

	public AbstractScreen(TreasonGame game){
		this.game = game;
//		if(skin == null)
//			skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));
//		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
//		Gdx.input.setInputProcessor(stage);
	}
	
	@Override public void render(float arg0) {
//		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
//		//Gdx.gl10.glClearColor(1, 0, 0, 0);
//		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
//		stage.draw();
	}

	@Override public void resize(int width, int height) {
		//stage.setViewport(width, height, false);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
