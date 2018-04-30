package TestingAshley.Systems;

import TestingAshley.Components.BodyComponent;
import TestingAshley.Components.SpriteComponent;
import TestingAshley.Components.TimeComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicSystem extends IteratingSystem {
   private static final float TIME_STEP = 1f / 300f;
   private static final int VELOCITY_ITERATIONS = 8;
   private static final int POSITION_ITERATIONS = 4;
   private World world;
   private float accumulator;
   private float deltaTime=0;

   private ComponentMapper<TimeComponent> timeComponents=ComponentMapper.getFor(TimeComponent.class);

   public PhysicSystem(World world) {
      super(Family.all(BodyComponent.class,TimeComponent.class,SpriteComponent.class).get());
      this.world = world;

   }

   /**
    * This method is called on every entity on every update call of the EntitySystem. Override this to implement your system's
    * specific processing.
    *
    * @param entity    The current Entity being processed
    * @param deltaTime The delta time between the last and current frame
    */
   @Override
   protected void processEntity(Entity entity, float deltaTime) {
      if(entity==getEntities().first()){
         float frameTime=  Math.min(deltaTime,0.25f);
         accumulator += frameTime;
         this.deltaTime=deltaTime;
      }
      while(accumulator>=TIME_STEP){
         accumulator-=TIME_STEP;
         world.step(TIME_STEP,VELOCITY_ITERATIONS,POSITION_ITERATIONS);
      }
      timeComponents.get(entity).alphaTime=accumulator/TIME_STEP;
   }
}
