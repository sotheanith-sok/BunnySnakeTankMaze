package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
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

        //Add to scriptManager
        ScriptManager.getObject().addScriptListener(this);

        //Add collision
        CollisionManager.getObject().addCollider(this);

        //Set tag for the object
        this.getBody().getBody().setUserData(getState());
        getBody().getFixture().setUserData(getState().ID);
    }

    // New bullet constructor that accepts variables to determine initial position and direction.
    public Bullet(float posX, float posY, float angle, float speed) {
        super(new Sprite(
                "gameObjects/Bullet.png"
        ), new Body(Physic.getObject().getWorld()
                , posX + 0.25f / 2 * MathUtils.cosDeg(angle), posY + 0.25f / 2 * MathUtils.sinDeg(angle), 0.25f, 0.25f, "Bullet"));
        getBody().getBody().setType(BodyDef.BodyType.DynamicBody);
        GameObjectManager.getObject().addGameObject(this);

        getState().ID = "Bullet";
        getState().isExist = true;

        this.getSprite().getSprite().flip(false, true);

        //Add to scriptManager
        ScriptManager.getObject().addScriptListener(this);

        //Add collision
        CollisionManager.getObject().addCollider(this);

        getBody().setAngle(angle);
        // Set velocity vector.
        this.getBody().getBody().setLinearVelocity(
                MathUtils.cosDeg(angle) * speed,
                MathUtils.sinDeg(angle) * speed);

        //Set tag for the object
        getBody().getBody().setUserData(getState());
        getBody().getFixture().setUserData(getState().ID);
    }

    public void update(float posX, float posY, float angle, float speed) {
        getBody().getBody().setType(BodyDef.BodyType.DynamicBody);
        getBody().setPosition(posX, posY);
        getBody().setAngle(angle);
        getBody().getBody().setLinearVelocity(
                MathUtils.cosDeg(angle) * speed,
                MathUtils.sinDeg(angle) * speed);
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

    /**
     * Call when the collision begin
     *
     * @param contact
     */
    @Override
    public void beginCollision(Contact contact) {
        System.out.println("Bullet is collided with something");
    }

    /**
     * Call when collision end
     *
     * @param contact
     */
    @Override
    public void endCollision(Contact contact) {

    }

    /**
     * Get the fixture of this collider
     *
     * @return Fixture
     */
    @Override
    public Fixture getFixture() {
        return getBody().getFixture();
    }
}
