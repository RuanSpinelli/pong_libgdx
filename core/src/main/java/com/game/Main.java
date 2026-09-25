package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.game.screens.GameScreen;


// Classe principal do jogo
/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
	
	
	public static Main INSTANCE;
	private int screenWidth, screenHeight;
	private OrthographicCamera camera;
	private FitViewport viewPort;
	
	
	
	public Main() {
		INSTANCE = this;
	}

	@Override
	public void create() {

	    this.screenWidth = 800;
	    this.screenHeight = 600;
	    //this.screenWidth = 1920;
	    //this.screenHeight = 1080;

		
	    this.camera = new OrthographicCamera();

	    this.viewPort = new FitViewport(screenWidth, screenHeight, camera); // ← FitViewport

	    // IMPORTANTE: calcula o worldWidth/worldHeight reais
	    // antes de criar Ball e Player
	    this.viewPort.update(
	        Gdx.graphics.getWidth(),
	        Gdx.graphics.getHeight(),
	        true
	    );

	    setScreen(new GameScreen(camera));
	}
    
    public FitViewport getViewPort() {
        return viewPort;
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
