package com.mygdx.game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Platform{
    int x, y;
    Rectangle bounds;
    public Platform(int x, int y) {
        this.x = x;
        this.y = y;
        bounds=new Rectangle(x,y,60,20);
    }

    public void movement() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && x >= 0) {
            x -= 6;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && x + bounds.width <= 800) {
            x += 6;
        }
        bounds.setPosition(x, y);
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);

    }
    public Rectangle getBounds() {
        return bounds;
    }
}