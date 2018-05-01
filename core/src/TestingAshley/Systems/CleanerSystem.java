package TestingAshley.Systems;

import TestingAshley.Components.BodyComponent;
import TestingAshley.Components.TagComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;

public class CleanerSystem extends IteratingSystem {
    private ComponentMapper<BodyComponent> bodyComponents = ComponentMapper.getFor(BodyComponent.class);
    private ComponentMapper<TagComponent> tagComponents = ComponentMapper.getFor(TagComponent.class);
    private World world;

    public CleanerSystem(World world) {
        super(Family.all(BodyComponent.class, TagComponent.class).get());
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
        if (!tagComponents.get(entity).isExist()) {
            world.destroyBody(bodyComponents.get(entity).getBody());
        }
    }
}
