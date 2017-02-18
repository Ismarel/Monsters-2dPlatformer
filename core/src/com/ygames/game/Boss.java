/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author user
 */
public class Boss {
    int posX,posY,speedX,speedY,width,height;
    public int health;
    Rectangle box,top,bound;
    Animation anime;
    Player player;
    public boolean left=true;
    public boolean right=false;
    public boolean activate=false;
    GameScreen game;
    Music music;
    public boolean visible=false;
    public boolean isAlive=true;
    public boolean active=false;
    public boolean stomp=false;
    public boolean isFinal=false;
    ShapeRenderer shape;
    int delay,count=0;
    int spawnTime;
    
    public Boss(int x,int y,int w,int h,GameScreen g){
        game=g;
        posX=x*w;
        posY=y*h;
        spawnTime=0;
        width=140;
        height=140;
        //anime=new Animation();
        box=new Rectangle();
        top=new Rectangle();
        bound=new Rectangle();
        speedX=0;
        speedY=0;
        health=100;
        shape=new ShapeRenderer();
        isFinal=false;
    }
    
    public void update(float time){
        anime.update(5);
        
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
        if(active){
            if(!left){
                speedX=2;
            }else{
                speedX=-2;
            }
        }
            
        if(health<=0){
            game.lockCam=false;
            isAlive=false;
            visible=false;
            
        }
        
        if(posX<game.camera.position.x-400){
            left=false;
        }else if(posX+width>game.camera.position.x+400){
            left=true;
        }
        
        box.set(posX+20,posY,width-20,height-70);
        top.set(posX+40,posY+110,width-80,height-110);
        bound.set(posX-300,posY,width+400,height);
        checkCollision();
        if(isFinal && visible){
            attack();
        }
    }
    
    void checkCollision(){
        if(box.overlaps(player.top)){
            player.health-=1;
        }
        if(top.overlaps(player.bot)){
            stomp=true;
            health-=10;           
            player.isJumped=false;
            player.jump();
            
        }else{
            delay++;
            if(delay>50){
                stomp=false;
                delay=0;
            }
            
        }
        if(bound.overlaps(player.right)){
            game.lockCam=true;
            active=true;
                    
        }
    }
    
    void attack(){
        spawnTime++;
        if(spawnTime>200){
            Projectile p = new Projectile((int)top.x+50,(int)top.y+70,game.level.player.posX,game.level.player.posY,game.game);
            p.setAnime(game.level.Projectile);
            game.level.Projectiles.add(p);
            spawnTime=0;
        }
        
    }
    public void render(SpriteBatch batch){
        
        batch.draw(anime.getFrame(), posX, posY);
//                shape.setAutoShapeType(true);
//        shape.setColor(Color.BLACK);
//        shape.setProjectionMatrix(game.camera.combined);
//        shape.begin();
//        shape.set(ShapeRenderer.ShapeType.Line);
//        shape.rect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
//        shape.rect(top.getX(), top.getY(), top.getWidth(), top.getHeight());
//        shape.rect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
////            shape.rect(left.getX(), left.getY(), left.getWidth(), left.getHeight());
////            shape.rect(right.getX(), right.getY(), right.getWidth(), right.getHeight());
//        shape.end();
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


