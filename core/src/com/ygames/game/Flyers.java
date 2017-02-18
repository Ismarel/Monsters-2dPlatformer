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
public class Flyers {
    int posX,posY,speedX,speedY,width,height;
    Rectangle box;
    Animation anime;
    Player player;
    public boolean left=true;
    public boolean activate=false;
    GameScreen game;
    public boolean visible=false;
    
    public Flyers(int x,int y,int w,int h,GameScreen g){
        game=g;
        posX=x*w;
        posY=y*h;
        
        width=w;
        height=h;
        
        //anime=new Animation();
        box=new Rectangle();
        
        speedX=-1;
        speedY=0;
    }
    
    public void update(float time){
        anime.update(2);
        
        if(posX <game.camera.position.x - 450){
            visible=false;
        }else if(posX+width > game.camera.position.x+450){
            visible=false;
        }else{
            visible=true;
        }
        
        posX+=speedX;
        posY+=speedY;
        
        if(!left){
            speedX=1;
        }
        box.set(posX,posY,width,height);
        checkCollision();
    }
    
    void checkCollision(){
        if(box.overlaps(player.top)){
            player.posY = posY+40;
        }
    }
    
    public void render(SpriteBatch batch){
        
        batch.draw(anime.getFrame(), posX, posY);
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

    public Rectangle getBox() {
        return box;
    }

    public void setBox(Rectangle box) {
        this.box = box;
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
