package TestingAshley.Components.Collisions;

import com.badlogic.gdx.physics.box2d.Contact;

public abstract class Collision {
    public abstract void beginContact(Contact contact);

    public abstract void endContact(Contact contact);
}
