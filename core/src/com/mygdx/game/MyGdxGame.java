package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.actor;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	Texture imgAutomatic;

	float x, y, xv, yv;

	float xAutomatic, yAutomatic, xvAutomatic, yvAutomatic;

	float directionX = 1;

	float directionY = 0;

	Stage stage;
	TextButton button;
	TextButton.TextButtonStyle textButtonStyle;
	BitmapFont font;

	//private int score;
	String yourScoreName;
	BitmapFont yourBitmapFontName;
	int buttonSelectCounter = 0;
	//Skin skin;
	//TextureAtlas buttonAtlas;

	//fastest it will go
	static final float MAX_VELOCITY = 100;

	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		//img = new Texture("Minecraft Experimental Skin1.8.png");
		imgAutomatic = new Texture("badlogic.jpg");

		//score = 0;
		yourScoreName = "score: 0";
		yourBitmapFontName = new BitmapFont();

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		font = new BitmapFont();
		//skin = new Skin();
		//buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
		//skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		//textButtonStyle.up = skin.getDrawable("up-button");
		//textButtonStyle.down = skin.getDrawable("down-button");
		//textButtonStyle.checked = skin.getDrawable("checked-button");
		button = new TextButton("Button1", textButtonStyle);
		stage.addActor(button);

		button.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				buttonSelectCounter++;
				System.out.println("Button Pressed");
				button.setText("button has been selected " + buttonSelectCounter + " times.");
			}
		});

	}

	@Override
	public void render () {

		//move();
		moveAutomatic();
//		button.addListener(new ChangeListener() {
//			@Override
//			public void changed (ChangeListener.ChangeEvent event, Actor actor) {
//				System.out.println("Button Pressed");
//			}
//		});

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, x, y);
		batch.draw(imgAutomatic, xAutomatic + directionX, yAutomatic + directionY);
		yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		yourBitmapFontName.draw(batch, yourScoreName, 25, 100);
		batch.end();

		super.render();
		stage.draw();

	}

	float decelerate(float velocity) {
		float deceleration = 0.99f; // the closer to 1, the slower the deceleration
		velocity *= deceleration;
		if (Math.abs(velocity) < 1) {
			velocity = 0;
		}
		return velocity;
	}

	void move() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			yv = MAX_VELOCITY;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = MAX_VELOCITY * -1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv = MAX_VELOCITY;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xv = MAX_VELOCITY * -1;
		}

		y += yv * Gdx.graphics.getDeltaTime();
		x += xv * Gdx.graphics.getDeltaTime();

		yv = decelerate(yv);
		xv = decelerate(xv);

//		if(raindrop.overlaps(bucket)) {
//			score++;
//			yourScoreName = "score: " + score;
//			dropSound.play();
//			iter.remove();
	}
//was in the original project, but not the
//	@Override
//	public void dispose () {
//		batch.dispose();
//		img.dispose();
//	}
//to move one of the sprites in a snakelike way(it is automatically moving, and controlling it changes direction)
	void moveAutomatic() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			directionX = 0; directionY = 1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			directionX = 0; directionY = -1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			directionX = 1; directionY = 0;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			directionX = -1; directionY = 0;
		}

		//directionX = Gdx.graphics.getDeltaTime();
		//directionY = Gdx.graphics.getDeltaTime();

		yAutomatic += directionY;
		xAutomatic += directionX;

		//yv = decelerate(yv);
		//xv = decelerate(xv);

//		if(raindrop.overlaps(bucket)) {
//			score++;
//			yourScoreName = "score: " + score;
//			dropSound.play();
//			iter.remove();
	}

}
