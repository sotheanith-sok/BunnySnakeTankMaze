package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;

public class Player extends GameObject implements Script, Collider {

    private float rateTimer = 0;
    private float reloadTimer = 0;
    private float capacityCounter = 0;



    public Player(float posX, float posY) {
        super(new Sprite(
                "gameObjects/Player.jpg"
        ), new Body(Physic.getObject().getWorld(), posX, posY, 1, 0.8f, "Player"));
        getState().ID = "Player";
        getState().isExist = true;
        getBody().getBody().setType(BodyDef.BodyType.DynamicBody);

        Filter filter=new Filter();
        filter.categoryBits=Physic.CATEGORY_PLAYER1;
        getFixture().setFilterData(filter);
        //Add to scriptManager
        ScriptManager.getObject().addScriptListener(this);

        //Set tag for the object
        this.getBody().getBody().setUserData(getState());
        getBody().getFixture().setUserData(getState().ID);

        CollisionManager.getObject().addCollider(this);
    }


    /**
     * This method will be call every game loop.
     */
    @Override
    public void runObjectScript() {
        movement();
        fire();
    }

    private void movement() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            getBody().addAngle(getState().rotatingSpeed);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            getBody().addAngle(-getState().rotatingSpeed);
        } else {
            getBody().getBody().setAngularVelocity(0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.getBody().getBody().setLinearVelocity(MathUtils.cosDeg(getBody().getAngle()) * getState().movingSpeed, MathUtils.sinDeg(getBody().getAngle()) * getState().movingSpeed);

        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.getBody().getBody().setLinearVelocity(MathUtils.cosDeg(getBody().getAngle()) * -getState().movingSpeed, MathUtils.sinDeg(getBody().getAngle()) * -getState().movingSpeed);
        } else {
            this.getBody().getBody().setLinearVelocity(0, 0);
        }
    }

    private void fire() {
        //Normal Fire
        rateTimer += Gdx.graphics.getDeltaTime();
        if ((capacityCounter <= getState().capacity) && rateTimer > 1 / getState().RPS && Gdx.input.isKeyPressed(Input.Keys.M)) {
            GameObjectManager.getObject().getBullet().update(getBody().getBody().getPosition().x ,
                    getBody().getBody().getPosition().y,
                    getBody().getAngle(), getState().bulletSpeed);
            rateTimer = 0;
            capacityCounter++;
        }

        //Reload
        if (capacityCounter > getState().capacity) {
            reloadTimer += Gdx.graphics.getDeltaTime();
            if (reloadTimer >= getState().reloadTime) {
                capacityCounter = 0;
                reloadTimer = 0;
            }
        }
    }


    @Override
    public void startCollision(Contact contact) {

    }

    @Override
    public void endCollision(Contact contact) {

    }

    @Override
    public Fixture getFixture() {
        return getBody().getFixture();
    }
}
