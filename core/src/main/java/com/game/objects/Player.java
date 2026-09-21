package com.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.screens.GameScreen;

public class Player extends PlayerPaddle {

	public Player(float x, float y, GameScreen gameScreen) {
		super(x, y, gameScreen);

	}

	
	
	public void update() {
		super.update();
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			
			velY += 1;	
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			
			velY -= 1;
		}
		
		body.setLinearVelocity(0, velY * speed);
	}
	
}
