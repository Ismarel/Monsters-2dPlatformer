/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class LevelSelect implements Screen{

    Game game;
    MainMenu menu;
    OrthographicCamera camera;
    Vector3 touch;
    int index=1;
    boolean touched=true;
    int delay=0;
    Texture image;
    
    public LevelSelect(final Game g,MainMenu m){
        game=g;
        menu=m;
        camera=new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        touch=new Vector3();
        image=new Texture("Images/LevelSelect.png");
        game.font.setColor(Color.RED);
  
    }
    @Override
    public void show() {
      
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
     
        game.batch.begin();
        game.batch.draw(image, 0, 0);
        
        game.font.draw(game.batch,String.valueOf(index), 400, 300);
        
//        game.font.draw(game.batch, "Select Level!!! ", 100, 150);
//        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();
        
        
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            try {
                game.setScreen(new GameScreen(game,menu));
            } catch (IOException ex) {
                Logger.getLogger(LevelSelect.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
        }else if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(game.menu);
            dispose();
        }
        
        if(Gdx.input.isTouched()){
            touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touch);
            if(touch.y<camera.position.y){
                try {
                   
                    GameScreen.currentLevel=index-1;
                    game.gamescreen.loadLevel();
                    game.setScreen(game.gamescreen);
                    
                   
                } catch (Exception ex) {
                    Logger.getLogger(LevelSelect.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(touch.y>camera.position.y && touch.x < 100){
                game.setScreen(game.menu);
            }else if(touch.y > camera.position.y && touch.x < camera.position.x ){ //decrement level
                if(delay>30){
                    index--;
                    delay=0;
                }
                
                if(index<1){
                    index=1;
                }
              
                
            }else if(touch.y > camera.position.y && touch.x> camera.position.x ){ //increment level
                if(delay>30){
                    index++;
                    delay=0;
                }
                if(index > game.unlockedLevels.size() ){
                    index = game.unlockedLevels.size();
                }
                if(index<1){
                    index=1;
                }
               
            }
            
        }
        
        delay++;
        if(delay > 300){
            touched=false;
            delay=0;
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
