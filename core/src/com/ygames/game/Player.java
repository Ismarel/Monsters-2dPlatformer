/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author user
 */
public class Player {
    
    int posX,posY,speedX,speedY,width,height,ladderspeed;
    int health;
    public Animation anime,heal;
    GameScreen game;
    public Rectangle Box,top,bot,left,right;
    public boolean isJumped,isOnGround,isHealing,trigger;
    ShapeRenderer shape;
    public boolean isAlive = true;
    public boolean movleft,movright,isOnLadder,up;
    public Sound jump;
    Game gam;
    int healSoundTime=0;
    
    public Player(final GameScreen g,Game ga){
        jump = Gdx.audio.newSound(Gdx.files.internal("Music/Jump.wav"));
        game=g;
        gam=ga;
        posX=0;
        posY=0;
        health=100;
        speedX=0;
        speedY=0;
        ladderspeed=0;
        movleft=false;
        movright=false;
        shape = new ShapeRenderer();
        width=70;
        height=70;
        Box = new Rectangle();
        top= new Rectangle();
        bot= new Rectangle();
        left=new Rectangle();
        right=new Rectangle();
        
        isAlive=true;
        isJumped=true;
        isOnGround = false;
        isOnLadder=false;
        up=true;
        //anime = new Animation();
    }
    
    public void update(float time){
        anime.update(8);
        if(isHealing){
            heal.update(8);
            if(healSoundTime==0){
                game.level.heal_sound.play();healSoundTime++;
            }          
        }else{
            healSoundTime=0;
        }
        posX+=speedX;
        
        if(isOnLadder){
            posY+=ladderspeed;
        }else{
            posY+=speedY;
        }
        if(health <=0){
            isAlive=false;
            gam.addDeaths();
        }
        
            speedY-=1; //Gravity
        

        //##################Prevent from going out of bounds
       if(posX<=0){
           posX=0;
       }else if(posX+width>game.level.width*40){
           posX=posX-10;
       }
       if(game.lockCam){ //lock when boss locks screen
           if(posX<game.camera.position.x-400){
               posX = posX+10;
           }else if(posX+width > game.camera.position.x+400){
               posX=posX-10;
           }
       }
        //##################################################
        //checkCollision();
        
        top.set(posX+15, posY, width-25, 35);
        bot.set(posX+25, posY-35, width-35, 35);
        right.set(posX+31,posY-10,width-30,35);
        left.set(posX,posY-10,width-40,35);
        Box.set(posX-80, posY+80, width+140, height-240);
    }
    
    void checkCollision(){
        
    }
    
    public void render(SpriteBatch batch){
        batch.draw(anime.getFrame(), posX, bot.getY());
        if(isHealing){
            batch.draw(heal.getFrame(), posX+20, posY+50);
        }
//        shape.setAutoShapeType(true);
//        shape.setColor(Color.BLACK);
//        shape.setProjectionMatrix(game.camera.combined);
//        shape.begin();
//        shape.set(ShapeRenderer.ShapeType.Line);
//        shape.rect(Box.getX(), Box.getY(), Box.getWidth(), Box.getHeight());
//        shape.rect(top.getX(), top.getY(), top.getWidth(), top.getHeight());
//        shape.rect(bot.getX(), bot.getY(), bot.getWidth(), bot.getHeight());
//            shape.rect(left.getX(), left.getY(), left.getWidth(), left.getHeight());
//            shape.rect(right.getX(), right.getY(), right.getWidth(), right.getHeight());
//        shape.end();
    }
    public void setPosition(int x,int y){
        posX=x;
        posY=y;
        Box.set(posX-80, posY+80, width+140, height-240);
        top.set(posX, posY, width, 35);
        bot.set(posX, posY-35, width, 35);
        right.set(posX+20,posY,width,35);
        left.set(posX,posY,width-20,35);
    }

    public void moveLeft(){
        movleft=true;
        movright=false;
        speedX=-5;
    }
    
    public void moveRight(){
         movleft=false;
        movright=true;
        speedX=5;
    }
    
    public void jump(){
        if(!isJumped ){
            speedY=15;
            isJumped=true;
            jump.play(0.3f);
            isOnGround=false;
        }
        
    }
    
    public void moveUp(){
        ladderspeed=2;
    }
    public void moveDown(){
        speedY=-2;
    }
    public Animation getAnime() {
        return anime;
    }

    public void setAnime(Animation anime) {
        this.anime = anime;
    }

    public Rectangle getBox() {
        return Box;
    }

    public void setBox(Rectangle Box) {
        this.Box = Box;
    }

    public Rectangle getTop() {
        return top;
    }

    public void setTop(Rectangle top) {
        this.top = top;
    }

    public Rectangle getBot() {
        return bot;
    }

    public void setBot(Rectangle bot) {
        this.bot = bot;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    public void dispose(){
        jump.dispose();
    }
}
