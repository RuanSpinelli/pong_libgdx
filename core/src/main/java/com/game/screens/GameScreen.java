package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Main;

public class GameScreen extends ScreenAdapter {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private World world;
	private Box2DDebugRenderer box2DDebugRenderer;
	
	
	// Construtor
    public GameScreen(OrthographicCamera camera) {
        this.camera = camera;
        this.camera.position.set(new Vector3(Main.INSTANCE.getScreenWidth()/2,
        						Main.INSTANCE.getScreenHeight()/2,
        						0));
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        
    }
    
    public void update() {
    	world.step(1/60f, 6, 2);
    	batch.setProjectionMatrix(camera.combined);
    	
    	if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
    		Gdx.app.exit();
    	}
    }
    
    @Override
    public void render(float delta) {
    	update(); // atualiza a tela
    	
    	
    	Gdx.gl.glClearColor(0,0,0,1); // limpar a tela
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
    	
    	batch.begin();
    		//batch.draw
    	batch.end();
    	
    }
}