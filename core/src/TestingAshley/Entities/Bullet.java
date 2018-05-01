package TestingAshley.Entities;

import TestingAshley.Components.BodyComponent;
import TestingAshley.Components.SpriteComponent;
import TestingAshley.Components.TagComponent;
import TestingAshley.Components.TimeComponent;
import TestingAshley.Utilities.Constants;
import com.agilewhisperers.bunnysnaketankmaze.systems.Physic;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Bullet extends Entity {
    public Bullet(World world, float posX, float posY,boolean isPlayer1) {
        add(new BodyComponent(world, posX, posY, 1f, "Carrot"));
        add(new TagComponent("Bullet"));
        add(new SpriteComponent("gameObjects/Projectiles.atlas", "Carrot", 3));
        add(new TimeComponent());
        getComponent(BodyComponent.class).getBody().setUserData(this);
        getComponent(BodyComponent.class).getBody().setType(BodyDef.BodyType.DynamicBody);
        if(isPlayer1){
            getComponent(BodyComponent.class).updateFilter(Constants.CATEGORY_BULLET,Constants.MASK_PLAYER1);
        }else{
            getComponent(BodyComponent.class).updateFilter(Constants.CATEGORY_BULLET,Constants.MASK_PLAYER2);
        }
    }



}
