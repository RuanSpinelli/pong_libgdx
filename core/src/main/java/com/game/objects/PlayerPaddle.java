package com.game.objects;


import com.game.helper.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.game.screens.GameScreen;

public abstract class PlayerPaddle {
	
	protected Body body;
	protected float x, y, speed, velY;
	protected int width, height, score;
	protected Texture texture;
	protected GameScreen gameScreen;
	
	public PlayerPaddle(float x, float y, GameScreen gameScreen) {
		this.x = x;
		this.y = y;
		this.gameScreen = gameScreen;
		this.speed = 12;
		this.width = 20;
		this.height = 100;
		this.texture = new Texture("white_square.png");
		this.body =  BodyHelper.createBody(x, y, this.width, this.height, 
				false, 10000, gameScreen.getWorld(), ContentType.PLAYER);
		
	}

	
	public void update() {
		x = body.getPosition().x * Const.PPM - (width / 2);
		y = body.getPosition().y * Const.PPM - (height / 2);
		velY= 0;
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(texture, x, y, width, height);
		
		
	}
	
	public void score() {
		this.score ++;
	}
}
