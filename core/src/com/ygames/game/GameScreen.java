/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class GameScreen implements Screen{
    
    Game game;
    MainMenu menu;
    public static int leftMoveArea,rightMoveArea,jumpArea;
    public OrthographicCamera camera;
    Level level;
    float camHalfWidth;
    float camHalfHeight;
    int x1,x2,y1,y2,jButtonInc,i,delay;
    Rectangle right,left,jmp;
    Vector3 Point;
    public boolean reachedExit=false;
    public boolean lockCam=false;
    public static int totalLevels,currentLevel,coins;
    public boolean paused=false;
    public boolean reload=false;
    
    public GameScreen(final Game g,MainMenu m) throws IOException{
        game = g;
        menu=m;
        currentLevel=0;
    
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);
        
        level = new Level(this,game);
         leftMoveArea = ((Gdx.graphics.getWidth()/2)/2)-100;
         rightMoveArea = leftMoveArea+leftMoveArea;
         jumpArea = rightMoveArea+200;
        
        level.load_map("Levels/map"+currentLevel+".txt");
        level.loadResources();
        Point = new Vector3();
        totalLevels=5;
        lockCam=false;
        delay=0;
        
    }
    
     public void loadLevel(){
         if(level!=null){
             level.dispose();
             level=null;
             System.gc();
         }
            
            camera=null;
            camera = new OrthographicCamera();
            camera.setToOrtho(false, 800, 480);
            reachedExit=false;
            lockCam=false;
            
            if(currentLevel > totalLevels){
                currentLevel=0;
            }
            game.addLevel(currentLevel); // add new unlocked level to sharedpreferences in Game class
            level = new Level(this,game);
            try {
               
                level.load_map("Levels/map"+currentLevel+".txt");
                level.loadResources();
                
            } catch (IOException ex) {
                Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
    }
    
    @Override
    public void show() {
       //System.out.println(Gdx.app.getJavaHeap()/130000);
       
       //System.out.println(Gdx.app.getNativeHeap());
       level.music.play(); 
       level.music.setLooping(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
        
        if(reachedExit){  //If exit tile detect collision in Tile class
            currentLevel++;
            level.music.stop();
            game.winscreen.coins=Level.coins;
            game.setScreen(game.winscreen);
            loadLevel();
            
        }
        if(level.player.isAlive && !paused){
            level.update(delta);
        }else{
            level.music.stop();
//            level.dispose();
//            level=null;
            game.setScreen(game.deathscreen);
        }
        
//        Gdx.input.setCatchBackKey(true);
//        if(!paused){
//            if(Gdx.input.isButtonPressed(Buttons.BACK)){
//                paused=true;
//                game.setScreen(new PauseScreen(game,this));
//            }
//        }
        
        //########################Handle Input###########################
        if(Gdx.input.isKeyPressed(Keys.LEFT)){
            level.player.moveLeft();
            
        }else if(Gdx.input.isKeyPressed(Keys.RIGHT)){
           level.player.moveRight();
            
        }else{
    
            level.player.speedX=0;
        }
        if(Gdx.input.isKeyPressed(Keys.UP)){
            if(!level.player.isOnLadder){
                level.player.jump();
            }else{
                level.player.moveUp();
            }
            
            
        }else{
            level.player.ladderspeed=0;
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)){
            paused=true;
            level.music.pause();
            game.setScreen(game.pausescreen);
        }
        
        //######################Touch input############################3
        for(i=0;i<2;i++){
            
            if(Gdx.input.isTouched(i)){
            x1 = Gdx.input.getX(i);
            y1 = Gdx.input.getY(i);
            
            Point.set(x1, y1, 0);
            
            camera.unproject(Point);
            if(x1>jumpArea && y1>350){
             if(!level.player.isOnLadder){
                    level.player.jump();
                }else{
                    level.player.moveUp();
                }
               
            }
            if(x1>700 && y1<170){               
                
                    paused=true;
                    level.music.pause();
                    game.setScreen(game.pausescreen);
                 
            }
            if(x1>leftMoveArea && x1< rightMoveArea){//right movement
                level.player.moveRight();
               
            }else if(x1<leftMoveArea){ //left movement
                level.player.moveLeft();
              
            }    // touch ends        
         
            
         }
        }
        
        //####################Input Ends#########################
        
        //################## Adjust Camera #################################
           if(level.player.posX>camera.position.x && !lockCam){  //keep player in center when moving right
               //if(level.player.speedX>0){
                   camera.position.x+=5; 
               //}
            
            }else if(level.player.posX < camera.position.x-10 && !lockCam){ //keep player in center when moving left
                //if(level.player.speedX < 0){
                   camera.position.x-=5; 
                //}
            }else{
                camera.position.x+=0;   
            }
           
           if(level.player.posY< camera.position.y -148 && !lockCam){ //kepp player center if player moves down
               if(level.player.speedY != 0){  //When jumping adjust camera
                   camera.position.y-=7;
               }else if(level.player.speedY == 0){ // when on ground adjust camera
                   camera.position.y-=5;
               }
           }else if(level.player.posY > camera.position.y && !lockCam){ //kepp player center if player moves up
               if(level.player.speedY != 0){ 
                   camera.position.y+=3;
               }
           }else{
               camera.position.y+=0;
           }
        //#################Prevent camera from going off edges...###################
           if(camera.position.x<=400){
               camera.position.x=400;
           }
           if(camera.position.x >= level.width*40-400){
               camera.position.x = level.width*40-400;
           }

        //########################################################################
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
	game.batch.begin();
	
       
        try {
            level.render(game.batch);//################Level draw call
        } catch (IOException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
     
	game.batch.end();
        
        
        
        
    }

    @Override
    public void resize(int width, int height) {
        
    }

    @Override
    public void pause() {
        paused=true;
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    
    }

    @Override
    public void dispose() {
        
        level.dispose();
    }


}
