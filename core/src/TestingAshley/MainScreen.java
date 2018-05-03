package TestingAshley;

import TestingAshley.Components.HealthBarComponent;
import TestingAshley.Utilities.AssetManagingSystem;
import TestingAshley.Utilities.ObjectFactory;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.concurrent.TimeUnit;

public class MainScreen implements Screen {
    private Game myGame;
    private Engine engine;
    private Stage stageUI;
   private HealthBarComponent healthBarComponentPlayer1,healthBarComponentPlayer2;

   private long lastUpdate = 0L;

    public MainScreen(Game myGame) {
        this.myGame = myGame;
        engine = ObjectFactory.getObject().getEngine();

       stageUI = new Stage();
      Group group=new Group();
       healthBarComponentPlayer1 =new HealthBarComponent(Gdx.graphics.getWidth()/3,25);
       healthBarComponentPlayer1.setPosition(0,0);
       group.addActor(healthBarComponentPlayer1);
       group.setPosition(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
       group.setRotation(180);
       healthBarComponentPlayer2 =new HealthBarComponent(Gdx.graphics.getWidth()/3,25);
       healthBarComponentPlayer2.setPosition(0,Gdx.graphics.getHeight()-25);
      stageUI.addActor(group);
      stageUI.addActor(healthBarComponentPlayer2);


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
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       if (System.currentTimeMillis() - lastUpdate > TimeUnit.SECONDS.toMillis(100000)) {
          healthBarComponentPlayer1.setValue(healthBarComponentPlayer1.getValue() - 0.1f);
          lastUpdate = System.currentTimeMillis();
       }
       stageUI.draw();
       stageUI.act();

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
