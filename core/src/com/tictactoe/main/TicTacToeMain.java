package com.tictactoe.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tictactoe.state.MainState;
import com.tictactoe.state.meta.GameStateManager;
import com.tictactoe.state.meta.State;

public class TicTacToeMain extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;

	public static final int WIDTH = 1440;
	public static final int HEIGHT = 900;

	private static GameStateManager gsm;

	private OrthographicCamera camera;
	private FitViewport viewport;

	private Vector3 mousePos;
	private Prefs prefs;

	public static final int MAIN_STATE = 0;

    private void initStates() {

        gsm = new GameStateManager(prefs);
        State.gsm = gsm;

        try {
            gsm.addState(new MainState("images/background.png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        gsm.setState(0);

    }

	@Override
	public void create () {

        Gdx.graphics.setTitle("Ultimate Tic Tac Toe");

        prefs = new Prefs();

	    camera = new OrthographicCamera();
	    viewport = new FitViewport(WIDTH, HEIGHT, camera);
	    viewport.apply();
	    camera.position.set(WIDTH/2,HEIGHT/2,0);

	    State.camera = camera;
	    State.viewport = viewport;

	    mousePos = new Vector3();

	    initStates();

		batch = new SpriteBatch();

		Gdx.input.setInputProcessor(this);
	}

	@Override
    public void resize(int x, int y) {
        viewport.update(x,y);
    }

	@Override
	public void render () {
        camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        mousePos = camera.unproject(mousePos,viewport.getScreenX(), viewport.getScreenY(),viewport.getScreenWidth(),viewport.getScreenHeight());

        int x = (int) mousePos.x;
        int y = (int) mousePos.y;
        gsm.update(x,y);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		gsm.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gsm.dispose();
	}

    @Override
    public boolean keyDown(int keycode) {
        gsm.keyDown(keycode);
	    return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        gsm.keyUp(keycode);
	    return false;
    }

    @Override
    public boolean keyTyped(char character) {
        gsm.keyTyped(character);
	    return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        mousePos = camera.unproject(mousePos,viewport.getScreenX(), viewport.getScreenY(),viewport.getScreenWidth(),viewport.getScreenHeight());

        int x = (int) mousePos.x;
        int y = (int) mousePos.y;
        gsm.clickDown(x, y, button);
	    return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        mousePos = camera.unproject(mousePos,viewport.getScreenX(), viewport.getScreenY(),viewport.getScreenWidth(),viewport.getScreenHeight());

        int x = (int) mousePos.x;
        int y = (int) mousePos.y;
        gsm.clickUp(x, y, button);
	    return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        mousePos = camera.unproject(mousePos,viewport.getScreenX(), viewport.getScreenY(),viewport.getScreenWidth(),viewport.getScreenHeight());

        int x = (int) mousePos.x;
        int y = (int) mousePos.y;
        gsm.clickDragged(x,y);
	    return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        gsm.mouseMoved(screenX, screenY);
	    return false;
    }

    @Override
    public boolean scrolled(int amount) {
        gsm.scrolled(amount);
	    return false;
    }
}
