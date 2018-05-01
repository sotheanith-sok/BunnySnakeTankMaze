package TestingAshley.Systems;

import TestingAshley.Components.*;
import TestingAshley.Entities.Bullet;
import TestingAshley.Utilities.Constants;
import TestingAshley.Utilities.ObjectFactory;
import com.agilewhisperers.bunnysnaketankmaze.entities.GameObject;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;

import java.sql.Time;

public class AttackSystem extends IteratingSystem {
    private ComponentMapper<BodyComponent> bodyComponents = ComponentMapper.getFor(BodyComponent.class);
    private ComponentMapper<AttackComponent> attackComponents = ComponentMapper.getFor(AttackComponent.class);
    private ComponentMapper<StatsComponent> statsComponent = ComponentMapper.getFor(StatsComponent.class);
    private ComponentMapper<TagComponent> tagComponent = ComponentMapper.getFor(TagComponent.class);
   private ComponentMapper<TimeComponent> timeComponent = ComponentMapper.getFor(TimeComponent.class);

    public AttackSystem() {
        super(Family.all(AttackComponent.class, StatsComponent.class).get());
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
      AttackComponent ac = attackComponents.get(entity);
      Body bc = bodyComponents.get(entity).getBody();
      StatsComponent sc = statsComponent.get(entity);
      TimeComponent tc=timeComponent.get(entity);


      tc.setRateTimer(tc.getRateTimer()+deltaTime);
      //Normal Fire
      if ((tc.getCapacityCounter() <= sc.getCapacity()) && tc.getRateTimer() > 1 / sc.getRPS() &&ac.isAttack()){
         if (ac.isAttack() &&tagComponent.get(entity).getName().equals("Player1")) {
            Bullet bullet=ObjectFactory.getObject().getBullet(true);
            Body body=bodyComponents.get(bullet).getBody();
            body.setTransform(bc.getPosition().x,bc.getPosition().y,bc.getAngle());
            body.setLinearVelocity(sc.getBulletSpeed()*MathUtils.cos(bc.getAngle()),sc.getBulletSpeed()*MathUtils.sin(bc.getAngle()));
         }else if (ac.isAttack() &&tagComponent.get(entity).getName().equals("Player2")) {
            Bullet bullet=ObjectFactory.getObject().getBullet(false);
            Body body=bodyComponents.get(bullet).getBody();
            body.setTransform(bc.getPosition().x,bc.getPosition().y,bc.getAngle());
            body.setLinearVelocity(sc.getBulletSpeed()*MathUtils.cos(bc.getAngle()),sc.getBulletSpeed()*MathUtils.sin(bc.getAngle()));
         }
         tc.setRateTimer(0);
         tc.setCapacityCounter(tc.getCapacityCounter()+1);
      }

      //Reload
      if(tc.getCapacityCounter()>sc.getCapacity()){
         tc.setReloadTimer(tc.getReloadTimer()+deltaTime);
         if(tc.getReloadTimer()>=sc.getReloadTime()){
            tc.setCapacityCounter(0);
            tc.setReloadTimer(0);
         }
      }

   }
}
