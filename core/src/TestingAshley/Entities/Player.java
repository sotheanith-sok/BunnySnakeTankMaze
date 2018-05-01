package TestingAshley.Entities;

import TestingAshley.Components.*;
import TestingAshley.Components.Animators.PlayerAnimator;
import TestingAshley.Components.Collisions.PlayerCollision;
import TestingAshley.Utilities.Constants;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends Entity {
    public Player(World world, float posX, float posY, boolean isPlayer1) {
        add(new BodyComponent(world, posX, posY, 1f, "Player"));
        if(isPlayer1){
           add(new TagComponent("Player1"));
           getComponent(BodyComponent.class).updateFilter(Constants.CATEGORY_PLAYER1, (short) -1);
        }else{
           add(new TagComponent("Player2"));
           getComponent(BodyComponent.class).updateFilter(Constants.CATEGORY_PLAYER2, (short) -1);
        }
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
