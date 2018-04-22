package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.Collider;
import com.agilewhisperers.bunnysnaketankmaze.systems.CollisionManager;
import com.agilewhisperers.bunnysnaketankmaze.systems.Physic;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

public class Wall extends GameObject implements Collider {
    public Wall(float posX, float posY) {
        super(new Sprite(
                "gameObjects/Wall.png"
        ), new Body(Physic.getObject().getWorld(), posX, posY, 1, 0, "Wall"));
        getBody().getBody().setType(BodyDef.BodyType.StaticBody);
        getState().ID = "Wall";
        getState().isExist = true;
        getBody().getBody().setUserData(getState());
        getBody().getFixture().setUserData(getState().ID);

        //Add collision
        CollisionManager.getObject().addCollider(this);
    }


    /**
     * Call when the collision begin
     *
     * @param contact
     */
    @Override
    public void beginCollision(Contact contact) {
        System.out.println("Wall is collided with something.");
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
