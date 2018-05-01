package TestingAshley.Systems;

import TestingAshley.Components.AttackComponent;
import TestingAshley.Components.BodyComponent;
import TestingAshley.Components.StatsComponent;
import TestingAshley.Utilities.Constants;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;

public class AttackSystem extends IntervalIteratingSystem {
    private ComponentMapper<BodyComponent> bodyComponents = ComponentMapper.getFor(BodyComponent.class);
    private ComponentMapper<AttackComponent> attackComponents = ComponentMapper.getFor(AttackComponent.class);
    private ComponentMapper<StatsComponent> statsComponent = ComponentMapper.getFor(StatsComponent.class);

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
        BodyComponent bc = bodyComponents.get(entity);
        StatsComponent sc = statsComponent.get(entity);

        if (ac.isAttack()) {
            //Update bullet;
        }
    }

    @Override
    protected void updateInterval() {
        super.updateInterval();
    }
}
