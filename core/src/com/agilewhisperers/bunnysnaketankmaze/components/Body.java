package com.agilewhisperers.bunnysnaketankmaze.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Body {

   private com.badlogic.gdx.physics.box2d.Body body;
   private Fixture fixture;
   private float width,high;

   public Body(World world,float posX, float posY, float width, float high) {
      this.width=width;
      this.high=high;
      BodyDef bodyDef = new BodyDef();
      bodyDef.type = BodyDef.BodyType.KinematicBody;
      bodyDef.position.set(posX, posY);
      body = world.createBody(bodyDef);

      //Collider Box
      PolygonShape shape=new PolygonShape();
      shape.setAsBox(this.width/2,this.high/2,new Vector2(width/2,high/2),0);

      //Body material type and stuff...
      FixtureDef fixtureDef=new FixtureDef();
      fixtureDef.shape=shape;
      fixtureDef.density=1;
      fixture=body.createFixture(fixtureDef);

   }

   public com.badlogic.gdx.physics.box2d.Body getBody() {
      return body;
   }

   public void setBody(com.badlogic.gdx.physics.box2d.Body body) {
      this.body = body;
   }

   public Fixture getFixture() {
      return fixture;
   }

   public void setFixture(Fixture fixture) {
      this.fixture = fixture;
   }

   public float getWidth() {
      return width;
   }

   public void setWidth(float width) {
      this.width = width;
      ((PolygonShape)(fixture.getShape())).setAsBox(width,high);
   }

   public float getHigh() {
      return high;
   }

   public void setHigh(float high) {
      this.high = high;
      ((PolygonShape)(fixture.getShape())).setAsBox(width,high);
   }
}
