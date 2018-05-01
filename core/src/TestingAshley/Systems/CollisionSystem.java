package TestingAshley.Systems;

import TestingAshley.Components.CollisionComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;


public class CollisionSystem extends EntitySystem implements ContactListener {
    /**
     * Called when two fixtures begin to touch.
     *
     * @param contact
     */
    @Override
    public void beginContact(Contact contact) {

        Entity entity1 = (Entity) contact.getFixtureA().getBody().getUserData();
        Entity entity2 = (Entity) contact.getFixtureB().getBody().getUserData();
        if (entity1.getComponent(CollisionComponent.class) != null) {
            System.out.println("HI");
            entity1.getComponent(CollisionComponent.class).beginContact(contact);
        }
        if (entity2.getComponent(CollisionComponent.class) != null) {
            entity2.getComponent(CollisionComponent.class).beginContact(contact);
        }
    }

    /**
     * Called when two fixtures cease to touch.
     *
     * @param contact
     */
    @Override
    public void endContact(Contact contact) {
        Entity entity1 = (Entity) contact.getFixtureA().getBody().getUserData();
        Entity entity2 = (Entity) contact.getFixtureA().getBody().getUserData();
        if (entity1.getComponent(CollisionComponent.class) != null) {
            entity1.getComponent(CollisionComponent.class).endContact(contact);
        }
        if (entity2.getComponent(CollisionComponent.class) != null) {
            entity2.getComponent(CollisionComponent.class).endContact(contact);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
