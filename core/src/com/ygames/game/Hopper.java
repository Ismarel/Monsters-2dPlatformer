/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygames.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author user
 */
public class Hopper{
    
    float posX,posY,speedX,speedY,width,height;
    Rectangle bot,left,right;
    Player player;
    Animation anime;
    public boolean jumped=true;
    GameScreen game;
    public boolean visible=false;
    int soundTime=0;
    
    public Hopper(int x, int y,int w,int h,GameScreen g){
        game=g;
        posX=x*w;
        posY=y*h;
        
        width=w;
        height=h;
        bot=new Rectangle();
        left=new Rectangle();
        right=new Rectangle();
        //anime=new Animation();
    }
    
    public void update(float time){
        anime.update(0.4f);
        
        if(posX <game.camera.position.x - 450){
            visible=false;
        }else if(posX+width > game.camera.position.x+450){
            visible=false;
        }else{
            visible=true;
        }
        posX+=speedX;
        posY+=speedY;
        
        speedY-=0.5f; //gravity
        
        bot.set(posX+10, posY, width-10, height);
        left.set(posX, posY, width-20, height-10);
        right.set(posX+20, posY, width-20, height-10);
        
        
        checkCollision();
    }
    
    public void render(SpriteBatch batch){
        batch.draw(anime.getFrame(), posX, posY);
    }
    
    void checkCollision(){
        if(left.overlaps(player.top) || right.overlaps(player.top)){
            speedY=0;           
            player.health-=3;
            if(soundTime==0){
                game.level.dog.play();soundTime++;
            }
        }else{
            soundTime=0;
        }
    }
    public void hop(){
        if(!jumped){
            speedY=+10;
            jumped=true;
        }
    }
    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Animation getAnime() {
        return anime;
    }

    public void setAnime(Animation anime) {
        this.anime = anime;
    }
    
    
}
