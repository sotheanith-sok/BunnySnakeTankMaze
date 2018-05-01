package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.badlogic.gdx.physics.box2d.Contact;

public interface Collider {
    void startCollision(Contact contact);

    void endCollision(Contact contact);

}
