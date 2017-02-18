/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygames.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user
 */
public class MainMenu implements Screen{

    Game game;
    OrthographicCamera camera;
    Vector3 touch;
    
    Texture image,startbutton,statbutton,conbotton,infbutton,levbutton;
    Rectangle start, statistics, levelselect,controls,information;
    //Constructor
    public MainMenu(final Game g){
        game = g;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        touch = new Vector3();
        image=new Texture("Images/Mainmenu.png");
        startbutton=new Texture("Images/startbutton.png");
        statbutton=new Texture("Images/statisticsbutton.png");
        conbotton=new Texture("Images/controlsbutton.png");
        infbutton=new Texture("Images/infobutton.png");
        levbutton=new Texture("Images/levelbutton.png");
        start=new Rectangle(325,350,150,30);
        levelselect=new Rectangle(325,300,150,30);
        statistics=new Rectangle(325,250,150,30); 
        controls=new Rectangle(325,200,200,30);
        information=new Rectangle(325,150,150,30);
    }
    
    @Override
    public void show() {
        image=new Texture("Images/Mainmenu.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(image, 0, 0);
        game.batch.draw(startbutton, start.x, start.y);
        game.batch.draw(levbutton, levelselect.x, levelselect.y);
        game.batch.draw(statbutton, statistics.x, statistics.y);
        game.batch.draw(conbotton, controls.x, controls.y);
        game.batch.draw(infbutton, information.x, information.y);
//        game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
//        game.font.draw(game.batch, "Tap at lower half to begin!", 100, 100);
        game.batch.end();
        
        if(Gdx.input.isKeyPressed(Keys.ENTER)){
            try {
                game.setScreen(new GameScreen(game,this));
            } catch (IOException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
        }else if(Gdx.input.isKeyPressed(Keys.L)){
            game.setScreen(new LevelSelect(game,this));
            dispose();
        }
        
        if(Gdx.input.isTouched()){
            touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touch);
            
            if(start.contains(touch.x,touch.y)){
            try {
                if(!game.gamescreen.level.player.isAlive){
                    game.gamescreen.loadLevel();
                }
                game.setScreen(game.gamescreen);
                
            } catch (Exception ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
        }else if(levelselect.contains(touch.x, touch.y)){
                game.setScreen(game.levelselect);
        }else if(statistics.contains(touch.x, touch.y)){
                game.setScreen(game.statistics);
          }else if(controls.contains(touch.x, touch.y)){
                game.setScreen(game.controlscreen);
          }else if(information.contains(touch.x, touch.y)){
                game.setScreen(game.infoscreen);
          }
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
    public void hide() {
        image.dispose();
    }

    @Override
    public void dispose() {
        image.dispose();
    }
    
}
