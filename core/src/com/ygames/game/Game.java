package com.ygames.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends com.badlogic.gdx.Game {
	SpriteBatch batch;
	FileHandle levels;
        Preferences pref;
        BufferedReader reader;
        public ArrayList<Integer> unlockedLevels;
	public BitmapFont font;
        String read;
        WinScreen winscreen;
        DeathScreen deathscreen;
        GameScreen gamescreen;
        LevelSelect levelselect;
        PauseScreen pausescreen;
        Statistics statistics;
        ControlsScreen controlscreen;
        InfoScreen infoscreen;
        public MainMenu menu;
        
	@Override
	public void create (){   //load images and sound assets
		batch = new SpriteBatch();
                font = new BitmapFont();
             
                levels = Gdx.files.local("Data/levels.txt");
                pref = Gdx.app.getPreferences("Statistics");
                menu = new MainMenu(this);
            try {
                gamescreen = new GameScreen(this,menu);
                
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
                levelselect = new LevelSelect(this,menu);
                winscreen=new WinScreen(this,gamescreen,GameScreen.coins);
                deathscreen=new DeathScreen(this,menu);
                pausescreen=new PauseScreen(this,gamescreen);
                statistics=new Statistics(this,gamescreen);
                controlscreen=new ControlsScreen(this);
                infoscreen=new InfoScreen(this);
                unlockedLevels = new ArrayList();
                for(int i=0;i<levels.length();i++){
                    unlockedLevels.add(i);
                }
                this.setScreen(menu);
	}

	@Override
	public void render () {
            
                super.render();
		
	}

        void addLevel(int l){
                if(!(unlockedLevels.size()>GameScreen.totalLevels)){
                    if(!unlockedLevels.contains(l)){
                        unlockedLevels.add(l);
                        levels.writeString(String.valueOf(l), true); 
                    }
               
                }
                
       
        }
        
        void addScore(int s){
             
        
         if(pref.getInteger("Coins")==0){
             pref.putInteger("Coins", s);
             pref.flush();
         }else{
             int i = pref.getInteger("Coins")+s;
             pref.putInteger("Coins", i);
              pref.flush();
         }
         
            
        }
        int getScore(){

          return pref.getInteger("Coins");
            
        }
        int getDeaths(){
            return pref.getInteger("Deaths");
        }
        void addDeaths(){
            if(pref.getInteger("Deaths")==0){
                pref.putInteger("Deaths", 1);
                pref.flush();
            }else{
                int d=pref.getInteger("Deaths")+1;
                pref.putInteger("Deaths", d);
                pref.flush();
            }
                
            
            
        }
        
    @Override
    public void resize(int width, int height) {
        
    }

    @Override
    public void pause() {

        }
    
                    
        
    

    @Override
    public void resume() {
        
    }

    @Override
    public void dispose() {
          
        batch.dispose();
        winscreen.dispose();
        gamescreen.dispose();
        deathscreen.dispose();
        menu.dispose();
        levelselect.dispose();
        pausescreen.dispose();
    }
}
