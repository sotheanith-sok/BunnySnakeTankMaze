package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.Physic;
import com.badlogic.gdx.physics.box2d.*;

public class Wall extends GameObject{
    public Wall(float posX, float posY){
       super(false, new Sprite(
               "game/Wall.png"
       ), new Body(Physic.getObject().getWorld(),posX+0.5f,posY+0.5f,1,1,0));
      getBody().getBody().setType(BodyDef.BodyType.StaticBody);
    }

}
