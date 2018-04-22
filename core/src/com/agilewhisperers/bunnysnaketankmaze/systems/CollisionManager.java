package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

public class CollisionManager implements ContactListener {
    private static CollisionManager single_instance;
    Array<Collider> colliders;

    private CollisionManager() {
        Physic.getObject().addCollision(this);
        colliders = new Array<>();
    }

    public static CollisionManager getObject() {
        if (single_instance == null) {
            single_instance = new CollisionManager();
        }
        return single_instance;
    }

    public void addCollider(Collider collider) {
        colliders.add(collider);
    }

    @Override
    public void beginContact(Contact contact) {
        int count = 0;
        for (int i = 0; i < colliders.size; i++) {
            if (colliders.get(i).getFixture() == contact.getFixtureA() || colliders.get(i).getFixture() == contact.getFixtureB()) {
                colliders.get(i).beginCollision(contact);
                count++;
                if (count > 1) {
                    break;
                }
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        int count = 0;
        for (int i = 0; i < colliders.size; i++) {
            if (colliders.get(i).getFixture() == contact.getFixtureA() || colliders.get(i).getFixture() == contact.getFixtureB()) {
                colliders.get(i).endCollision(contact);
                count++;
                if (count > 1) {
                    break;
                }
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
