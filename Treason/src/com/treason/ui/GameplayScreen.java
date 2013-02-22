package com.treason.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.treason.TreasonGame;
import com.treason.character.Character;

public class GameplayScreen extends AbstractScreen implements InputProcessor
{
	TextureRegion textureRegion;
	TextureRegion energyBar;
	TextureRegion building;
	TextureRegion light;
	TextureRegion chestClosed;
	TextureRegion water;
	TextureRegion gold;
	TextureRegion gemBlue;
	TextureRegion gemGreen;
	TextureRegion gemOrange;
	TextureRegion characterBoy;
	TextureRegion characterEvilGirl;
	TextureRegion tree;
	TextureRegion selectGlow;
	TextureRegion selectGlowRed;
	TextureRegion star;
	TextureRegion heart;
	
	TextureRegion healthBar;
	TextureRegion healthBarOutline;
	
	SpriteBatch batch;
	
	//TextureRegion tile;
	Animation bobLeft;
	Animation bobRight;
	Animation bobJumpLeft;
	Animation bobJumpRight;
	Animation bobIdleLeft;
	Animation bobIdleRight;
	Animation bobDead;
	
	static final int IDLE = 0;
	static final int RUN = 1;
	static final int JUMP = 2;
	static final int SPAWN = 3;
	static final int DYING = 4;
	static final int DEAD = 5;
	static final int LEFT = -1;
	static final int RIGHT = 1;
	
	Vector2 pos = new Vector2();
	
	List<Character> characters;
	List<Character> enemies;
	
	boolean dragInitiated = false;
	
	private ArrayList<Character> GetSelectedCharacters()
	{
		List<Character> selectedCharacters = new ArrayList<Character>();
		for(Character character : this.characters)
		{
			if(character.isSelected)
			{
				selectedCharacters.add(character);
			}
		}
		return (ArrayList<Character>) selectedCharacters;
	}
	
	private ArrayList<Character> GetTargetedEnemies()
	{
		List<Character> targetedEnemies = new ArrayList<Character>();
		for(Character enemy : this.enemies)
		{
			if(enemy.isTalkable)
			{
				targetedEnemies.add(enemy);
			}
		}
		return (ArrayList<Character>) targetedEnemies;
	}
	
	boolean buttonPressed = false;
	Vector2 targetPos = new Vector2();
	Stage stage;
	
	public GameplayScreen(TreasonGame game) {
		super(game);
		// TODO Auto-generated constructor stub
		//stage = new Stage();
		Gdx.input.setInputProcessor(this);
		
		this.createStage();
		
		//this.tile = new TextureRegion(new Texture(Gdx.files.internal("data/tile.png")), 0, 0, 20, 20);
		Texture bobTexture = new Texture(Gdx.files.internal("data/textures/bob.png"));
		
		TextureRegion[] split = new TextureRegion(bobTexture).split(20, 20)[0];
		TextureRegion[] mirror = new TextureRegion(bobTexture).split(20, 20)[0];
		for (TextureRegion region : mirror)
			region.flip(true, false);
		//spikes = split[5];
		bobRight = new Animation(0.1f, split[0], split[1]);
		bobLeft = new Animation(0.1f, mirror[0], mirror[1]);
		bobJumpRight = new Animation(0.1f, split[2], split[3]);
		bobJumpLeft = new Animation(0.1f, mirror[2], mirror[3]);
		bobIdleRight = new Animation(0.5f, split[0], split[4]);
		bobIdleLeft = new Animation(0.5f, mirror[0], mirror[4]);
		bobDead = new Animation(0.2f, split[0]);	
		

		
		characters = new ArrayList<Character>();
		enemies = new ArrayList<Character>();
		
		//Vector2 characterPos = new Vector2();
		//characterPos.x = 100; characterPos.y = 100;
		characters.add(new Character(new Vector2(100, 200)));
		
		//characterPos.x = 200; characterPos.y = 100;
		characters.add(new Character(new Vector2(300, 300)));
		
		//characterPos.x = 300; characterPos.y = 100;
		characters.add(new Character(new Vector2(500, 400)));
		
		enemies.add(new Character(new Vector2(500, 100)));
		enemies.add(new Character(new Vector2(500, 200)));
		enemies.add(new Character(new Vector2(500, 300)));
	}

	@Override
	public void show () {
		//map = new Map();
		//renderer = new MapRenderer(map);
		//controlRenderer = new OnscreenControlRenderer(map);
		textureRegion = new TextureRegion(new Texture(Gdx.files.internal("data/textures/mockup.jpg")), 0, 0, 512, 512);
		building = new TextureRegion(new Texture(Gdx.files.internal("data/textures/building.png")), 0, 0, 256, 256);
		light = new TextureRegion(new Texture(Gdx.files.internal("data/textures/light.png")), 0, 0, 512, 512);
		//light = new TextureRegion(new Texture(Gdx.files.internal("data/textures/lightred.png")), 0, 0, 256, 256);
		//water = new TextureRegion(new Texture(Gdx.files.internal("data/textures/water.png")), 0, 0, 512, 512);
		//water = new TextureRegion(new Texture(Gdx.files.internal("data/textures/grass2.jpg")), 0, 0, 512, 512);
		water = new TextureRegion(new Texture(Gdx.files.internal("data/textures/amazonia2048.jpg")), 0, 0, 2048, 2048);
		chestClosed = new TextureRegion(new Texture(Gdx.files.internal("data/textures/planetcute/chest closed.png")), 0, 0, 256, 256);
		gold = new TextureRegion(new Texture(Gdx.files.internal("data/textures/gold.png")), 0, 0, 64, 64);
		
		gemBlue = new TextureRegion(new Texture(Gdx.files.internal("data/textures/planetcute/Gem Blue.png")), 0, 0, 128, 128);
		gemGreen = new TextureRegion(new Texture(Gdx.files.internal("data/textures/planetcute/Gem Green.png")), 0, 0, 128, 128);
		gemOrange = new TextureRegion(new Texture(Gdx.files.internal("data/textures/planetcute/Gem Orange.png")), 0, 0, 128, 128);
		
		characterBoy = new TextureRegion(new Texture(Gdx.files.internal("data/textures/planetcute/character boy.png")), 0, 0, 128, 128);
		characterEvilGirl = new TextureRegion(new Texture(Gdx.files.internal("data/textures/planetcute/character horn girl.png")), 0, 0, 128, 128);
		tree = new TextureRegion(new Texture(Gdx.files.internal("data/textures/tree.png")), 0, 0, 64, 64);
		
		healthBar = new TextureRegion(new Texture(Gdx.files.internal("data/textures/healthbar.png")), 0, 0, 128, 128);
		healthBarOutline = new TextureRegion(new Texture(Gdx.files.internal("data/textures/healthbaroutline.png")), 0, 0, 128, 128);
		
		selectGlow = new TextureRegion(new Texture(Gdx.files.internal("data/textures/selectGlow.png")), 0, 0, 128, 128);
		selectGlowRed = new TextureRegion(new Texture(Gdx.files.internal("data/textures/selectGlowRed.png")), 0, 0, 128, 128);
		
		star = new TextureRegion(new Texture(Gdx.files.internal("data/textures/planetcute/star.png")), 0, 0, 128, 128);
		heart = new TextureRegion(new Texture(Gdx.files.internal("data/textures/heart.png")), 0, 0, 128, 128);
		
		//energyBar = new TextureRegion(new Texture(Gdx.files.internal("data/textures/energyBar.png")), 0, 0, 256, 256);
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 960, 640);
	}

	@Override
	public void render (float delta) {
		delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(water, 0, 0, 2048, 2048);
		//batch.draw(textureRegion, 0, 0);
		batch.draw(building, 300, 200);
		batch.draw(light, 700, 150);
		batch.draw(gold, 500, 100);
		batch.draw(chestClosed, 600, 200, 128, 128);
		batch.draw(gemBlue, 600, 300, 64, 64);
		batch.draw(gemGreen, 600, 400, 64, 64);
		batch.draw(gemOrange, 600, 500, 64, 64);
		batch.draw(tree, 300, 300, 64, 64);
		batch.draw(heart, 450, 450, 64, 64);

		//batch.draw(energyBar, 100, 100);
		
		int state = JUMP;
		
		Animation anim = null;
		boolean loop = true;
//		if (state == RUN) {
//			if (map.bob.dir == Bob.LEFT)
				anim = bobLeft;
//			else
//				anim = bobRight;
//		}
//		if (map.bob.state == Bob.IDLE) {
//			if (map.bob.dir == Bob.LEFT)
//				anim = bobIdleLeft;
//			else
//				anim = bobIdleRight;
//		}
//		if (map.bob.state == Bob.JUMP) {
//			if (map.bob.dir == Bob.LEFT)
//				anim = bobJumpLeft;
//			else
//				anim = bobJumpRight;
//		}
//		if (map.bob.state == Bob.SPAWN) {
//			anim = spawn;
//			loop = false;
//		}
//		if (map.bob.state == Bob.DYING) {
//			anim = dying;
//			loop = false;
//		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			anim = bobLeft;
			pos.x-=3;
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			anim = bobRight;
			pos.x+=3;
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			anim = bobLeft;
			pos.y+=3;
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			anim = bobRight;
			pos.y-=3;
		}
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			anim = bobJumpRight;
			pos.y+=10;
		}
//		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
//			anim = bobLeft;
//		}

		Vector2 cursorPos = new Vector2(Gdx.input.getX(), 640-Gdx.input.getY());

		//batch.draw(anim.getKeyFrame(0, false), pos.x, pos.y, 48, 48);
		
		for(Character character : this.characters)
		{
			character.Update();
			
			batch.draw(characterBoy, character.pos.x + 32, character.pos.y + 32, 64, 64);
			if(character.isSelected)
			{
				batch.draw(healthBarOutline, character.pos.x, character.pos.y + 40, 128, 128);
				batch.draw(healthBar, character.pos.x, character.pos.y + 40, 80, 128);
				batch.draw(selectGlow, character.pos.x, character.pos.y, 128, 128);
			}
			if(character.isTalkable)
			{
				batch.draw(light, character.pos.x-64, character.pos.y, 256, 256);
			}
		}
		
		for(Character enemy : this.enemies)
		{
			batch.draw(characterEvilGirl, enemy.pos.x + 32, enemy.pos.y + 32, 64, 64);
			if(enemy.isTalkable)
			{
				batch.draw(selectGlowRed, enemy.pos.x, enemy.pos.y, 128, 128);
			}
		}

		if(this.buttonPressed)// && this.GetSelectedCharacters().size() > 0)
		{
			batch.draw(star, cursorPos.x-64, cursorPos.y-64, 128, 128);
		}
		else
		{
			if(this.GetSelectedCharacters().size() > 0 &&
				this.GetTargetedEnemies().size() == 0
				&& this.dragInitiated)
			{
				batch.draw(star, cursorPos.x-32, cursorPos.y-32, 64, 64);
			}
		}
		
		batch.draw(star, targetPos.x-32, targetPos.y-32, 64, 64);
		
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		Table.drawDebug(stage);

//		map.update(delta);
//		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
//		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//		renderer.render(delta);
//		controlRenderer.render();
//
//		if (map.bob.bounds.overlaps(map.endDoor.bounds)) {
//			game.setScreen(new GameOverScreen(game));
//		}
//
//		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
//			game.setScreen(new MainMenu(game));
//		}
		
		buttonPressed = false;
	}
	
	private void createStage()
	{
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		final Skin skin = new Skin();
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("boy", new Texture("data/textures/planetcute/character boy.png"));

		Image sourceImage = new Image(skin, "boy");
		sourceImage.setBounds(50, 125, 100, 100);
		stage.addActor(sourceImage);

		Image validTargetImage = new Image(skin, "boy");
		validTargetImage.setBounds(200, 50, 100, 100);
		stage.addActor(validTargetImage);

		Image invalidTargetImage = new Image(skin, "boy");
		invalidTargetImage.setBounds(200, 200, 100, 100);
		stage.addActor(invalidTargetImage);
		DragAndDrop dragAndDrop = new DragAndDrop();
		dragAndDrop.addSource(new Source(sourceImage) {
			public Payload dragStart (InputEvent event, float x, float y, int pointer) {
				Payload payload = new Payload();
				payload.setObject("Some payload!");

				payload.setDragActor(new Label("Some payload!", skin));

				Label validLabel = new Label("Some payload!", skin);
				validLabel.setColor(0, 1, 0, 1);
				payload.setValidDragActor(validLabel);

				Label invalidLabel = new Label("Some payload!", skin);
				invalidLabel.setColor(1, 0, 0, 1);
				payload.setInvalidDragActor(invalidLabel);

				return payload;
			}
		});
		dragAndDrop.addTarget(new Target(validTargetImage) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.GREEN);
				return true;
			}

			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
				System.out.println("Accepted: " + payload.getObject() + " " + x + ", " + y);
			}
		});
		dragAndDrop.addTarget(new Target(invalidTargetImage) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.RED);
				return false;
			}

			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
			}
		});
	}
	
	public void resize (int width, int height) {
		stage.setViewport(width, height, true);
	}

	public boolean needsGL20 () {
		return false;
	}

	@Override
	public void hide () {
		Gdx.app.debug("Cubocy", "dispose game screen");
//		renderer.dispose();
//		controlRenderer.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//this.buttonPressed = true;
		this.dragInitiated = false;
		// TODO Auto-generated method stub
		Vector2 touchPos = new Vector2(screenX, 640 - screenY);
		
		for(Character character : this.characters)
		{
			Vector2 characterCenterPos = new Vector2(character.pos.x + 64, character.pos.y + 64);
			//if(Math.abs(screenX - (character.pos.x + 64)) < 32
			//	&& Math.abs((640-screenY) - (character.pos.y + 64)) < 32)
			if(touchPos.dst(characterCenterPos) < 32 && !character.isDraggable)
			{
				character.isDraggable = true;
			}			
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		this.dragInitiated = false;
		//buttonPressed = true;
		Vector2 touchPos = new Vector2(screenX, 640 - screenY);
		
		boolean isAnyoneSelected = false;

		for(Character character : this.characters)
		{
			Vector2 characterCenterPos = new Vector2(character.pos.x + 64, character.pos.y + 64);
			//if(Math.abs(screenX - (character.pos.x + 64)) < 32
			//	&& Math.abs((640-screenY) - (character.pos.y + 64)) < 32)
			if(touchPos.dst(characterCenterPos) < 32)
			{
				isAnyoneSelected = true;
				break;
			}
		}
		
		for(Character character : this.characters)
		{
			Vector2 characterCenterPos = new Vector2(character.pos.x + 64, character.pos.y + 64);
			//if(Math.abs(screenX - (character.pos.x + 64)) < 32
			//	&& Math.abs((640-screenY) - (character.pos.y + 64)) < 32)
			if(touchPos.dst(characterCenterPos) < 32 && !character.isTalkable)
			{
				character.isSelected = !character.isSelected;
			}

			if(character.isSelected && !isAnyoneSelected)
			{
				character.SetDestination(new Vector2(touchPos.x - 64, touchPos.y - 64));
				character.isSelected = false;	
				for(Character enemy : this.enemies)
				{
					Vector2 enemyCenterPos = new Vector2(enemy.pos.x + 64, enemy.pos.y + 64);
					if(touchPos.dst(enemyCenterPos) < 32)// && !character.isTalkable)
					{
						enemy.isTalkable = true;
					}
				}
			}
			
			character.isDraggable = false;
		}
		if(!isAnyoneSelected)// || this.GetSelectedCharacters().size() > 0)
		{
			this.targetPos = touchPos.cpy();
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		this.buttonPressed = true;
		this.dragInitiated = true;
		Vector2 touchPos = new Vector2(screenX, 640 - screenY);
		
		for(Character character : this.characters)
		{
			Vector2 characterCenterPos = new Vector2(character.pos.x + 64, character.pos.y + 64);
			//if(Math.abs(screenX - (character.pos.x + 64)) < 32
			//	&& Math.abs((640-screenY) - (character.pos.y + 64)) < 32)
			if(touchPos.dst(characterCenterPos) > 32 && character.isDraggable)
			{
				character.isSelected = true;
				character.isTalkable = false;
			}
		}
		for(Character character : this.GetSelectedCharacters())
		{
//			Vector2 characterCenterPos = new Vector2(character.pos.x + 64, character.pos.y + 64);
//			if (touchPos.dst(characterCenterPos) < 32 && !character.isDraggable)
//			{
//				character.isTalkable = true;
//			}
//			else
//			{
//				character.isTalkable = false;
//			}
			
			for(Character enemy : this.enemies)
			{
				Vector2 enemyCenterPos = new Vector2(enemy.pos.x + 64, enemy.pos.y + 64);
				if(touchPos.dst(enemyCenterPos) < 32)// && !character.isTalkable)
				{
					enemy.isTalkable = true;
				}
				else
				{
					enemy.isTalkable = false;
				}
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
//		if(this.GetSelectedCharacters().size() > 0)
//		{
//			this.buttonPressed = true;
//		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
