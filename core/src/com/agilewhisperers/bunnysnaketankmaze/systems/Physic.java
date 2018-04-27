package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.agilewhisperers.bunnysnaketankmaze.components.Stats;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * The physic system.
 */
public class Physic {
    public static final short CATEGORY_PLAYER1 = 0x0001;
    public static final short CATEGORY_PLAYER2 = 0x0002;
    public static final short CATEGORY_ENVIRONMENT = 0x0004;
    public static final short CATEGORY_BULLET = 0x0008;
    public static final short CATEGORY_WORLDMOVER=0x0010;
    public static final short MASK_PLAYER1 = ~CATEGORY_PLAYER1 & ~CATEGORY_BULLET;
    public static final short MASK_PLAYER2 = ~CATEGORY_PLAYER2 & ~CATEGORY_BULLET;


    private static Physic single_instance;

    private World world;


    private Physic() {
        world = new World(new Vector2(0, 0), true);
    }

    public static Physic getObject() {
        if (single_instance == null) {
            single_instance = new Physic();
        }
        return single_instance;
    }

    /**
     * Get the physic container.
     *
     * @return
     */
    public World getWorld() {
        return world;
    }

    /**
     * Add listeners to the physic engine
     *
     * @param contactListener listener
     */
    public void setContactListener(ContactListener contactListener) {
        world.setContactListener(contactListener);

    }


    public void cleanDeadBody() {
        Array<Body> bodies = new Array<>();
        Physic.getObject().getWorld().getBodies(bodies);
        for (com.badlogic.gdx.physics.box2d.Body body : bodies) {
            if (body.getUserData() != null && ((Stats) body.getUserData()).isExist() == false) {
                Physic.getObject().getWorld().destroyBody(body);
            }

        }
    }
}
