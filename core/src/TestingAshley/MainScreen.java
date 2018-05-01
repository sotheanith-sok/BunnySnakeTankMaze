package TestingAshley;

import TestingAshley.Utilities.AssetManagingSystem;
import TestingAshley.Utilities.ObjectFactory;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class MainScreen implements Screen {
    private Game myGame;
    private Engine engine;


    public MainScreen(Game myGame) {
        this.myGame = myGame;
        engine = ObjectFactory.getObject().getEngine();
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        AssetManagingSystem.getObject().loadAssets();

        ObjectFactory.getObject().loadEntities();
        ObjectFactory.getObject().loadSystems();


    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        engine.update(delta);

    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
