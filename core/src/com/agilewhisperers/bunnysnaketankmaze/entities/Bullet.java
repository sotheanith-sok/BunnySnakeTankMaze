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
import com.badlogic.gdx.utils.Pool;

public class Bullet extends GameObject implements Script, Pool.Poolable, Collider {
    //In second
    private static final float max_Lifetime = 10f;
    private float lifetime = 0f;
    private boolean freed = false;

    public Bullet() {
        super(new Sprite(
                "gameObjects/Bullet.png"
        ), new Body(Physic.getObject().getWorld(), -1, -1, 0.5f, 10, "Bullet"));
        GameObjectManager.getObject().addGameObject(this);
        this.getSprite().getSprite().flip(false, true);
        getBody().getBody().setType(BodyDef.BodyType.DynamicBody);
        getState().ID = "Bullet";
        getState().isExist = true;

        Filter filter=new Filter();
        filter.categoryBits=Physic.CATEGORY_PLAYER1;
        filter.categoryBits=1;

        getFixture().setFilterData(filter);

        //Add to scriptManager
        ScriptManager.getObject().addScriptListener(this);

        CollisionManager.getObject().addCollider(this);

        //Set tag for the object
        this.getBody().getBody().setUserData(getState());
        getBody().getFixture().setUserData(getState().ID);
    }


    public void update(float posX, float posY, float angle, float speed) {
        getBody().getBody().setType(BodyDef.BodyType.DynamicBody);
        getBody().setPosition(posX, posY);
        getBody().setAngle(angle);
        getBody().getBody().setLinearVelocity(
                MathUtils.cosDeg(angle) * speed,
                MathUtils.sinDeg(angle) * speed);
        Filter filter=getFixture().getFilterData();
        filter.categoryBits=Physic.CATEGORY_PLAYER1;
        getFixture().setFilterData(filter);
        getFixture().refilter();
        lifetime = 0;
        freed = false;

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
        turnOnCollision();
    }


    /**
     * Resets the object for reuse. Object references should be nulled and fields may be set to default values.
     */
    @Override
    public void reset() {
        getBody().getBody().setLinearVelocity(new Vector2(0, 0));
        getBody().getBody().setAngularVelocity(0);
        getBody().setPosition(-10, -10);
        getBody().getBody().setType(BodyDef.BodyType.StaticBody);

    }

    @Override
    public void startCollision(Contact contact) {
       turnOnCollision();


        Fixture firstBody, secondBody;
        if(contact.getFixtureA()==getFixture()){
            firstBody=contact.getFixtureA();
            secondBody=contact.getFixtureB();
        }else{
            secondBody=contact.getFixtureA();
            firstBody=contact.getFixtureB();
        }


    }

    @Override
    public void endCollision(Contact contact) {

    }

    @Override
    public Fixture getFixture() {
        return getBody().getFixture();
    }
    public void turnOnCollision(){
        if(lifetime>0.05f){
            Filter filter=getFixture().getFilterData();
            filter.categoryBits=Physic.CATEGORY_BULLET;
            filter.maskBits=1;
            getFixture().setFilterData(filter);
            getFixture().refilter();
        }
    }
}

