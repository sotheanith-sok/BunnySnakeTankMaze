package TestingAshley.Systems;

import TestingAshley.Components.BodyComponent;
import TestingAshley.Components.MovementComponent;
import TestingAshley.Components.StatsComponent;
import TestingAshley.Utilities.Constants;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.math.MathUtils;

public class MovementSystem extends IntervalIteratingSystem {
    private ComponentMapper<BodyComponent> bodyComponents = ComponentMapper.getFor(BodyComponent.class);
    private ComponentMapper<MovementComponent> movementComponents = ComponentMapper.getFor(MovementComponent.class);
    private ComponentMapper<StatsComponent> statsComponent = ComponentMapper.getFor(StatsComponent.class);

    public MovementSystem() {
        super(Family.all(MovementComponent.class, StatsComponent.class).get(), Constants.TIME_STEP, 20);
    }


    /**
     * The user should place the entity processing logic here.
     *
     * @param entity
     */
    @Override
    protected void processEntity(Entity entity) {
        MovementComponent mc = movementComponents.get(entity);
        BodyComponent bc = bodyComponents.get(entity);
        StatsComponent sc = statsComponent.get(entity);
        if (mc.isMoveForward()) {
            bc.getBody().setLinearVelocity(sc.getLinearVelocity() * MathUtils.cos(bc.getBody().getAngle()), sc.getLinearVelocity() * MathUtils.sin(bc.getBody().getAngle()));
        } else if (mc.isMoveBackward()) {
            bc.getBody().setLinearVelocity(-sc.getLinearVelocity() * MathUtils.cos(bc.getBody().getAngle()), -sc.getLinearVelocity() * MathUtils.sin(bc.getBody().getAngle()));
        } else {
            bc.getBody().setLinearVelocity(0, 0);
        }

        if (mc.isRotateClockwise()) {
            bc.getBody().setAngularVelocity(-sc.getAngularVelocity());
        } else if (mc.isRotateCounterClockwise()) {
            bc.getBody().setAngularVelocity(sc.getAngularVelocity());
        } else {
            bc.getBody().setAngularVelocity(0f);
        }
    }
}
