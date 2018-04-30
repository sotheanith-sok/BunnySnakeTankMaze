package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.agilewhisperers.bunnysnaketankmaze.components.Animator;
import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.entities.GameObject;
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

   private Renderer() {
      camera = new OrthographicCamera();
      camera.setToOrtho(false, 1980, 1020);
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
         }
      }

      batch.end();
   }

   /**
    * Render hitbox of all gameObject. For testing purpose only.
    *
    * @param world container of all gameObjects for physic.
    */
   public void renderHitBox(World world) {

      renderer.render(world, camera.combined.cpy().scale(ppm, ppm, 0));
   }
}
