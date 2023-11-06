package com.mygdx.game.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Bricks {
    int col;
    Rectangle bounds;
    boolean remove;

    public Bricks(int x, int y,int colorNum){
        bounds = new Rectangle(x, y, 40, 20);
        remove=false;
        this.col=colorNum;
    }
    public void draw(ShapeRenderer shapeRenderer) {
        if(!remove){
            if(col%5==0)
                shapeRenderer.setColor(Color.CORAL);
            else if(col%3==0)
                shapeRenderer.setColor(Color.GOLD);
            else if(col%4==0)
                shapeRenderer.setColor(Color.ROYAL);
            else if(col%2==0)
                shapeRenderer.setColor(Color.RED);
            else
                shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public void hit() {
        remove=true;
    }
    public boolean isRemove(){
        return remove;
    }
}
