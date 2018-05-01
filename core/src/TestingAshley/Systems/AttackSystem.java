package TestingAshley.Systems;

import TestingAshley.Components.AttackComponent;
import TestingAshley.Components.BodyComponent;
import TestingAshley.Components.StatsComponent;
import TestingAshley.Components.TagComponent;
import TestingAshley.Entities.Bullet;
import TestingAshley.Utilities.Constants;
import TestingAshley.Utilities.ObjectFactory;
import com.agilewhisperers.bunnysnaketankmaze.entities.GameObject;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;

public class AttackSystem extends IntervalIteratingSystem {
    private ComponentMapper<BodyComponent> bodyComponents = ComponentMapper.getFor(BodyComponent.class);
    private ComponentMapper<AttackComponent> attackComponents = ComponentMapper.getFor(AttackComponent.class);
    private ComponentMapper<StatsComponent> statsComponent = ComponentMapper.getFor(StatsComponent.class);
    private ComponentMapper<TagComponent> tagComponent = ComponentMapper.getFor(TagComponent.class);

    public AttackSystem() {
        super(Family.all(AttackComponent.class, StatsComponent.class).get(), Constants.TIME_STEP, 20);
    }

    /**
     * The user should place the entity processing logic here.
     *
     * @param entity
     */
    @Override
    protected void processEntity(Entity entity) {
        AttackComponent ac = attackComponents.get(entity);
        Body bc = bodyComponents.get(entity).getBody();
        StatsComponent sc = statsComponent.get(entity);

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
    }

    @Override
    protected void updateInterval() {
        super.updateInterval();
    }


}
