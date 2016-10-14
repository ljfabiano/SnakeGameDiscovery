package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.actor;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture imgTail;
	Texture imgHead;
	Texture imgSnakeHead;
	float index;
	ArrayList<Float> crumbsX = new ArrayList<Float>();
	ArrayList<Float> crumbsY = new ArrayList<Float>();

	private float lastTouchedPositionX = 0;
	private float lastTouchedPositionY = 0;

	boolean collision = false;

	float x, y, xv, yv;

	float xAutomatic, yAutomatic, xTail, yTail;

	float directionX = 1;

	float directionY = 0;

	float headWidth = 256;
	float headHeight = 256;

	float screenHeight;
	float screenWidth;

	int dimensionOfCell = 10;
	Array[] xArray = new Array[((int)screenWidth)/dimensionOfCell];
	Array[] yArray = new Array[((int)screenHeight)/dimensionOfCell];

	float mouseX;
	float mouseY;
	boolean didClick = false;

	Stage stage;
	TextButton button;
	TextButton.TextButtonStyle textButtonStyle;
	BitmapFont font;
	Rectangle playBoundaryLeft;
	Rectangle playBoundaryRight;
	Rectangle playBoundaryTop;
	Rectangle playBoundaryBottom;
	Rectangle snakeHeadCollisionDetection;
	Rectangle potentialSnakeHead;
	ShapeRenderer myShape;

	Camera myCamera;

	Sprite head;
	Sprite tail;
	Sprite snakeHead;
	//private int score;
	String yourScoreName;
	BitmapFont yourBitmapFontName;
	int buttonSelectCounter = 0;
	//Skin skin;
	//TextureAtlas buttonAtlas;

	//fastest it will go
	static final float MAX_VELOCITY = 100;

	public MyGdxGame()
	{
		super();

	}

	public float getLastTouchedPositionX() {
		return lastTouchedPositionX;
	}

	public void setLastTouchedPositionX(float lastTouchedPositionX) {
		this.lastTouchedPositionX = lastTouchedPositionX;
	}

	public float getLastTouchedPositionY() {
		return lastTouchedPositionY;
	}

	public void setLastTouchedPositionY(float lastTouchedPositionY) {
		this.lastTouchedPositionY = lastTouchedPositionY;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		//img = new Texture("Minecraft Experimental Skin1.8.png");
		imgHead = new Texture("badlogic.jpg");
		imgTail = new Texture("badlogic.jpg");
		imgSnakeHead = new Texture("SnakeHead.png");
		Sprite bottomBoundary = new Sprite();
		bottomBoundary.setSize(80, 1);
		bottomBoundary.setPosition(0f, 0f);
		playBoundaryBottom = new Rectangle(bottomBoundary.getX(), bottomBoundary.getY(), bottomBoundary.getWidth(), bottomBoundary.getHeight());
		//playBoundaryBottom = new Rectangle(-2f, -2f, 802f, 1f);
		Actor boundary;
		head = new Sprite(imgHead);
		tail = new Sprite(imgTail);
		snakeHead = new Sprite();
		//snakeHead.setColor(0f, 255f, 0f, 0f);
		//snakeHead.setSize(15, 15);
		potentialSnakeHead = new Rectangle();

		MyInputProcessor myProcessor = new MyInputProcessor(this);
		Gdx.input.setInputProcessor(myProcessor);

		myShape = new ShapeRenderer();

		//myCamera = new Camera


		//score = 0;
		yourScoreName = "score: 0";
		yourBitmapFontName = new BitmapFont();

		stage = new Stage();
//		Gdx.input.setInputProcessor(stage);
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
		//button.setOrigin(100, 0);
		button.setPosition(75, 0);
		stage.addActor(button);

		//stage.addActor();

		button.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				buttonSelectCounter++;
				System.out.println("Button Pressed");
				button.setText("button has been selected " + buttonSelectCounter + " times.");
			}
		});

		button.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				buttonSelectCounter++;
				System.out.println("Button Pressed");
				button.setText("button has been selected " + buttonSelectCounter + " times.");
			}
		});

	}
	//method is called every frame
	@Override
	public void render () {

		//move();
		moveAutomatic();
		if(detectWallCollision(xAutomatic, yAutomatic) == true)
		{
			System.out.println("collision with bottom boundary");
		}
//		button.addListener(new ChangeListener() {
//			@Override
//			public void changed (ChangeListener.ChangeEvent event, Actor actor) {
//				System.out.println("Button Pressed");
//			}
//		});
//boolean isOverlapping =
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		didClick = createRectangle();
		if (didClick == true);
		{
//			myShape.begin(ShapeRenderer.ShapeType.Filled);
//			myShape.setColor(0, 1, 0, 1);
//			myShape.rect(mouseX, mouseY, 5, 5);
//			//myShape.circle(x, y, radius);
//			myShape.end();
		}
//		if(head.getBoundingRectangle().overlaps(playBoundaryBottom))
//		{
//			//System.out.println("head is over boundary");
//			//System.out.println("head bounding rectangle stats: " + head.getBoundingRectangle().getX() + " " + head.getBoundingRectangle().getY() + " " + head.getBoundingRectangle().getWidth() + " " + head.getBoundingRectangle().getHeight());
//		}
//		if(playBoundaryBottom.overlaps(head.getBoundingRectangle()))
//		{
//			System.out.println("boundary is over head");
//		}

		//info for creating a game board

//		float squareWidth = camera.viewportWidth / squaresOnWidth;
//		float squareHeight = camera.viewportHeight / squaresOnHeight;
//		square.setWidth(squareWidth);
//		square.setHeight(squareHeight);
//		batch.begin(); `
//		for(int y = 0; y < squaresOnHeight; y++){
//			for(int x = 0; x < squaresOnWidth; x++){
//				square.setX(x * squareWidth);
//				square.setY(y * squareHeight);
//				square.draw(batch);
//			}
//		}
//		batch.end();
		float squareWidth = 160;
		float squareHeight = 120;

//		square.setHeight(squareHeight);

		batch.begin();

//		ShapeRenderer myShape = new ShapeRenderer();
//		myShape.begin(ShapeRenderer.ShapeType.Filled);
//		myShape.setColor(0, 1, 0, 1);
//
//		screenHeight = Gdx.graphics.getHeight();
//		screenWidth = Gdx.graphics.getWidth();
//		//System.out.println("screen height = " + screenHeight + " screen width = " + screenWidth);
//		//height = 480 width = 640 default
//		float drawPositionY = screenHeight - lastTouchedPositionY;
//
//		myShape.rect(lastTouchedPositionX, drawPositionY, 100, 100);
//		//myShape.circle(x, y, radius);
//		myShape.end();

		ShapeRenderer myShape = new ShapeRenderer();
		myShape.begin(ShapeRenderer.ShapeType.Filled);
		myShape.setColor(0, 1, 0, 1);
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		float drawPositionY = screenHeight - lastTouchedPositionY;
		//myShape.rect(lastTouchedPositionX, drawPositionY, 100, 100);

		Cell myCell = createGridCell(lastTouchedPositionX, drawPositionY);
		myShape.rect(myCell.getX(), myCell.getY(), 10, 10);
		myShape.end();

//		ShapeRenderer myShape2 = new ShapeRenderer();
//		myShape2.begin(ShapeRenderer.ShapeType.Filled);
//		myShape2.setColor(0, 0, 1, 1);
//		myShape2.rect(0, 0, 25, 25);
//		//myShape.circle(x, y, radius);
//		myShape2.end();

//		ShapeRenderer myShape3 = new ShapeRenderer();
//		myShape3.begin(ShapeRenderer.ShapeType.Filled);
//		myShape3.setColor(0, 0, 1, 1);
//		myShape3.rect(300, 300, 100, 100);
//		//myShape.circle(x, y, radius);
//		myShape3.end();

		//batch.draw(img, x, y);
		//batch.draw(imgHead, xAutomatic + directionX, yAutomatic + directionY);
//		batch.draw(imgHead, xAutomatic, yAutomatic);
//		if((index - headWidth) < 0)
//		{
//			batch.draw(imgTail, crumbsX.get(0), crumbsY.get(0));
//		}
//		else {
//			batch.draw(imgTail, crumbsX.get((int)(index - headWidth)), crumbsY.get((int)(index - headHeight)));
//		}
		//batch.draw(imgSnakeHead, 300, 300);
		//batch.imgHead, xAutomatic, yAutomatic);
		//System.out.println("the height/width of tail " + head.getHeight() + " " + head.getWidth());
//		if(tail.getX() + tail.getWidth() == head.getX()) {
//			batch.draw(imgTail, (xAutomatic) - tail.getWidth(), yAutomatic);
//		}
//		else if (tail.getY() + tail.getHeight() == head.getY())
//		{
//			batch.draw(imgTail, (xAutomatic), (yAutomatic) - imgTail.getHeight());
//		}
//		else if (tail.getX() == head.getX() + head.getWidth())
//		{
//			batch.draw(imgTail, (xAutomatic) + tail.getWidth(), yAutomatic);
//		}
//		else if (tail.getY() == head.getY() + head.getHeight())
//		{
//			batch.draw(imgTail, (xAutomatic), (yAutomatic) + imgTail.getHeight());
//		}
//		else
//		{
//			batch.draw(imgTail, 0, 0);
//		}



		yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		yourBitmapFontName.draw(batch, yourScoreName, 25, 100);
		batch.end();

		super.render();
		stage.draw();
		//reset the collision boolean so it can be tested again next render cycle
		collision = false;
		didClick = false;

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
		//Delta time is time between the current frame and the last frame
		//Think of it like using "per second" in place of deltatime
		//This is used to avoid issues of different FPS values for different machines for consistently
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

		xTail = xAutomatic - tail.getWidth();
		yTail = yAutomatic - tail.getHeight();
		//directionX = Gdx.graphics.getDeltaTime();
		//directionY = Gdx.graphics.getDeltaTime();

		xAutomatic += directionX;
		yAutomatic += directionY;

		crumbsX.add(xAutomatic);
		crumbsY.add(yAutomatic);
		index++;

//		if(head.getBoundingRectangle().overlaps(playBoundaryBottom))
//		{
//			System.out.println("Game over man! Game Over!");
//		}

//		if (xAutomatic > imgTail.getWidth())
//		{
//
//		}

		//yv = decelerate(yv);
		//xv = decelerate(xv);

//		if(raindrop.overlaps(bucket)) {
//			score++;
//			yourScoreName = "score: " + score;
//			dropSound.play();
//			iter.remove();
	}

	boolean createRectangle() {

		if (Gdx.input.isTouched()) {
			//directionX = 0; directionY = 1;
//			mouseX = Gdx.input.getX();
//			mouseY = Gdx.input.getY();
//			System.out.println("clicking the mouse left button.");
			return true;
		}
		else
		{
			return false;
		}
	}

	Cell createGridCell(float x, float y)
	{
		Cell myCell = new Cell();
		for(int index = 0; index >= xArray.length; index++)
		{
			if(index * 10 > x)
			{
				--index;
				myCell.setX(index * 10);
				break;
			}
		}
		for(int index = 0; index >= yArray.length; index++)
		{
			if(index * 10 > y)
			{
				--index;
				myCell.setY(index * 10);
				break;
			}
		}
		//Cell myCell = new Cell();
		return myCell;
	}
	//Designed to detect collision with the bottom edge of the screen's rectangle
	boolean detectWallCollision(float x, float y) {
		snakeHeadCollisionDetection = new Rectangle(x, y, headWidth, headHeight);
		if(snakeHeadCollisionDetection.overlaps(playBoundaryBottom))
		{
			collision = true;
		}
		return collision;
	}

}
