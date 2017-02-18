/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author user
 */
public class PauseScreen implements Screen{

    Game game;
    GameScreen gs;
    OrthographicCamera camera;
    Vector3 touch;
    Texture image;
    
    public PauseScreen(final Game g, GameScreen gss){
        game=g;
        gs=gss;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        touch=new Vector3();
        image=new Texture("Images/paused.png");
    }
    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        game.batch.begin();
        //game.font.draw(game.batch, "Paused", 300, 450);
        game.batch.draw(image, 0, 0);
        game.batch.end();
        
        if(Gdx.input.isTouched()){       //Set gamescreen
            touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touch);
            if(touch.y<250){
                game.gamescreen.paused=false;
                game.gamescreen.level.music.play();
                game.setScreen(game.gamescreen);    
            }else if(touch.y>360 && touch.x < 400){ //set mainmenu screeen
                game.gamescreen.level.music.stop(); 
                game.gamescreen.paused=false;
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
