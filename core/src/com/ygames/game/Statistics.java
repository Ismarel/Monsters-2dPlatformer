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
import com.badlogic.gdx.math.Vector3;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Statistics implements Screen{
    Vector3 touch;
    Game game;
    GameScreen gs;
    OrthographicCamera camera;
    Texture image;
    int delay=0;
    int totalcoins,deaths;
    public Statistics(final Game g, final GameScreen gss){
       
        game=g;
        gs=gss;
        camera=new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        image=new Texture("Images/Statistics.png");
        touch = new Vector3();
        totalcoins=0;
        deaths=0;
    }
    @Override
    public void show() {
      totalcoins=game.getScore();
      deaths=game.getDeaths();
   
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        game.batch.begin();
        game.batch.draw(image, 0, 0);
        game.font.draw(game.batch, "Total Coins: "+totalcoins, 400, 240);
        game.font.draw(game.batch, "Deaths: "+deaths, 400, 200);
        //game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();
        delay++;
        if(Gdx.input.isTouched()){
            touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touch);
            
            if(touch.x<300 && touch.y > 300){
                game.setScreen(game.menu);
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

