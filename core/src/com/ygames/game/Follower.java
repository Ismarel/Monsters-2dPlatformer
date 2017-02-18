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
public class Follower {
    
    int posX, posY, width,  height,speedX,speedY;
    Rectangle bot,left,right;
    Animation anime;
    Player player;
    GameScreen game;
    public boolean visible=false;
    
    public Follower(int x,int y,int w,int h,GameScreen g){
        game=g;
        //anime=new Animation();
        posX=x*w;
        posY=y*h;
        
        width=w;
        height=h;
        
        speedX=0;
        speedY=0;
        bot=new Rectangle();
        left=new Rectangle();
        right=new Rectangle();
    }
    
    public void update(float time){
        anime.update(1);
        
        if(posX <game.camera.position.x - 450){
            visible=false;
        }else if(posX+width > game.camera.position.x+450){
            visible=false;
        }else{
            visible=true;
        }
        posX+=speedX;
        posY+=speedY;
        
        speedY-=1; //Gravity
        
        if(posX < player.posX){
            speedX=1;
        }else if(posX > player.posX){
            speedX=-1;
        }else{
            speedX=0;
        }
        
        bot.set(posX+10,posY,width-20,height);
        left.set(posX,posY+10,width-20,height-10);
        right.set(posX+20,posY+10,width-20,height-10);
        
        checkCollision();
    }
    
    public void render(SpriteBatch batch){
        batch.draw(anime.getFrame(), posX, posY);
    }
    
    void checkCollision(){
        if(left.overlaps(player.bot)){
            game.level.kiss.play();
            player.posX=posX-65;
            player.health-=1;
        }else if(right.overlaps(player.bot)){
            game.level.kiss.play();
            player.posX=posX+45;
            player.health-=1;
        }
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public Animation getAnime() {
        return anime;
    }

    public void setAnime(Animation anime) {
        this.anime = anime;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    
}
