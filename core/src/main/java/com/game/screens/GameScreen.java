package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Main;
import com.game.objects.Ball;
import com.game.objects.Player;
import com.game.objects.Wall;

public class GameScreen extends ScreenAdapter {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private World world;
	private Box2DDebugRenderer box2DDebugRenderer;
	
	// Game objects
	private Player player;
	private Ball ball;
	private Wall wallTop, wallBottom;
	
	// Construtor
    public GameScreen(OrthographicCamera camera) {
        this.camera = camera;
        this.camera.update();
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
    
        float worldH = Main.INSTANCE.getViewPort().getWorldHeight();

        // usa worldH em vez do screenHeight fixo
        player = new Player(16, worldH / 2, this);
        ball = new Ball(this);

        // paredes
        this.wallBottom = new Wall(16, false, this);
        this.wallTop    = new Wall(worldH - 16, true, this);
    }
    
    public void update() {
    	world.step(1/60f, 6, 2);
    	
    	camera.update();
    	batch.setProjectionMatrix(camera.combined);
    	
    	this.player.update();
    	this.ball.update();
    	
    	if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
    		Gdx.app.exit();
    	}
    	
    	if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
    		this.ball.reset();
    	}
    }
    
    @Override
    public void render(float delta) {
    	update();
    	
    	Gdx.gl.glClearColor(0,0,0,1);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
    	
        Main.INSTANCE.getViewPort().apply(); // importante pro ExtendViewport

        batch.begin();
    		player.render(batch);
    		ball.render(batch);
    		wallBottom.render(batch);
    		wallTop.render(batch);
    	batch.end();
    }

    @Override
	public void resize(int width, int height) {
    	Main.INSTANCE.getViewPort().update(width, height, true);
    	camera.update();

        // reposiciona/redimensiona as paredes
        //wallTop.resize();
        //wallBottom.resize();
	}
    
    public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}