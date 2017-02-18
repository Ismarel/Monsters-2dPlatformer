/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ygame.game;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author user
 */
public class Collision {
    
    public static boolean Rect(Rectangle r1, Rectangle r2){
        float left = Math.min(r1.x, r1.x+r1.width);
        float right = Math.max(r1.x, r1.x+r1.width);
        float top = Math.min(r1.y, r1.y+r1.height);
        float bottom = Math.max(r1.y, r1.y+r1.height);
        
        float left2 = Math.min(r2.x, r2.x+r2.width);
        float right2 = Math.max(r2.x, r2.x+r2.width);
        float top2 = Math.min(r2.y, r2.y+r2.height);
        float bottom2 = Math.max(r2.y, r2.y+r2.height);
        
        return left<right2 && right < left2 && top < bottom2 && bottom > top2;
    }
}
