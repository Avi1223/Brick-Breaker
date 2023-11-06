package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.components.Ball;
import com.mygdx.game.components.Bricks;
import com.mygdx.game.components.Platform;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	int score=0;
	BitmapFont font;
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	Platform platform;
	Ball ball;
	Bricks[] bricks;
	FitViewport viewport;
	Random colorNum=new Random();
	
	@Override
	public void create () {
		viewport = new FitViewport(640, 480);
		viewport.apply();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		batch=new SpriteBatch();
		platform = new Platform((int) (viewport.getWorldWidth() / 2 - 20), 10);
		ball = new Ball((int) (viewport.getWorldWidth() / 2), (int) (viewport.getWorldHeight() / 2), 10);
		bricks = new Bricks[80];
		for (int i=0; i<5;i++) {
			for (int j=0;j<16;j++) {
				int x = j * 40;
				int y = (int) (viewport.getWorldHeight() - (i * 20)-20);
				bricks[i*16+j]=new Bricks(x,y,colorNum.nextInt());
			}
		}
	}

	@Override
	public void render () {
		viewport.update(640, 480);
		ScreenUtils.clear(0,0,0,1);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		Score();
		platform.movement();
		update();
		for (Bricks brick : bricks) {
			if (!brick.isRemove()) {
				brick.draw(shapeRenderer);
			}
		}
		platform.draw(shapeRenderer);
		ball.draw(shapeRenderer);
		shapeRenderer.end();
	}

	private void update(){
		ball.update(Gdx.graphics.getDeltaTime());
		if (bricks.length==score||ball.getBounds().y+ball.getBounds().radius < 0)
			WinOrLose();

		if (ball.getBounds().x - ball.getBounds().radius < 0 || ball.getBounds().x + ball.getBounds().radius > viewport.getScreenWidth())
			ball.reverseX();

		if (ball.getBounds().y + ball.getBounds().radius > viewport.getWorldHeight())
			ball.reverseY();

		if (Intersector.overlaps(ball.getBounds(),platform.getBounds()))
			ball.reverseY();

		for (Bricks brick : bricks) {
			if (Intersector.overlaps(ball.getBounds(),brick.getBounds())) {
				if(!brick.isRemove()) {
					brick.hit();
					score++;
					ball.reverseY();
				}
			}
		}
	}
	private void Score() {
		batch.begin();
		shapeRenderer.setColor(Color.BLACK);
		font.getData().setScale(2);
		shapeRenderer.rect((float) viewport.getScreenWidth()/2, (float) viewport.getScreenHeight()/2, 100, 30);
		font.draw(batch, "Score: " + score, (float)Gdx.graphics.getWidth()/2-50, (float) Gdx.graphics.getHeight()/2-50);
		batch.end();
	}
	private void WinOrLose(){
		String str;
		batch.begin();
		shapeRenderer.setColor(Color.BLACK);
		font.getData().setScale(3);
		shapeRenderer.rect((float) viewport.getScreenWidth()/2, (float) viewport.getScreenHeight()/2, 100, 30);
		if(bricks.length==score)
			str="You Win!";
		else
			str="You Lose";
		font.draw(batch, str, (float)Gdx.graphics.getWidth()/2-85, (float) Gdx.graphics.getHeight()/2-100);
		batch.end();
		try {
			Thread.sleep(3000);
			dispose();
		}catch(Exception e){
			dispose();
		}
	}
	@Override
	public void dispose() {
		shapeRenderer.dispose();
	}
}
