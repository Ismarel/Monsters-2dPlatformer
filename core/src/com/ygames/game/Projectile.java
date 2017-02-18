/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygames.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;



/**
 *
 * @author user
 */
public class Projectile {
    
    int posX,posY,playerX,playerY,speedX,speedY,width,height;
    public boolean visible=true;
    Rectangle box,r;
    int timetolive=500;
    Game game;
    Animation anime;
    ShapeRenderer shape;
    //Constructor
    public Projectile(int x, int y,int px, int py,Game game){
        posX=x;
        posY=y;
        playerX=px;
        playerY=py;
        this.game=game;
        width=50;
        height=20;
        speedX=0;
        speedY=0;
        timetolive=500;
        r = new Rectangle(posX,posY,width,height);
        box = new Rectangle(posX-50,posY-50,width+100,height+100);
        shape=new ShapeRenderer();
    }
    
    public void update(){
        anime.update(2);
        posX+=speedX;
        posY+=speedY;
        
        if(posX <-43 || posX+width > game.gamescreen.camera.position.x+400){
            visible = false;
        }else if(posY <-200 || posY+height > game.gamescreen.camera.position.y+240){
            visible = false;
        }else if(timetolive <=0){
            visible = false;
        }else{
            visible = true;
        }
        
        
        
        if(playerX>posX ){//if player is ahead
            speedX=3;
        }else{           //if player is behind
            speedX=-3;
        }
        
        if(playerY<posY ){//if player is above
            
                speedY=-1;
       
        }else if(playerY>posY ){//if player is below
          
                speedY=1;
      
        }
        
        
        
        r.setX(posX);r.setY(posY);r.setWidth(width);r.setHeight(height);
        box.setX(posX-50);box.setY(posY-50);box.setWidth(width+100);box.setHeight(height+100);
        timetolive--;
        
        //if(box.overlaps(game.gamescreen.level.player.Box)){
            checkPlayerCollision();
        //}
    }
    
    public void render(SpriteBatch g){
        g.draw(anime.getFrame(), posX, posY);
                shape.setAutoShapeType(true);
        //shape.setColor(Color.BLACK);
//        shape.setProjectionMatrix(game.gamescreen.camera.combined);
//        shape.begin();
//        shape.set(ShapeRenderer.ShapeType.Line);
//        shape.rect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
//        shape.rect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
//        
//        shape.end();
    }
    
    void checkPlayerCollision(){
        if(r.overlaps(game.gamescreen.level.player.top)){
            game.gamescreen.level.player.health-=1;
        }
    }

    public Animation getAnime() {
        return anime;
    }

    public void setAnime(Animation anime) {
        this.anime = anime;
    }
    
    
}
