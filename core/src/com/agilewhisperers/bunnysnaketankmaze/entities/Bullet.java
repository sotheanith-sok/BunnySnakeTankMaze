package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.components.Stats;
import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.utils.Pool;

import static com.agilewhisperers.bunnysnaketankmaze.systems.Physic.*;

public class Bullet extends GameObject implements Script, Pool.Poolable, Collider {
    //In second
    private static final float max_Lifetime = 10f;
    private float lifetime = 0f;
    private boolean freed = false;

    public Bullet() {
        super();
        super.setBody(new Body(Physic.getObject().getWorld(), 5, 5, 1f, 0, "Carrot"));
        super.setSprite(new Sprite("gameObjects/Projectiles.atlas", "Carrot", 3));

        this.getBody().getBody().setType(BodyDef.BodyType.DynamicBody);

        getStats().setID("Bullet");
        getStats().setExist(true);

        //Add to scriptManager
        ScriptManager.getObject().addScriptListener(this);

        CollisionManager.getObject().addCollider(this);

        //Set tag for the object
        this.getBody().getBody().setUserData(getStats());
        this.getBody().getFixtureList().get(0).setUserData(getStats().getID());
        this.getBody().updateFilter(CATEGORY_BULLET, (short) -1);

    }


    public void update(float posX, float posY, float angle, float speed, boolean isPlayer1) {
        if (isPlayer1) {
            getBody().updateFilter(CATEGORY_BULLET, MASK_PLAYER1);
        } else {
            getBody().updateFilter(CATEGORY_BULLET, MASK_PLAYER2);
        }

        this.getBody().setPosition(posX, posY);
        this.getBody().setAngle(angle);
        this.getBody().getBody().setLinearVelocity(
                MathUtils.cosDeg(angle) * speed,
                MathUtils.sinDeg(angle) * speed);
        lifetime = 0;
        freed = false;

    }

    /**
     * This method will be call every game loop.
     *
     * @param deltaTime
     */
    @Override
    public void runObjectScript(float deltaTime) {
        lifetime += deltaTime;
        if (lifetime >= max_Lifetime && !freed) {
            GameObjectManager.getObject().freeBullet(this);
        }

    }


    /**
     * Resets the object for reuse. Object references should be nulled and fields may be set to default values.
     */
    @Override
    public void reset() {
        freed = true;
        this.getBody().getBody().setLinearVelocity(new Vector2(0, 0));
        this.getBody().getBody().setAngularVelocity(0);
        this.getBody().setPosition(-10, -10);


    }

    @Override
    public void startCollision(Contact contact) {
        //Damage
        com.badlogic.gdx.physics.box2d.Body body1, body2;
        if (contact.getFixtureA().getBody() == this.getBody().getBody()) {
            body1 = contact.getFixtureA().getBody();
            body2 = contact.getFixtureB().getBody();
        } else {
            body2 = contact.getFixtureA().getBody();
            body1 = contact.getFixtureB().getBody();
        }

        Stats stats = (Stats) body2.getUserData();
      /*if (stats.getID().equals("Player")) {
         stats.setCurrentHP(stats.getCurrentHP() - (stats.getMaxHP() / 3f));
         GameObjectManager.getObject().freeBullet(this);
          this.getBody().setPosition(10, 10);
      }*/


    }

    @Override
    public void endCollision(Contact contact) {

    }

    @Override
    public com.badlogic.gdx.physics.box2d.Body getBodyForCollisionTesting() {
        return getBody().getBody();
    }


}

