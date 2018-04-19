package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.GameObjectManager;
import com.agilewhisperers.bunnysnaketankmaze.systems.Physic;
import com.agilewhisperers.bunnysnaketankmaze.systems.Script;
import com.agilewhisperers.bunnysnaketankmaze.systems.ScriptManager;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;

public class Bullet extends GameObject implements Script, ContactListener {
    private static final float speed = 10;
    private static final int max_Lifetime = 300;
    private int lifetime = 0;

    // Default(???) Bullet constructor.
    // Pretty much for testing purposes now.
    public Bullet() {
        super(new Sprite(
                "game/bullet.png"
        ), new Body(Physic.getObject().getWorld(), 0, 0, 10, 10, 0));
        GameObjectManager.getObject().addGameObject(this);
        this.getSprite().getSprite().flip(false, true);
        getBody().getBody().setType(BodyDef.BodyType.DynamicBody);
        getIdentifier().ID = "Bullet";
        getIdentifier().isExist = true;

        //Add to scriptManager
        ScriptManager.getObject().addScriptListener(this);

        //Add to Physic engine
        Physic.getObject().addCollision(this);
        //Set tag for the object
        this.getBody().getBody().setUserData(getIdentifier());
    }

    // New bullet constructor that accepts variables to determine initial position and direction.
    public Bullet(float posX, float posY, float angle) {
        super(new Sprite(
                "game/bullet.png"
        ), new Body(Physic.getObject().getWorld()
                , posX + 0.25f / 2 * MathUtils.cosDeg(angle), posY + 0.25f / 2 * MathUtils.sinDeg(angle), 0.25f, 0.25f, 0));
        getBody().getBody().setType(BodyDef.BodyType.DynamicBody);
        GameObjectManager.getObject().addGameObject(this);

        getIdentifier().ID = "Bullet";
        getIdentifier().isExist = true;

        this.getSprite().getSprite().flip(false, true);

        //Add to scriptManager
        ScriptManager.getObject().addScriptListener(this);

        //Add to Physic engine
        Physic.getObject().addCollision(this);

        getBody().setAngle(angle);
        // Set velocity vector.
        this.getBody().getBody().setLinearVelocity(
                MathUtils.cosDeg(angle) * speed,
                MathUtils.sinDeg(angle) * speed);

        //Set tag for the object
        getBody().getBody().setUserData(getIdentifier());
    }

    /**
     * This method will be call every game loop.
     */
    @Override
    public void runObjectScript() {
        lifetime++;
        if (lifetime >= max_Lifetime) {
            GameObjectManager.getObject().removeGameObject(this);
        }
    }

    /**
     * Called when two fixtures begin to touch.
     *
     * @param contact
     */
    @Override
    public void beginContact(Contact contact) {

    }

    /**
     * Called when two fixtures cease to touch.
     *
     * @param contact
     */
    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
