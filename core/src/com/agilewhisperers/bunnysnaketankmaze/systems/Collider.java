package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

public interface Collider {
    /**
     * Call when the collision begin
     *
     * @param contact
     */
    void beginCollision(Contact contact);

    /**
     * Call when collision end
     *
     * @param contact
     */
    void endCollision(Contact contact);

    /**
     * Get the fixture of this collider
     *
     * @return Fixture
     */
    Fixture getFixture();
}
