package com.mygdx.game.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Ball {
    Vector2 velocity;
    Circle bounds;

    public Ball(int x,int y,int radius){
        bounds=new Circle(x,y,radius);
        velocity=new Vector2(2,2).nor();
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.circle(bounds.x, bounds.y, bounds.radius);
    }
    public Circle getBounds() {
        return bounds;
    }
    public void reverseY() {
        velocity.y = -velocity.y;
    }
    public void update(float delta) {
        bounds.x -= velocity.x*200*delta;
        bounds.y += velocity.y*200*delta;
    }

    public void reverseX() {
        velocity.x = -velocity.x;
    }
}
