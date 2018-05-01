package TestingAshley.Systems;

import TestingAshley.Components.BodyComponent;
import TestingAshley.Components.TimeComponent;
import TestingAshley.Utilities.Constants;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;


public class PhysicSystem extends IteratingSystem {

    private World world;
    private float accumulator;
    private float deltaTime = 0;

    private ComponentMapper<TimeComponent> timeComponents = ComponentMapper.getFor(TimeComponent.class);
    private ComponentMapper<BodyComponent> bodyComponents = ComponentMapper.getFor(BodyComponent.class);


    public PhysicSystem(World world) {
        super(Family.all(BodyComponent.class).get(), 40);
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
        timeComponents.get(entity).alphaTime = accumulator / Constants.TIME_STEP;
        bodyComponents.get(entity).updatePreviousPositionAndAngle();
    }

    @Override
    public void update(float deltaTime) {
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= Constants.TIME_STEP) {
            if (!world.isLocked()) {
                accumulator -= Constants.TIME_STEP;
                super.update(deltaTime);
                world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
            }
        }

    }
}
