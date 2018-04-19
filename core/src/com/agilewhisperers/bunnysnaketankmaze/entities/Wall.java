package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.Physic;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class Wall extends GameObject {
    public Wall(float posX, float posY) {
        super(new Sprite(
                "gameObjects/Wall.png"
        ), new Body(Physic.getObject().getWorld(), posX , posY, 1, 0,"Wall"));
        getBody().getBody().setType(BodyDef.BodyType.StaticBody);
        getState().ID = "Wall";
        getState().isExist = true;
        getBody().getBody().setUserData(getState());
    }

}
