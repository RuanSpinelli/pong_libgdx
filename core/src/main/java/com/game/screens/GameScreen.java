package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.game.GameContactListener;
import com.game.Main;
import com.game.objects.Ball;
import com.game.objects.Player;
import com.game.objects.PlayerAI;
import com.game.objects.Wall;

public class GameScreen extends ScreenAdapter {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private World world;
	private Box2DDebugRenderer box2DDebugRenderer;
	
	// Game objects
	private Player player;
	private PlayerAI playerAi;
	private Ball ball;
	private Wall wallTop, wallBottom;
	private GameContactListener gameContactListener;
	private TextureRegion[] numbers;
	
	
	
	// Construtor
    public GameScreen(OrthographicCamera camera) {
        this.camera = camera;
        this.camera.update();
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.gameContactListener = new GameContactListener(this);
        this.world.setContactListener(this.gameContactListener);
    
        float worldH = Main.INSTANCE.getViewPort().getWorldHeight();

        // usa worldH em vez do screenHeight fixo
        player = new Player(16, worldH / 2, this);
        playerAi = new PlayerAI(Main.INSTANCE.getViewPort().getWorldWidth() - 16, worldH / 2,this);
         
        ball = new Ball(this);

        // paredes
        this.wallBottom = new Wall(16, false, this);
        this.wallTop    = new Wall(worldH - 16, true, this);
        
        this.numbers = loadTextureSprite("numbers.png", 10);
    }
    
    public void update() {
    	world.step(1/60f, 6, 2);
    	
    	camera.update();
    	batch.setProjectionMatrix(camera.combined);
    	
    	this.player.update();
    	this.playerAi.update();
    	this.ball.update();
    	
    	if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
    		Gdx.app.exit();
    	}
    	
    	if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
    		this.ball.reset();
    		this.player.setScore(0);
    		this.playerAi.setScore(0);
    	}
    }
    
    
    private void drawNumbers(SpriteBatch batch, int number, float x, float y, float width, float height) {
    	if(number < 10) {
    		batch.draw(numbers[number], x, y, width, height);
    	} else {
    		batch.draw(numbers[Integer.parseInt((""+number).substring(0,1))], x, y, width, height);
    		batch.draw(numbers[Integer.parseInt((""+number).substring(1,2))], x+20, y, width, height);
    		
    	}
    }
    
    private TextureRegion[] loadTextureSprite(String filename, int colums) {	
    	Texture texture = new Texture(filename);
    	return TextureRegion.split( texture, texture.getWidth() / colums, texture.getHeight())[0];
    	
    }
    
    public Player getPlayer() {
		return player;
	}

	public PlayerAI getPlayerAi() {
		return playerAi;
	}

	@Override
    public void render(float delta) {
    	update();
    	
    	Gdx.gl.glClearColor(0,0,0,1);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
    	
        Main.INSTANCE.getViewPort().apply(); // importante pro ExtendViewport

        batch.begin();
    		player.render(batch);
    		playerAi.render(batch);
    		ball.render(batch);
    		wallBottom.render(batch);
    		wallTop.render(batch);
    		this.drawNumbers(batch, player.getScore(), 94, Main.INSTANCE.getScreenHeight() - 35, 30,42);
    		this.drawNumbers(batch, playerAi.getScore(), Main.INSTANCE.getScreenWidth() - 96, Main.INSTANCE.getScreenHeight() - 35, 30,42);
    		
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

    public Ball getBall() {
    	return this.ball;
    }
    
	public void setWorld(World world) {
		this.world = world;
	}
}