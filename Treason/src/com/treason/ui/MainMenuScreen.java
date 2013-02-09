package com.treason.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.treason.TreasonGame;

public class MainMenuScreen extends AbstractScreen<TreasonDesktop> implements Screen {
	final Label label;

	public MainMenuScreen(final TreasonDesktop game) {
		super(game);
		label = new Label("", skin);
		final TextField nameField = new TextField("", skin);
		nameField.setMessageText("<name>");
		final Button backButton = new TextButton("Back", skin);
//		backButton.addListener(new ClickListener() {
//			@Override public void clicked(InputEvent event, float x, float y) {
//				game.setScreen(new CharactersScreen(game));
//			}
//		});
		final Button createButton = new TextButton("Create", skin);
//		createButton.addListener(new ClickListener() {
//			@Override public void clicked(InputEvent event, float x, float y) {
//				game.setScreen(new OverworldScreen(game, new Being(nameField.getText(), Being.CHARACTER_JETPACK_POWER)));
//			}
//		});
		Window window = new Window("Treason", skin);
		window.add(nameField);
		window.row();
		window.add(createButton);
		window.row();
		window.add(backButton);
		window.pack();
		window.add(label);
		window.pack();
		window.setX(Gdx.graphics.getWidth()/2 - window.getWidth()/2);
		window.setY(Gdx.graphics.getHeight()/2 - window.getHeight()/2);
		stage.addActor(window);
	}
}