/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygames.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author user
 */
public class Enemy {
    
    float posX,posY,speedX,speedY,width,height;
    public int health,damage;
    public Animation anime;
    Rectangle r,left,right;
    Player player;
    ShapeRenderer s;
    public boolean visible=true;
    GameScreen game;
    public boolean movLeft,movRight;
    int delay=0;
    
    public Enemy(int x,int y, int w,int h,GameScreen g){
        game=g;
        posX=x*w;
        posY=y*h;
        
        speedX=-1;
        speedY=0;
        width=w;
        height = h;
        //anime = new Animation();
        r = new Rectangle();
        left=new Rectangle();
        right=new Rectangle();
        movLeft=true;
        movRight=false;
        s=new ShapeRenderer();
    }
    
    public void update(float time){
        anime.update(1);
        behaviour();       
    }
     
     void behaviour(){
         
        if(posX <game.camera.position.x - 450){
            visible=false;
        }else if(posX+width > game.camera.position.x+450){
            visible=false;
        }else{
            visible=true;
        }
        posX+=speedX;
        posY+=speedY;
        
         speedY-=1;
         
        if(movLeft){
           speedX=-1;
        }else{
            speedX=1;
        }
        
        
        r.set(posX+10, posY, width-10, height);
        left.set(posX,posY+10,width-25,height-10);
        right.set(posX+10,posY+10,width,height-10);
        checkCollosion();
    }
     
    void checkCollosion(){
        
        if(r.overlaps(player.bot)){
            game.level.horn.play();
            player.posX=player.posX+30;
            player.posY=player.posY+20;
            player.health-=5;
        }
    }
    
   public void render(SpriteBatch batch){
        batch.draw(anime.getFrame(), posX, posY);
        
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
