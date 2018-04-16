package com.agilewhisperers.bunnysnaketankmaze.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * 
 * This is a component used to interact with physic engine.
 */
public class Body {
   private com.badlogic.gdx.physics.box2d.Body body;
   private static final double DEGREE_TO_RADIANS=(double)(Math.PI/180);
   private Fixture fixture;
   private float width,height,angle;

   public Body(World world,float posX, float posY, float width, float height, float angle) {
      this.width=width;
      this.height=height;
      this.angle=angle;
      BodyDef bodyDef = new BodyDef();
      bodyDef.type = BodyDef.BodyType.DynamicBody;
      bodyDef.position.set(posX+width/2, posY+height/2);
      body = world.createBody(bodyDef);

      //Collider Box
      PolygonShape shape=new PolygonShape();
      shape.setAsBox(this.width/10,this.height/10);

      //Body material type and stuff...
      FixtureDef fixtureDef=new FixtureDef();
      fixtureDef.shape=shape;
      fixtureDef.density=1;
      fixture=body.createFixture(fixtureDef);
   }

   // Body constructor for Bullet objects. Hitbox is appropriately tiny.
   public Body(World world,float posX, float posY, float width, float height, float angle, int bullet) {
      this.width=width;
      this.height=height;
      this.angle=angle;
      BodyDef bodyDef = new BodyDef();
      bodyDef.type = BodyDef.BodyType.DynamicBody;
      bodyDef.position.set(posX+width/2, posY+height/2);
      body = world.createBody(bodyDef);

      // Collider Box
      PolygonShape shape=new PolygonShape();
      shape.setAsBox(0.1f,0.1f);

      //Body material type and stuff...
      FixtureDef fixtureDef=new FixtureDef();
      fixtureDef.shape=shape;
      fixtureDef.density=1;
      fixture=body.createFixture(fixtureDef);

    }

    /**
     * Get the physic engine representation of this object.
     * @return body
     */
   public com.badlogic.gdx.physics.box2d.Body getBody() {
      return body;
   }

    /**
     * Get the physical definition of this object in the engine.
     * @return Fixture
     */
   public Fixture getFixture() {
      return fixture;
   }

    /**
     * Get the width of the hitbox.
     * @return width
     */
   public float getWidth() {
      return width;
   }

    /**
     * Set the width of the hitbox
     * @param width 
     */
   public void setWidth(float width) {
      this.width = width;
      ((PolygonShape)(fixture.getShape())).setAsBox(width,height);
   }

    /**
     * Get the height of the hitbox.
     * @return height
     */
   public float getHeight() {
      return height;
   }

    /**
     * Set the height of the hitbox.
     * @param height
     */
   public void setHeight(float height) {
      this.height = height;
      ((PolygonShape)(fixture.getShape())).setAsBox(width,height);
   }

    /**
     * Set the angle of this object
     * @param degree
     */
   public void setAngle(float degree){
       angle=(float)(degree*DEGREE_TO_RADIANS);
       body.setTransform(body.getPosition(),angle);
   }

    /**
     * Incriminate the angle of this object by a certain amount.
     * @param degree
     */
    public void addAngle(float degree){
        angle=(float)(degree*DEGREE_TO_RADIANS);
        body.setTransform(body.getPosition(),body.getAngle()+angle);
    }

    /**
     * Get the angle of this object
     * @return degree
     */
   public float getAngle(){
       return (float)(body.getAngle()/DEGREE_TO_RADIANS);
   }

    /**
     * Modify the position of this object.
     * @param x
     * @param y
     */
   public void setPosition(float x, float y){
       body.setTransform(new Vector2(x+width/2,y+height/2),body.getAngle());
   }
}
