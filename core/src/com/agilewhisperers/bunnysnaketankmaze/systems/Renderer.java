package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.agilewhisperers.bunnysnaketankmaze.components.Animator;
import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.components.Stats;
import com.agilewhisperers.bunnysnaketankmaze.entities.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * The rendering system.
 */
public class Renderer {

    private static Renderer single_instance;
    private final float ppm = 32 / 1f;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Box2DDebugRenderer renderer;
    private float elapsedTime = 0f;

    private Stage stageUI;
    private HealthBar healthBar1, healthBar2;
    private AmmoBar ammoBar1, ammoBar2;

    private Renderer() {
        initializeHpBar();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1980, 1060);
        batch = new SpriteBatch();
        renderer = new Box2DDebugRenderer();
    }

    public static Renderer getObject() {
        if (single_instance == null) {
            single_instance = new Renderer();
        }
        return single_instance;
    }

    /**
     * Render all gameObjects
     *
     * @param alphaTime
     * @param timeStep
     */
    public void render(float alphaTime, float timeStep) {

        elapsedTime += alphaTime;
        Gdx.gl.glClearColor(0.95f, 0.95f, 0.95f, 0.95f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw((Texture) AssetManager.getObject().getAssetManager().get("gameObjects/Background.png"), 0, 0, 1980, 1080f);
        for (GameObject object : GameObjectManager.getObject().getAllGameObjects()) {
            if (object.isExist()) {
                if (object.getBody() != null && (object.getSprite() != null || object.getAnimator() != null)) {
                    Body body = object.getBody();
                    TextureRegion textureRegion = null;
                    if (object.getAnimator() != null) {
                        Animator animator = object.getAnimator();

                        textureRegion = animator.getFrame(elapsedTime);
                    } else if (object.getSprite() != null) {
                        Sprite sprite = object.getSprite();
                        textureRegion = sprite.getTexture();
                    }
                    if (textureRegion != null) {
                        //Previous stats
                        float ratio = (float) textureRegion.getRegionHeight() / (float) textureRegion.getRegionWidth();
                        Vector2 currentPosition = body.getBody().getPosition();
                        float currentAngle = body.getBody().getAngle();


                        //Current stats
                        Vector2 previousPosition = body.getPreviousPosition();
                        float previousAngle = body.getPreviousAngle();

                        batch.draw(textureRegion,
                                (Interpolation.linear.apply(previousPosition.x, currentPosition.x, alphaTime) - body.getWidth() / 2) * ppm,
                                (Interpolation.linear.apply(previousPosition.y, currentPosition.y, alphaTime) - body.getHeight() / 2) * ppm,
                                body.getWidth() / 2 * ppm,
                                body.getHeight() / 2 * ppm,
                                body.getWidth() * ppm,
                                body.getHeight() * ppm, 1f, ratio,
                                (Interpolation.circle.apply(previousAngle, currentAngle, alphaTime)) / MathUtils.degRad);
                    }

                }
                updatePlayerHPBar(object);
            }
        }
        //

        batch.end();
        stageUI.act();
        stageUI.draw();
    }

    /**
     * Render hitbox of all gameObject. For testing purpose only.
     *
     * @param world container of all gameObjects for physic.
     */
    public void renderHitBox(World world) {

        renderer.render(world, camera.combined.cpy().scale(ppm, ppm, 0));
    }

    public void updatePlayerHPBar(GameObject gameObject) {
        if (gameObject instanceof Player) {
            Stats stats = ((GameObject) gameObject.getBody().getBody().getUserData()).getStats();
            if (gameObject instanceof Player1) {
                healthBar1.setValue(stats.getCurrentHP() / stats.getMaxHP());
                ammoBar1.setValue((stats.getCapacity() - stats.getCapacityCounter()) / stats.getCapacity());
            }
            if (gameObject instanceof Player2) {
                healthBar2.setValue(stats.getCurrentHP() / stats.getMaxHP());
                ammoBar2.setValue((stats.getCapacity() - stats.getCapacityCounter()) / stats.getCapacity());
            }
        }
    }

    public void initializeHpBar() {

        stageUI = new Stage();
        Group group = new Group();
        healthBar1 = new HealthBar(Gdx.graphics.getWidth() / 3, 25);
        healthBar1.setPosition(0, 0);
        ammoBar1 = new AmmoBar(Gdx.graphics.getWidth() / 4, 10);
        ammoBar1.setPosition(0, 25);
        group.addActor(healthBar1);
        group.addActor(ammoBar1);
        group.setPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        group.setRotation(180);
        healthBar2 = new HealthBar(Gdx.graphics.getWidth() / 3, 25);
        healthBar2.setPosition(0, Gdx.graphics.getHeight() - 25);
        ammoBar2 = new AmmoBar(Gdx.graphics.getWidth() / 4, 10);
        ammoBar2.setPosition(0, Gdx.graphics.getHeight() - 35);
        stageUI.addActor(group);
        stageUI.addActor(healthBar2);
        stageUI.addActor(ammoBar2);
    }

    public void clean() {
        single_instance = null;
    }
}
