package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

public interface Collider {
    void startCollision(Contact contact);

    void endCollision(Contact contact);

    Fixture getFixture();
}
