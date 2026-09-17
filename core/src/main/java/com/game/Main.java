package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.screens.GameScreen;


// Classe principal do jogo
/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
	
	
	public static Main INSTANCE;
	private int screenWidth, screenHeight;
	private OrthographicCamera camera;
	
	
	
	public Main() {
		INSTANCE = this;
	}

    @Override
    public void create() {
    	this.screenHeight = Gdx.graphics.getWidth();
    	this.screenWidth = Gdx.graphics.getHeight();
    	this.camera = new OrthographicCamera();
    	this.camera.setToOrtho(false, screenWidth, screenHeight);
    	
    	
    	setScreen(new GameScreen(camera)); //Define uma tela que vai rodar quando o jogo inicializar
    }

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
    
    
    
    
}
