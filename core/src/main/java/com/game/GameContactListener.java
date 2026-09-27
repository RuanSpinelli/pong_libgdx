package com.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.game.helper.ContentType;
import com.game.screens.GameScreen;

public class GameContactListener implements ContactListener{

	private GameScreen gameScreen;
	
	
	public GameContactListener(GameScreen gameScreen){
		this.gameScreen = gameScreen;
		
	}
	
	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		Fixture a = contact.getFixtureA();
		Fixture b = contact.getFixtureB();
		
		
		if (a == null || b == null) return ;
		if(a.getUserData() == null || b.getUserData() == null) return;
		
		if (a.getUserData() == ContentType.BALL || b.getUserData() == ContentType.BALL) {
			// ball - player
			if (a.getUserData() == ContentType.PLAYER || b.getUserData() == ContentType.PLAYER) {
				gameScreen.getBall().reverseVelX();
				gameScreen.getBall().incSpeed();
			}
			
			if (a.getUserData() == ContentType.WALL || b.getUserData() == ContentType.WALL) {
				gameScreen.getBall().reverseVelY();
				gameScreen.getBall().incSpeed();
			}
		
		}
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

	
	
}
