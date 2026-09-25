package com.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import com.game.Main;
import com.game.helper.BodyHelper;
import com.game.helper.ContentType;
import com.game.screens.GameScreen;

public class Wall {

    private Body body;
    private GameScreen gameScreen;

    private float x, y;
    private float width, height;

    private Texture texture;
    private boolean isTop; // para saber se é a parede de cima

    public Wall(float y, boolean isTop, GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.isTop = isTop;
        this.y = y;

        this.height = 32;
        this.width = Main.INSTANCE.getScreenWidth(); // ← pode voltar a usar isso!
        this.x = width / 2f;

        this.texture = new Texture("white_square.png");

        this.body = BodyHelper.createBody(
            x, y, width, height, true, 0,
            gameScreen.getWorld(), ContentType.WALL
        );
    }

    /** Recalcula tamanho/posição quando a tela muda */
    
    /*public void resize() {
        this.width = Main.INSTANCE.getViewPort().getWorldWidth();
        this.x = width / 2f;

        // reposiciona a parede de cima de acordo com a nova altura
        if (isTop) {
            this.y = Main.INSTANCE.getViewPort().getWorldHeight() - 16;
        }

        // atualiza o body (destrói e recria, ou usa setTransform + shape)
        gameScreen.getWorld().destroyBody(body);
        this.body = BodyHelper.createBody(
            x, y, width, height, true, 0,
            gameScreen.getWorld(), ContentType.WALL
        );
    }*/

    public void render(SpriteBatch batch) {
        batch.draw(texture, x - width / 2f, y - height / 2f, width, height);
    }
}