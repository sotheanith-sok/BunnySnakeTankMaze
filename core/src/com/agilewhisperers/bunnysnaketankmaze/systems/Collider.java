package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;

public interface Collider {
    void startCollision(Contact contact);

    void endCollision(Contact contact);

    Array<Fixture> getFixtureArray();
}
