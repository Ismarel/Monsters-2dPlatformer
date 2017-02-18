/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygames.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;


/**
 *
 * @author user
 */
public class Tile {
    ShapeRenderer shape;
    int posX,posY,width,height,speedX,speedY,delay;
    char type;
    public Animation anime;
    GameScreen game;
    Rectangle r;
    Game gam;
    boolean up=false;
    public boolean isAlive=true;
    public boolean visible=true;

    //Constructor
    public Tile(int x, int y, int width,int height,char type,final GameScreen g,Game ga){
        posX=x*width;
        posY=y*height;
        
        this.width=width;
        this.height=height;
        this.type=type;
        //shape=new ShapeRenderer();
        //anime=new Animation();
        game = g;
        gam=ga;
        r = new Rectangle(posX,posY,width,height);
        speedX=0;
        speedY=0;
        delay=0;
        up=false;
        isAlive=true;
    }
    
    public void update(float time){
        
        anime.update(1);
        posX+=speedX;
        posY+=speedY;
        
        if(posX <game.camera.position.x - 450){
            visible=false;
        }else if(posX+width > game.camera.position.x+450){
            visible=false;
        }else{
            visible=true;
        }
        
        if(!r.overlaps( game.level.player.Box)){
           if(visible)
            checkCollision();
        }
        
        if(type!='6'){ //if not tile 6 then check colision
            for(Enemy e:Level.Enemies){
                checkEnemy(e);
            }
       
            for(Hopper h: Level.Hopper){
                checkHopper(h);
            }
       
            for(Follower f:Level.Follower){
                checkFollower(f);
            }
            for(Boss b:Level.Boss){
                checkBoss(b);
            }
        }
       
        r.set(posX, posY, width, height);
        
        if(up && type=='m'){
            delay++;
            if(delay>100){
                speedY=0;
                delay=0;
                up=false;
            }
        }else if(up && type=='j'){
            delay++;
            if(delay>10){
                speedY=0;
                delay=0;
                up=false;
                isAlive=false;
                speedY=-6;
            }
        }
      
        
    }//update ends
    
    void checkCollision(){
        
          if(type=='#' || type=='g' || type=='r' || type=='i' || type=='j' || type=='m' || type=='n' || type=='q' ){  
            if(r.overlaps( game.level.player.bot) && isAlive){
                game.level.player.setSpeedY(0);
                game.level.player.posY = posY +65;
                game.level.player.isOnGround = true;
                game.level.player.isJumped=false;
                game.level.player.isHealing=false;

               // System.out.println("grounded");
            }else{
                game.level.player.isOnGround = false;  
            }
            if(r.overlaps(game.level.player.top) && isAlive){
                game.level.player.posY=game.level.player.posY - 5;
                game.level.player.speedY=-1;
            }
            if(r.overlaps(game.level.player.left) && isAlive){
                game.level.player.posX=posX+43;
                //game.level.player.movleft=false;
            }else{
                //game.level.player.movleft=true;
            }
            if(r.overlaps(game.level.player.right) && isAlive){
                game.level.player.posX=posX-70;
                //game.level.player.movright=false;
            }else{
              //game.level.player.movright=true;
          }
            
          
          }
          if(type=='g'){
              if(game.level.player.top.overlaps(r) || game.level.player.bot.overlaps(r)){
                  game.level.player.isHealing=true;
                  game.level.player.setHealth(100);
              }                               
            }
          if(type=='e'){
              if(r.overlaps(game.level.player.bot)){
                  game.reachedExit=true;
                 
              }
              
          }
          if(type=='s'){
              if(r.overlaps(game.level.player.bot)){
                  game.level.player.isAlive=false;
                 
              }
              
          }
          if(type=='m'){
              if(r.overlaps(game.level.player.top)){
                  speedY=1; 
                  up=true;
              }
          }
          
          if(type=='j' && isAlive){
              if(r.overlaps(game.level.player.top)){
                  speedY=2; 
                  up=true;
              }
          }
          if(type=='u'){
              if(r.overlaps(game.level.player.top)||r.overlaps(game.level.player.bot)){                 
                  game.level.player.speedY=0;
                  game.level.player.isOnLadder=true;
              }
          }
          if(type=='w'){
              if(r.overlaps(game.level.player.top)){                 
                  game.level.player.speedY=0;
                  game.level.player.isOnLadder=true;
              }
          }
          if(type=='v'){
              if(r.overlaps(game.level.player.bot)){
                  game.level.player.isOnLadder=false;
                  //game.level.player.isJumped=false;
                  
              }
          }
       //System.out.println(game.level.player.isOnLadder);
    } 
    
    void checkEnemy(Enemy e){
        if(r.overlaps(e.r)){
            e.speedY=0;
            e.posY = posY+32;
        }
        if(r.overlaps(e.left)){
            e.movLeft= false;
        }else if(r.overlaps(e.right)){
            e.movLeft=true;
        }
        
    }
    
    void checkHopper(Hopper h){
        if(r.overlaps(h.bot)){
            h.speedY=0;
            h.jumped=false;
            h.hop();
        }
    }
    
    void checkFollower(Follower f){
        if(r.overlaps(f.bot)){
            f.speedY=0;
            f.posY=posY+32;
        }
        if(r.overlaps(f.left)){
            f.posX=f.posX+1;
        }else if(r.overlaps(f.right)){
            f.posX=f.posX-1;
        }
    }
    
    void checkBoss(Boss b){
        if(r.overlaps(b.box)){
            b.speedY=0;
            b.posY=posY+32;
        }
    }
    public void render(SpriteBatch batch){
        if(anime.getFrame()!=null)
        batch.draw(anime.getFrame(), r.getX(), r.getY());
//       shape.setProjectionMatrix(game.camera.combined);
//        shape.setAutoShapeType(true);
//        shape.begin();
//        shape.set(ShapeRenderer.ShapeType.Line);
//        shape.rect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
//        
//        shape.end();
    }

    public Animation getAnime() {
        return anime;
    }

    public void setAnime(Animation anime) {
        this.anime = anime;
    }
    
}
