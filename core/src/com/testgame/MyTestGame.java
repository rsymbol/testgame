package com.testgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyTestGame extends Game {

	SpriteBatch batch;
	private TextureRegion heroRegion;
	private float heroX, heroY, heroWidth, heroHeight, scale, rotation;

	class MyInputListener implements InputProcessor {

		@Override
		public boolean keyDown(int keycode) {
			switch (keycode) {
				case Input.Keys.UP:
					heroY+=5;
					break;
				case Input.Keys.DOWN:
					heroY-=5;
					break;
				case Input.Keys.LEFT:
					heroX-=5;
					break;
				case Input.Keys.RIGHT:
					heroX+=5;
					break;
				case Input.Keys.EQUALS:
					scale+=0.1f;
					break;
				case Input.Keys.MINUS:
					scale-=0.1f;
					break;
			}
			return false;
		}

			@Override
			public boolean keyUp(int keycode) {
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				heroX = screenX - heroWidth/2;
				heroY = Gdx.graphics.getHeight() - screenY - heroHeight/2;
				return true;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				heroX = screenX - heroWidth/2;
				heroY = Gdx.graphics.getHeight() - screenY - heroHeight/2;
				return true;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return false;
			}

			@Override
			public synchronized boolean scrolled(int amount) {
				for (int i=1; i<181; i++) {
					rotation += amount * i;
					try{
						Thread.sleep(100);
						render();
					} catch (InterruptedException e){}

				}
				return true;
			}
		}

	@Override
	public void create () {
		batch = new SpriteBatch();
		heroRegion = new TextureRegion(new Texture("texture.png"));
		heroX = 100;
		heroY = 150;
		heroWidth = 100;
		heroHeight = 100;
		scale = 1;
		rotation = 0;

		Gdx.input.setInputProcessor(new MyInputListener());
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(heroRegion, heroX, heroY, heroWidth/2, heroHeight/2, heroWidth,heroHeight, scale, scale, rotation);
		batch.end();
	}

}
