package TestingAshley.Components;

import TestingAshley.Components.Collisions.Collision;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Contact;

public class CollisionComponent implements Component {
    private Collision collision;

    public CollisionComponent(Collision collision) {
        this.collision = collision;
    }

    public void beginContact(Contact contact) {
        collision.beginContact(contact);
    }

    public void endContact(Contact contact) {
        collision.endContact(contact);
    }
}
