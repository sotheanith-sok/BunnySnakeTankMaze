package TestingAshley.Components.Collisions;

import com.badlogic.gdx.physics.box2d.Contact;

public class PlayerCollision extends Collision {
    @Override
    public void beginContact(Contact contact) {
        System.out.println("Player is hitting something");
    }

    @Override
    public void endContact(Contact contact) {

    }
}
