package com.treason.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.treason.TreasonGame;
import com.treason.ui.GameplayScreen;

public class MainMenuScreen extends AbstractScreen {
	//final Label label;
	TextureRegion title;
	SpriteBatch batch;
	float time = 0;
	
	public MainMenuScreen(final TreasonGame treasonGame) {
		
		super(treasonGame);
		//final Button exitButton = new TextButton("Exit", skin);
		//final Button newButton = new TextButton("Create New...", skin);
//		newButton.addListener(new ClickListener() {
//			@Override public void clicked(InputEvent event, float x, float y) {
//				game.setScreen(new NewCharacterScreen(game));
//			}
//		});
//		exitButton.addListener(new ClickListener() {
//			@Override public void clicked(InputEvent event, float x, float y) {
//				Gdx.app.exit();
//			}
//		});
//		Window window = new Window("Characters", skin);
//		window.add(newButton);
//		window.row();
////		for(final Being being : SaveManager.load()){
////			final Button beingButton = new TextButton(being.getName(), skin);
////			beingButton.addListener(new ClickListener() {
////				@Override public void clicked(InputEvent event, float x, float y) {
////					game.setScreen(new OverworldScreen(game, new Being(being.getName(), being.getMaxPower())));
////				}
////			});
////			window.add(beingButton);
////			window.row();
////		}
//		window.add(exitButton);
//		window.pack();
//		window.setX(Gdx.graphics.getWidth()/2 - window.getWidth()/2);
//		window.setY(Gdx.graphics.getHeight()/2 - window.getHeight()/2);
//		stage.addActor(window);
		
		
	}
	
	@Override
	public void show () {
		title = new TextureRegion(new Texture(Gdx.files.internal("data/textures/title.png")), 0, 0, 1280, 720);
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 1280, 720);
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(title, 0, 0);
		batch.end();

		time += delta;
		if (time > 1) {
			if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
				game.setScreen(new GameplayScreen(game));
			}
		}
	}

	@Override
	public void hide () {
		Gdx.app.debug("Treason", "dispose main menu");
		batch.dispose();
		title.getTexture().dispose();
	}
}