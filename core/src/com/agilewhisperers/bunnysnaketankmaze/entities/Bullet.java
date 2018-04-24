package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class Bullet extends GameObject implements Script, Pool.Poolable, Collider {
    //In second
    private static final float max_Lifetime = 10f;
    private float lifetime = 0f;
    private boolean freed = false;

    public Bullet() {
       super();
       super.setBody( new Body(Physic.getObject().getWorld(), 5, 5, 1f, 0, "Carrot"));
      super.setSprite(new Sprite("gameObjects/Projectiles.atlas","Carrot",3));

        getBody().getBody().setType(BodyDef.BodyType.DynamicBody);
        getStats().ID = "Bullet";
        getStats().isExist = true;

        //Add to scriptManager
        ScriptManager.getObject().addScriptListener(this);

        CollisionManager.getObject().addCollider(this);

        //Set tag for the object
        this.getBody().getBody().setUserData(getStats());
        getBody().getFixtureList().get(0).setUserData(getStats().ID);
        getBody().updateFilter(Physic.CATEGORY_BULLET,(short)-1);

    }


    public void update(float posX, float posY, float angle, float speed) {
        getBody().setPosition(posX, posY);
        getBody().setAngle(angle);
        getBody().getBody().setLinearVelocity(
                MathUtils.cosDeg(angle) * speed,
                MathUtils.sinDeg(angle) * speed);
        lifetime = 0;
        freed = false;
        getBody().updateFilter(Physic.CATEGORY_BULLET,(short)~Physic.CATEGORY_PLAYER1);

    }

    /**
     * This method will be call every game loop.
     */
    @Override
    public void runObjectScript() {
        lifetime += Gdx.graphics.getDeltaTime();
        if (lifetime >= max_Lifetime && !freed) {
            GameObjectManager.getObject().freeBullet(this);
            freed = true;

        }
        if(lifetime>0.5f){
            getBody().updateFilter(Physic.CATEGORY_BULLET,(short)-1);
        }



    }


    /**
     * Resets the object for reuse. Object references should be nulled and fields may be set to default values.
     */
    @Override
    public void reset() {
        getBody().getBody().setLinearVelocity(new Vector2(0, 0));
        getBody().getBody().setAngularVelocity(0);
        getBody().setPosition(-10, -10);

    }

    @Override
    public void startCollision(Contact contact) {
        getBody().updateFilter(Physic.CATEGORY_BULLET,(short)-1);

       /* Fixture firstBody, secondBody;
        if(contact.getFixtureA()==getFixtureArray()){
            firstBody=contact.getFixtureA();
            secondBody=contact.getFixtureB();
        }else{
            secondBody=contact.getFixtureA();
            firstBody=contact.getFixtureB();
        }*/


    }

    @Override
    public void endCollision(Contact contact) {

    }

    @Override
    public Array<Fixture> getFixtureArray() {

        return getBody().getFixtureList();
    }


}

