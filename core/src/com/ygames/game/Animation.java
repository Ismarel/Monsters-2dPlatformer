/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygames.game;

import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Animation {
    
    int currentFrame,totalFrames;
    float animeTime;
    float elapsedTime;
  
    
    ArrayList<Texture> texArray;
    
    //Constructor
    public Animation(){
        
        currentFrame=0;
        animeTime=50;
        elapsedTime=0;
        totalFrames=0;
        
        texArray = new ArrayList();
    }
    
    public void update(float time){
        
        if(totalFrames >1){
            elapsedTime+=time;
        if(elapsedTime>animeTime){
            elapsedTime=0; 
            currentFrame++;
            
            if(currentFrame >totalFrames-1){
                currentFrame = 0;
            }
        }
    }
    }
    public Texture getFrame(){
        return texArray.get(currentFrame);
    }
    
    public void addFrame(Texture t){
        texArray.add(t);
        totalFrames++;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public void setTotalFrames(int totalFrames) {
        this.totalFrames = totalFrames;
    }
    
    
}
