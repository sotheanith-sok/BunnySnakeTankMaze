package TestingAshley.Entities;

import TestingAshley.Components.*;
import TestingAshley.Components.Animators.PlayerAnimator;
import TestingAshley.Components.Collisions.PlayerCollision;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends Entity {
    public Player(World world, float posX, float posY) {
        add(new BodyComponent(world, posX, posY, 1f, "Player"));
        add(new TagComponent("Player1"));
        add(new AnimatorComponent(new PlayerAnimator()));
        add(new TimeComponent());
        getComponent(BodyComponent.class).getBody().setUserData(this);
        getComponent(BodyComponent.class).getBody().setType(BodyDef.BodyType.DynamicBody);
        add(new MovementComponent());
        add(new StatsComponent());
        add(new CollisionComponent(new PlayerCollision()));
        add(new AttackComponent());
    }
}
