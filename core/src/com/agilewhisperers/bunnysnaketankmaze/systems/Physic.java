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
   public static final short CATEGORY_PLAYER1 = 0x002;
   public static final short CATEGORY_PLAYER2 = 0x004;
   public static final short CATEGORY_ENVIROMENT = 0x006;
   public static final short CATEGORY_BULLET = 0x008;


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
         if (body.getUserData() != null && ((Stats) body.getUserData()).isExist == false) {
            Physic.getObject().getWorld().destroyBody(body);
         }

      }
   }
}
