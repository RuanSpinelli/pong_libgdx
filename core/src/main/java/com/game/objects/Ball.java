package com.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.game.Main;
import com.game.helper.BodyHelper;
import com.game.helper.Const;
import com.game.helper.ContentType;
import com.game.screens.GameScreen;

public class Ball {
	
	private Body body;
	private float x,y, speed, velX, velY;	
	private int width, height;
	private GameScreen gameScreen;
	private Texture texture;
	
	public Ball(GameScreen gameScreen) {
		this.x = Main.INSTANCE.getViewPort().getWorldWidth() / 2f;
		this.y = Main.INSTANCE.getViewPort().getWorldHeight() / 2f;
		this.speed = 5;
		
		this.velX = getRandomDirection();
		this.velY = getRandomDirection();
		this.texture = new Texture("white_square.png");
		this.gameScreen = gameScreen;
		this.width = 32;
		this.height = 32;
		this.body = BodyHelper.createBody(this.x, this.y, this.width, this.height,
						false, 0, gameScreen.getWorld(), ContentType.BALL);
	}
	
	
	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}


	private float getRandomDirection() {
		return (Math.random() < 0.5) ? 1: -1;
	}
	
	public void update() {
		x = body.getPosition().x * Const.PPM - (width / 2);
		y = body.getPosition().y * Const.PPM - (height / 2);
		
		this.body.setLinearVelocity(velX * speed, velY * speed);
		
		// score
		
		if (x < 0 ) {
			gameScreen.getPlayerAi().score();
			reset();
		} else if (x > Main.INSTANCE.getScreenWidth()) {
			gameScreen.getPlayer().score();
			reset();
		}
		
	}

	public void reverseVelX(){
		this.velX *= -1;
	}
	
	
	public void incSpeed() {
		this.speed *= 1.1f;
	}
	
	
	public void reverseVelY() {
		this.velY *= -1;
	}
	
	
	public void reset() {

	    this.velX = getRandomDirection();
	    this.velY = getRandomDirection();
	    this.speed = 5;

	    float centerX = Main.INSTANCE.getViewPort().getWorldWidth() / 2f;
	    float centerY = Main.INSTANCE.getViewPort().getWorldHeight() / 2f;

	    this.body.setTransform(
	        centerX / Const.PPM,
	        centerY / Const.PPM,
	        0
	    );

	    this.body.setLinearVelocity(
	        velX * speed,
	        velY * speed
	    );
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(texture, x, y, width, height);
	}
}
