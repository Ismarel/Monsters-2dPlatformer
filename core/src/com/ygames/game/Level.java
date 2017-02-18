/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Level {
    
    public Player player;
    ArrayList<String> Lines;
    public static int score,coins;
    int totalcoins;
    ArrayList<Tile> TileMap;
    ArrayList<Projectile> Projectiles;
    public static ArrayList<Enemy> Enemies;
    public static ArrayList<Hopper> Hopper;
    public static ArrayList<Follower> Follower;
    public static ArrayList<Coins> Coins;
    public static ArrayList<Flyers> Flyers;
    public static ArrayList<Boss> Boss;
    Texture right,left,heal1,heal2,tile1,tile2,tile3,tile4,tile6,cement,p_idle,p_idle2,walk1,walk2,walk3,walk4,walk5,hopp,hopp2,fol_1,fol_2,coin1,coin2,mud,spike;
    Texture flyer1,flyer2,bg,fboss1,pro1,pro2,fboss2,fboss3,fboss4,fboss5,toppanel,heal,invisible,grass,ladder,climb1,climb2,crystal,crystal2,p_idle11,p_idle22,jump11,jump12,walk11,walk22,walk33,walk44,walk55,enemy1,enemy2,exit,boss_1,boss_2,boss_3,boss_4;
    public Animation tile_1,tile_2,Health,boss1,boss2,Fboss1,Fboss2,Invisible,rock,Crystal,Climb,Ladder,Grass,Tile6,Cement,player_idle,player_idle2,walk,walkL,enemy,exitTile,hopper,follower,coin,flyer,Heal,jump,jump1,Mud,Spike,Projectile;
    public int width=0;
    public int height=0;
    GameScreen game;
    Game gam;
    Iterator<Coins> cIter;
    Iterator<Boss> bIter;
    Iterator<Tile> tIter;
    Iterator<Projectile> pIter;
    public Music music;
    public Sound coin_sound,evil,horn,kiss,dog,heal_sound;
    
    //Constructor
    public Level(final GameScreen g,Game ga){
        game=g;
        gam=ga;
        player = new Player(game,ga);
        Lines = new ArrayList();
        TileMap= new ArrayList();
        Hopper=new ArrayList();
        Follower=new ArrayList();
        Coins=new ArrayList();
        Flyers=new ArrayList();
        rock=new Animation();
        tile_1 = new Animation();
        Enemies = new ArrayList();
        Boss=new ArrayList();
        Projectiles=new ArrayList();
        
        Invisible=new Animation();
        player_idle = new Animation();
        player_idle2 = new Animation();
        walk = new Animation();
        enemy = new Animation();
        exitTile = new Animation();
        hopper=new Animation();
        follower=new Animation();
        coin=new Animation();
        flyer=new Animation();
        Heal=new Animation();
        jump=new Animation();
        jump1=new Animation();
        walkL=new Animation();
        Cement=new Animation();
        tile_2=new Animation();
        boss1=new Animation();
        boss2=new Animation();
        Mud=new Animation();
        Spike=new Animation();
        Grass=new Animation();
        Crystal=new Animation();
        Tile6=new Animation();
        Ladder=new Animation();
        Climb=new Animation();
        Fboss1=new Animation();
        Fboss2=new Animation();
        Projectile=new Animation();
        Health = new Animation();
            score=gam.getScore();
    
        coins=0;
        gam.font.setColor(Color.RED);
    }
    
    public void loadResources(){
        music= Gdx.audio.newMusic(Gdx.files.internal("Music/bg"+GameScreen.currentLevel+".mp3"));
        coin_sound=Gdx.audio.newSound(Gdx.files.internal("Music/coin.mp3")); //https://freesound.org/people/timgormly/sounds/170147/
        horn=Gdx.audio.newSound(Gdx.files.internal("Music/teleport.wav"));
        kiss=Gdx.audio.newSound(Gdx.files.internal("Music/kiss.wav"));
        heal_sound=Gdx.audio.newSound(Gdx.files.internal("Music/heal.mp3"));
//        evil=Gdx.audio.newSound(Gdx.files.internal("Music/evil.mp3"));
        dog=Gdx.audio.newSound(Gdx.files.internal("Music/Splash.mp3"));
        heal1 = new Texture("Images/heal1.png");
        heal2 = new Texture("Images/heal2.png");
        tile1 = new Texture("Images/tile1.png");
        tile2 = new Texture("Images/tile2.png");
        tile3 = new Texture("Images/rock.png");
        tile4 = new Texture("Images/tile3.png");
        tile6 = new Texture("Images/tile6.png");
        left = new Texture("Images/left.png");
        right = new Texture("Images/right.png");
        pro1 = new Texture("Images/pro1.png");
        pro2 = new Texture("Images/pro2.png");
        fboss1= new Texture("Images/finalboss1.png");
        fboss2= new Texture("Images/finalboss2.png");
        fboss3= new Texture("Images/finalboss3.png");
        fboss4= new Texture("Images/finalboss4.png");
        fboss5= new Texture("Images/finalboss5.png");
        toppanel= new Texture("Images/toppanel.png");
        invisible = new Texture("Images/invisible.png");
        ladder = new Texture("Images/ladder.png");
        mud = new Texture("Images/mud.png");
        climb1 = new Texture("Images/climb1.png");
        climb2 = new Texture("Images/climb2.png");
        crystal = new Texture("Images/crystal.png");
        crystal2 = new Texture("Images/crystal2.png");
        grass = new Texture("Images/grass.png");
        spike = new Texture("Images/spike.png");
        exit = new Texture("Images/exit.png");
        boss_1 = new Texture("Images/boss1"+GameScreen.currentLevel+".png");
        boss_2 = new Texture("Images/boss2"+GameScreen.currentLevel+".png");
        boss_3 = new Texture("Images/boss3"+GameScreen.currentLevel+".png");
        boss_4 = new Texture("Images/boss4"+GameScreen.currentLevel+".png");
        
        
      
        cement = new Texture("Images/cement.png");
        enemy1 = new Texture("Images/enemy_1.png");
        enemy2 = new Texture("Images/enemy_2.png");
        p_idle = new Texture("Images/idle1.png");
        p_idle2 = new Texture("Images/idle2.png");
        p_idle11 = new Texture("Images/idle11.png");
        p_idle22 = new Texture("Images/idle22.png");
        walk1 = new Texture("Images/walk_1.png");
        walk2 = new Texture("Images/walk_2.png");
        walk3 = new Texture("Images/walk_3.png");
        walk4 = new Texture("Images/walk_4.png");
        walk5 = new Texture("Images/walk_5.png");
        walk11 = new Texture("Images/walk11.png");
        walk22 = new Texture("Images/walk22.png");
        walk33 = new Texture("Images/walk33.png");
        walk44 = new Texture("Images/walk44.png");
        walk55 = new Texture("Images/walk55.png");
        hopp=new Texture("Images/hopper_1.png");
        hopp2=new Texture("Images/hopper_2.png");
        fol_1=new Texture("Images/follower1.png");
        fol_2=new Texture("Images/follower2.png");
        coin1=new Texture("Images/gem1.png");
        coin2=new Texture("Images/gem2.png");
        flyer1=new Texture("Images/flyer1.png");
        flyer2=new Texture("Images/flyer2.png");
        heal=new Texture("Images/heal.png");
        bg = new Texture("Images/bg"+GameScreen.currentLevel+".png");
        jump11=new Texture("Images/jump1.png");
        jump12=new Texture("Images/jump2.png");
        heal=new Texture("Images/heal.png");
        
        //tile_1.addFrame(tile2);
        tile_1.addFrame(tile1);
        //tile_1.addFrame(tile3);
        tile_2.addFrame(tile4);
        
        rock.addFrame(tile3);
        Invisible.addFrame(invisible);
        Crystal.addFrame(crystal);
        Crystal.addFrame(crystal2);
        Cement.addFrame(cement);
        exitTile.addFrame(exit);
        
        Climb.addFrame(climb1);
        Climb.addFrame(climb2);
        Ladder.addFrame(ladder);
        Grass.addFrame(grass);
        Mud.addFrame(mud);
        Spike.addFrame(spike);
        Tile6.addFrame(tile6);
        player_idle.addFrame(p_idle);
        player_idle.addFrame(p_idle);
        player_idle.addFrame(p_idle);
        player_idle.addFrame(p_idle2);
        
        player_idle2.addFrame(p_idle11);
        player_idle2.addFrame(p_idle11);
        player_idle2.addFrame(p_idle11);
        player_idle2.addFrame(p_idle22);
        
        walk.addFrame(walk1);
        walk.addFrame(walk2);
        walk.addFrame(walk3);
        walk.addFrame(walk4);
        walk.addFrame(walk5);
        
        walkL.addFrame(walk11);
        walkL.addFrame(walk22);
        walkL.addFrame(walk33);
        walkL.addFrame(walk44);
        walkL.addFrame(walk55);
        
        jump.addFrame(jump11);
        jump1.addFrame(jump12);
        
        enemy.addFrame(enemy1);
        enemy.addFrame(enemy2);
        
        hopper.addFrame(hopp);
        hopper.addFrame(hopp2);
        
        follower.addFrame(fol_1);
        follower.addFrame(fol_2);
        
        coin.addFrame(coin1);
        coin.addFrame(coin2);
        
        flyer.addFrame(flyer1);
        flyer.addFrame(flyer2);
        
        Heal.addFrame(heal);
        Health.addFrame(heal1);
        Health.addFrame(heal2);
        Projectile.addFrame(pro1);
        Projectile.addFrame(pro2);
        boss1.addFrame(boss_1);
        boss1.addFrame(boss_2);
        boss1.addFrame(boss_3);
        boss2.addFrame(boss_4);
        
        Fboss1.addFrame(fboss1);
        Fboss1.addFrame(fboss2);
        Fboss1.addFrame(fboss3);
        
        Fboss2.addFrame(fboss4);
        Fboss2.addFrame(fboss5);
    }
    
    public void load_map(String level) throws IOException{
        
        char c;
        FileHandle file = Gdx.files.internal(level);
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.read()));
        
        while(true){
            String line = reader.readLine();
            
            if(line == null){             
                reader.close();
                break;
            }
            
            if(!line.startsWith("!")){
                Lines.add(line);
                width = Math.max(width, line.length());
            }
        }
        height = Lines.size();
        
        //Load Tile map array
        for(int i=0;i<height;i++){
            String line = Lines.get(i);
            for(int j=0;j<width;j++){
                
                if(j<line.length()){
                    c=line.charAt(j);
                    loadTiles(j,i,40,40,c);
                }
            }
        }//loading ends
        
    }
    
    void loadTiles(int x,int y,int width,int height,char type){
        Tile t;
        Enemy e;
        switch(type){
            case '#':
                t = new Tile(x,y,40,34,type,game,gam);
                t.setAnime(tile_1);
                TileMap.add(t);
            break;
            case 'g':
                t = new Tile(x,y,40,34,type,game,gam);
                t.setAnime(Heal);
                TileMap.add(t);
            break;
             case 'i':
                t = new Tile(x,y,40,34,type,game,gam);
                t.setAnime(Cement);
                TileMap.add(t);
            break;
            case 'r':
                t = new Tile(x,y,40,34,type,game,gam);
                t.setAnime(rock);
                TileMap.add(t);
            break;
            case 'j':
                t = new Tile(x,y,40,34,type,game,gam);
                t.setAnime(tile_2);
                TileMap.add(t);
            break;
            case 'm':
                t = new Tile(x,y,40,34,type,game,gam);
                t.setAnime(Mud);
                TileMap.add(t);
            break;
            case 's':
                t = new Tile(x,y,40,34,type,game,gam);
                t.setAnime(Spike);
                TileMap.add(t);
            break;
            case 'n':
                t = new Tile(x,y,40,34,type,game,gam);
                t.setAnime(Grass);
                TileMap.add(t);
            break;
            case 'q':
                t = new Tile(x,y,40,34,type,game,gam);
                t.setAnime(Crystal);
                TileMap.add(t);
            break;
            case '6':
                t = new Tile(x,y,40,34,type,game,gam);
                t.setAnime(Tile6);
                TileMap.add(t);
            break;
            case 'u':
                t = new Tile(x,y,40,34,type,game,gam);
                t.setAnime(Ladder);
                TileMap.add(t);
            break;
            case 'v':
                t = new Tile(x,y,40,34,type,game,gam);
                t.setAnime(Invisible);
                TileMap.add(t);
            break;
            case 'w':
                t = new Tile(x,y,40,34,type,game,gam);
                t.setAnime(Invisible);
                TileMap.add(t);
            break;
            case 'e':
                t = new Tile(x,y,40,34,type,game,gam);
                t.setAnime(exitTile);
                TileMap.add(t);
            break;
            case 'h':
               Hopper h = new Hopper(x,y,40,34,game);
               h.setPlayer(player);
               h.setAnime(hopper);
               Hopper.add(h);
            break;
            case 'f':
                Follower f = new Follower(x,y,40,34,game);
                f.setPlayer(player);
                f.setAnime(follower);
                Follower.add(f);
            break;
            case 'x':
                e = new Enemy(x,y,40,34,game);
                e.setAnime(enemy);
                e.setPlayer(player);
                Enemies.add(e);
            break;
            case 'b':
                Flyers b = new Flyers(x,y,40,40,game);
                b.setPlayer(player);
                b.setAnime(flyer);
                Flyers.add(b);
            break;
            case 'z':
                Boss z = new Boss(x,y,40,40,game);
                z.setPlayer(player);
                z.setAnime(boss1);
                Boss.add(z);
            break;
            case 'y':
                Boss v = new Boss(x,y,40,40,game);
                v.isFinal=true;
                v.width=200;
                v.height=200;
                v.health=500;
                v.setPlayer(player);
                v.setAnime(Fboss1);
                Boss.add(v);
            break;
            case 'p':
                player.setPosition(x*40, y*40);
                player.setAnime(player_idle);
                player.heal = Health;
            break;
            case 'c':
                Coins c=new Coins(x,y,40,34,game);
                c.setPlayer(player);
                c.setAnime(coin);
                Coins.add(c);
            break;
        }
    }
    //Update level###################################
    public void update(float time){
        updatePlayer(time); 
        updateTiles(time);
        updateEnemies(time);
        updateCoins(time);
        updateBoss(time);
        updateProjectiles(time);
    }
    //########################################
    void updateTiles(float time){
        tIter = TileMap.iterator();
        
        while(tIter.hasNext()){
            Tile t=tIter.next();
            if(!t.visible && !t.isAlive){
                tIter.remove();
            }else{
                 t.update(time);
            }
           
        }
    
    }
    
    void updateEnemies(float time){
        for(Enemy e: Enemies){
            e.update(time);
        }
        
        for(Hopper h:Hopper){
            h.update(time);
        }
        
        for(Follower f:Follower){
            f.update(time);
        }
        
        for(Flyers f:Flyers){
            if(f.posX<game.camera.position.x+400)
            f.update(time);
        }
    }
    
    void updatePlayer(float time){  
        if(player.speedX>0 && player.speedY==0){
            player.setAnime(walk);
        }else if(player.speedX<0 && player.speedY==0){
            player.setAnime(walkL);
        }else if(!player.isOnGround && player.speedX>0 && player.speedY>0){
            player.setAnime(jump);
        }else if(!player.isOnGround && player.speedX<0 && player.speedY>0){
            player.setAnime(jump1);
        }else if(player.isOnLadder){
            player.setAnime(Climb);
        }else if(player.movleft){
            player.setAnime(player_idle2);
        }else if(player.movright){
            player.setAnime(player_idle);
        }
        player.update(time);
     
    }
    
    void updateCoins(float time){
        cIter = Coins.iterator();
        
        while(cIter.hasNext()){
            Coins c = cIter.next();
            if(!c.collected){
                c.update(time);
            }else{
                cIter.remove();
            }
        }
    }
    void updateBoss(float time){
        bIter = Boss.iterator();
        while(bIter.hasNext()){
            Boss b=bIter.next();
            if(b.isAlive){
                b.update(time);
                if(!b.isFinal){
                    if(b.stomp){
                        b.setAnime(boss2);
                    }else{
                        b.setAnime(boss1);
                    }
                }else{
                    if(b.stomp){
                        b.setAnime(Fboss2);
                    }else{
                        b.setAnime(Fboss1);
                }
                }
                
            }else{
                bIter.remove();
                game.lockCam=false;
            }
        }
        
    }
    void updateProjectiles(float time){
        pIter=Projectiles.iterator();
        while(pIter.hasNext()){
            Projectile p = pIter.next();
            if(p.visible){
                p.update();
            }else{
                pIter.remove();
            }
        }
    }
    //Draw level elements and objects
    public void render(SpriteBatch batch) throws IOException{
        gam.batch.draw(bg, game.camera.position.x-400, game.camera.position.y-300); //draw background
        
        for(Tile t:TileMap){
            if(t.visible && t.type!='6')
            t.render(batch);
        }
       player.render(batch);

       for(Enemy e:Enemies){
            if(e.visible)
            e.render(batch);
        }
        
        for(Hopper h:Hopper){
            if(h.visible)
            h.render(batch);
        }
        
        for(Follower f:Follower){
            if(f.visible)
            f.render(batch);
        }
        
        for(Coins c:Coins){
            if(c.visible)
            c.render(batch);
        }
        for(Flyers f:Flyers){
            if(f.visible)
            f.render(batch);
        }
        for(Boss b:Boss){
            if(b.visible)
                b.render(batch);
        }
       
       for(Tile t:TileMap){              //render tile6 at last
            if(t.visible && t.type=='6')
            t.render(batch);
        }
        for(Projectile p:Projectiles){
            p.render(batch);
        }
//        healthRect.setProjectionMatrix(game.camera.combined);
//        healthRect.setAutoShapeType(true);
//        healthRect.set(ShapeRenderer.ShapeType.Filled);
//        healthRect.setColor(Color.GREEN);
//        healthRect.begin();
//        healthRect.rect(game.camera.position.x-300, 170, player.health, 40);
//        healthRect.end();
        //gam.batch.draw(toppanel, game.camera.position.x-400, game.camera.position.y+215); 
        gam.font.draw(batch, "Level "+String.valueOf(GameScreen.currentLevel+1), game.camera.position.x-150, game.camera.position.y+230);
        gam.font.draw(batch, "Coins "+String.valueOf(coins), game.camera.position.x-250, game.camera.position.y+230);
        gam.font.draw(batch, "Health "+String.valueOf(player.health), game.camera.position.x-350, game.camera.position.y+230);
        gam.font.draw(batch, "Total coins "+score, game.camera.position.x-50, game.camera.position.y+230);
        gam.font.draw(batch, "Pause", game.camera.position.x+340, game.camera.position.y+230);
        gam.batch.draw(left, game.camera.position.x-400, game.camera.position.y-220);
        gam.batch.draw(right, game.camera.position.x-200, game.camera.position.y-220);

    }
    
    public void dispose(){
        player.dispose();
        music.stop();
        music.dispose();
        heal.dispose();
        heal1.dispose();
        heal2.dispose();
        tile1.dispose();
        tile2.dispose();
        tile3.dispose();
        cement.dispose();
        coin1.dispose();
        coin2.dispose();
        enemy2.dispose();
        enemy1.dispose();
        tile4.dispose();
        p_idle.dispose();
        p_idle2.dispose();
        p_idle11.dispose();
        p_idle22.dispose();
        walk1.dispose();
        walk2.dispose();
        walk3.dispose();
        walk4.dispose();
        walk5.dispose();
        hopp.dispose();
        fol_1.dispose();
        fol_2.dispose();
        flyer1.dispose();
        flyer2.dispose();
        jump11.dispose();
        jump12.dispose();
        walk22.dispose();
        walk33.dispose();
        walk44.dispose();
        walk55.dispose();
        exit.dispose();
        boss_1.dispose();
        boss_2.dispose();
        boss_3.dispose();
        boss_4.dispose();
        fboss1.dispose();
        fboss2.dispose();
        fboss3.dispose();
        fboss4.dispose();
        fboss5.dispose();
        climb1.dispose();
        climb2.dispose();
        
        mud.dispose();
        crystal.dispose();
        crystal2.dispose();
        grass.dispose();
        spike.dispose();
        tile6.dispose();
        pro1.dispose();
        pro2.dispose();
        left.dispose();
        right.dispose();
        ladder.dispose();
        invisible.dispose();
        bg.dispose();
        coin_sound.dispose();
        horn.dispose();
        kiss.dispose();
        dog.dispose();
        heal_sound.dispose();
//        evil.dispose();
        TileMap.clear();
        Enemies.clear();
        Flyers.clear();
        Hopper.clear();
        Follower.clear();
        Coins.clear();
        Boss.clear();
//        cIter=null;
//        player=null;
        player.dispose();
        
    }
    
}
