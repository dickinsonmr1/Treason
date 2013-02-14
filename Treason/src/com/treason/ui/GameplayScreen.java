package com.treason.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.treason.TreasonGame;

public class GameplayScreen extends AbstractScreen
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
	
	public GameplayScreen(TreasonGame game) {
		super(game);
		// TODO Auto-generated constructor stub
		
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
		
		pos.x = 100;
		pos.y = 100;
	}

	@Override
	public void show () {
		//map = new Map();
		//renderer = new MapRenderer(map);
		//controlRenderer = new OnscreenControlRenderer(map);
		textureRegion = new TextureRegion(new Texture(Gdx.files.internal("data/textures/mockup.jpg")), 0, 0, 512, 512);
		building = new TextureRegion(new Texture(Gdx.files.internal("data/textures/building.png")), 0, 0, 256, 256);
		light = new TextureRegion(new Texture(Gdx.files.internal("data/textures/lightred.png")), 0, 0, 256, 256);
		water = new TextureRegion(new Texture(Gdx.files.internal("data/textures/water.png")), 0, 0, 512, 512);
		chestClosed = new TextureRegion(new Texture(Gdx.files.internal("data/textures/planetcute/chest closed.png")), 0, 0, 256, 256);
		gold = new TextureRegion(new Texture(Gdx.files.internal("data/textures/gold.png")), 0, 0, 64, 64);
		
		gemBlue = new TextureRegion(new Texture(Gdx.files.internal("data/textures/planetcute/Gem Blue.png")), 0, 0, 128, 128);
		gemGreen = new TextureRegion(new Texture(Gdx.files.internal("data/textures/planetcute/Gem Green.png")), 0, 0, 128, 128);
		gemOrange = new TextureRegion(new Texture(Gdx.files.internal("data/textures/planetcute/Gem Orange.png")), 0, 0, 128, 128);
		
		//energyBar = new TextureRegion(new Texture(Gdx.files.internal("data/textures/energyBar.png")), 0, 0, 256, 256);
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 1280, 720);
	}

	@Override
	public void render (float delta) {
		delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(water, 0, 0, 1280, 720);
		batch.draw(textureRegion, 0, 0);
		batch.draw(building, 100, 100);
		batch.draw(light, 400, 0);
		batch.draw(gold, 500, 100);
		batch.draw(chestClosed, 600, 200, 64, 64);
		batch.draw(gemBlue, 600, 300, 32, 32);
		batch.draw(gemGreen, 600, 400, 32, 32);
		batch.draw(gemOrange, 600, 500, 32, 32);
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

		batch.draw(anim.getKeyFrame(0, false), pos.x, pos.y, 48, 48);

		
		batch.end();

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
	}

	@Override
	public void hide () {
		Gdx.app.debug("Cubocy", "dispose game screen");
//		renderer.dispose();
//		controlRenderer.dispose();
	}
}
