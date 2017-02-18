/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class WinScreen implements Screen{

    Game game;
    GameScreen gs;
    OrthographicCamera camera;
    public int coins=0;
    Texture image;
    int delay=0;
    public WinScreen(final Game g, final GameScreen gss,int c){
       
        game=g;
        gs=gss;
        camera=new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        image=new Texture("Images/Win.png");
    }
    @Override
    public void show() {
        gs.level.music.pause();
        
         game.addScore(coins);
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        game.batch.begin();
        game.batch.draw(image, 0, 0);
        game.font.draw(game.batch, "Coins "+coins, 370, 250);
        //game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();
        delay++;
        if(Gdx.input.isTouched()){
            if(delay>100){
                delay=0;
                gs.level.music.play();
                game.setScreen(gs);
            }
     
        }
    }

    @Override
    public void resize(int width, int height) {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
      
    }

    @Override
    public void dispose() {
        image.dispose();
    }
    
}
