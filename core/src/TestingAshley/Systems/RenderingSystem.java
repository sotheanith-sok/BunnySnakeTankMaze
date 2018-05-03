package TestingAshley.Systems;

import TestingAshley.Components.*;
import TestingAshley.Utilities.AssetManagingSystem;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
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

public class RenderingSystem extends IteratingSystem {
    private final float ppm = 32 / 1f;
    private ComponentMapper<BodyComponent> bodyComponents = ComponentMapper.getFor(BodyComponent.class);
    private ComponentMapper<SpriteComponent> spriteComponents = ComponentMapper.getFor(SpriteComponent.class);
    private ComponentMapper<AnimatorComponent> animatorComponents = ComponentMapper.getFor(AnimatorComponent.class);
    private ComponentMapper<TagComponent> tagComponents = ComponentMapper.getFor(TagComponent.class);
    private ComponentMapper<TimeComponent> timeComponents = ComponentMapper.getFor(TimeComponent.class);
   private ComponentMapper<HealthBarComponent> hpComponents = ComponentMapper.getFor(HealthBarComponent.class);


    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Box2DDebugRenderer renderer;
    private float elapsedTime = 0f;
    private World world;


    public RenderingSystem(World world) {
        super(Family.all(BodyComponent.class, TagComponent.class, TimeComponent.class).get(), 50);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1980, 1100);
        batch = new SpriteBatch();
        renderer = new Box2DDebugRenderer();
        this.world = world;
    }

    /**
     * This method is called on every entity on every update call of the EntitySystem. Override this to implement your system's
     * specific processing.
     *
     * @param entity    The current Entity being processed
     * @param deltaTime The delta time between the last and current frame
     */
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (tagComponents.get(entity).isVisible()) {
            BodyComponent body = bodyComponents.get(entity);
            TextureRegion textureRegion = null;
            AnimatorComponent animatorComponent = animatorComponents.get(entity);
            SpriteComponent spriteComponent = spriteComponents.get(entity);
            if (animatorComponent != null) {
                textureRegion = animatorComponent.getFrame(elapsedTime);
            } else if (spriteComponent != null) {
                textureRegion = spriteComponent.getTexture();
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
                        (Interpolation.linear.apply(previousPosition.x, currentPosition.x, timeComponents.get(entity).alphaTime) - body.getWidth() / 2) * ppm,
                        (Interpolation.linear.apply(previousPosition.y, currentPosition.y, timeComponents.get(entity).alphaTime) - body.getHeight() / 2) * ppm,
                        body.getWidth() / 2 * ppm,
                        body.getHeight() / 2 * ppm,
                        body.getWidth() * ppm,
                        body.getHeight() * ppm, 1f, ratio,
                        (Interpolation.circle.apply(previousAngle, currentAngle, timeComponents.get(entity).alphaTime)) / MathUtils.degRad);
            }

        }


    }

    private void renderHitBox() {
        renderer.render(world, camera.combined.cpy().scale(ppm, ppm, 0));
    }

    @Override
    public void update(float deltaTime) {
        elapsedTime += deltaTime;
        Gdx.gl.glClearColor(0.95f, 0.95f, 0.95f, 0.95f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw((Texture) AssetManagingSystem.getObject().getAssetManager().get("gameObjects/Background.png"), 0, 0, camera.viewportWidth, camera.viewportHeight);
        super.update(deltaTime);
        batch.end();

        renderHitBox();
    }
}

