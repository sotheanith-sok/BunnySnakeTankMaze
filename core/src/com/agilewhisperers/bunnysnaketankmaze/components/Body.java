package com.agilewhisperers.bunnysnaketankmaze.components;

import com.agilewhisperers.bunnysnaketankmaze.systems.BodyEditorLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * This is a component used to interact with physic engine.
 */
public class Body {
    private com.badlogic.gdx.physics.box2d.Body body;
    private Fixture fixture;
    private float width, height, angle;


   public Body(World world, float posX, float posY,float scale, float angle, String name) {
      BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("gameObjects/Data.json"));
      this.width = 1*scale;
      this.height = 1*scale;
      this.angle = angle;
      BodyDef bodyDef = new BodyDef();
      bodyDef.type = BodyDef.BodyType.KinematicBody;
      bodyDef.position.set(posX+width/2, posY+height/2);
      body = world.createBody(bodyDef);


      //Body material type and stuff...
      FixtureDef fixtureDef = new FixtureDef();
      fixtureDef.density = 1;
      loader.attachFixture(body,name,fixtureDef,scale);
   }

    /**
     * Get the physic engine representation of this object.
     *
     * @return body
     */
    public com.badlogic.gdx.physics.box2d.Body getBody() {
        return body;
    }

    /**
     * Get the physical definition of this object in the engine.
     *
     * @return Fixture
     */
    public Fixture getFixture() {
        return fixture;
    }

    /**
     * Get the width of the hitbox.
     *
     * @return width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Get the height of the hitbox.
     *
     * @return height
     */
    public float getHeight() {
        return height;
    }


    /**
     * Incriminate the angle of this object by a certain amount.
     *
     * @param degree
     */
    public void addAngle(float degree) {
        angle = (float) (degree * MathUtils.degRad);
        body.setTransform(body.getPosition(), body.getAngle() + angle);
    }

    /**
     * Get the angle of this object
     *
     * @return degree
     */
    public float getAngle() {
        return body.getAngle() / MathUtils.degRad;
    }

    /**
     * Set the angle of this object
     *
     * @param degree
     */
    public void setAngle(float degree) {
        angle = (float) (degree * MathUtils.degRad);
        body.setTransform(body.getPosition(), angle);
    }

    /**
     * Modify the position of this object.
     *
     * @param x
     * @param y
     */
    public void setPosition(float x, float y) {
        body.setTransform(new Vector2(x + width / 2, y + height / 2), body.getAngle());
    }
}
