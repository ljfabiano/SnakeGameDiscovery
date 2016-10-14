package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by jfabiano on 10/13/2016.
 */
public class MyInputProcessor implements InputProcessor {

    MyGdxGame myGame = null;

    public MyInputProcessor(MyGdxGame myGame) {
        this.myGame = myGame;
    }

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        System.out.println("click *****");
        if (button == Input.Buttons.LEFT) {
            // Some stuff
//            mouseX = Gdx.input.getX();
//            mouseY = Gdx.input.getY();
            System.out.println("**** clicking the mouse left button.");

            myGame.setLastTouchedPositionX(Gdx.input.getX());
            myGame.setLastTouchedPositionY(Gdx.input.getY());

            System.out.println("last touched x = " + myGame.getLastTouchedPositionX());
            System.out.println("last touched y = " + myGame.getLastTouchedPositionY());




            return true;
        }

        return false;
    }
    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    public boolean keyDown (int keycode) {
        return false;
    }

    public boolean keyUp (int keycode) {
        return false;
    }

    public boolean keyTyped (char character) {
        return false;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean mouseMoved (int x, int y) {
        return false;
    }

    public boolean scrolled (int amount) {
        return false;
    }
}


