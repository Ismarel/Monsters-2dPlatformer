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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author user
 */
public class InfoScreen implements Screen{

    Texture image,Back;
    Game game;
    OrthographicCamera camera;
    Vector3 touch;
    Rectangle back;
    
    public InfoScreen(Game g){
        game=g;
        image=new Texture("Images/Information.png");
        camera=new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        touch=new Vector3();
        back=new Rectangle(0,450,150,50);
        Back=new Texture("Images/back.png");
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
        game.batch.draw(image, 0, 0);
        game.batch.draw(Back, back.x, back.y);
        game.batch.end();
        
        if(Gdx.input.isTouched()){
            touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touch);
            
            if(back.contains(touch.x,touch.y)){
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
        
    }
    
}
