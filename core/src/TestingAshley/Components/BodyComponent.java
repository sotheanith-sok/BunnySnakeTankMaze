package TestingAshley.Components;

import com.agilewhisperers.bunnysnaketankmaze.systems.BodyEditorLoader;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class BodyComponent implements Component {
   private Body body;
   private float width;
   private float height;
   private Vector2 previousPosition;
   private float previousAngle;

   public BodyComponent(World world, float posX, float posY, float scale, String name) {


      BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("gameObjects/Data.json"));
      this.width = 1 * scale;
      this.height = 1 * scale;
      BodyDef bodyDef = new BodyDef();
      bodyDef.type = BodyDef.BodyType.KinematicBody;
      bodyDef.position.set(posX + width / 2, posY + height / 2);
      body = world.createBody(bodyDef);





      //Body material type and stuff...
      FixtureDef fixtureDef = new FixtureDef();
      fixtureDef.density = 1;
      loader.attachFixture(body, name, fixtureDef, scale);

      previousPosition = body.getPosition().cpy();
      previousAngle = body.getAngle();
   }

   public Body getBody() {
      return body;
   }

   public void setBody(Body body) {
      this.body = body;
   }

   public Vector2 getPreviousPosition() {
      return previousPosition;
   }

   public void setPreviousPosition(Vector2 previousPosition) {
      this.previousPosition = previousPosition;
   }

   public float getPreviousAngle() {
      return previousAngle;
   }

   public void setPreviousAngle(float previousAngle) {
      this.previousAngle = previousAngle;
   }

   public void updatePreviousPositionAndAngle() {
      previousPosition.set(body.getPosition().x, body.getPosition().y);
      previousAngle = body.getAngle();
   }

   public float getWidth() {
      return width;
   }

   public float getHeight() {
      return height;
   }

}
