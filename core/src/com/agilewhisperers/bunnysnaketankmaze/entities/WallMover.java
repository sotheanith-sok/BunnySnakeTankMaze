package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.SteerableComponent;
import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.utils.Array;


public class WallMover extends GameObject implements Script, Collider {
    Array<Body> bodyArray;
    private float startTimer = 0;
    private float stageTimer = 0;
    private int[][] wallLocation;
    private boolean isEating;
    private float deltaTime;

    public WallMover() {
        super();
        super.setBody(new Body(Physic.getObject().getWorld(), 15, 15, 2.5f, 0, "Spider"));
        super.setSprite(new Sprite("gameObjects/NPC.atlas", "Spider", 1));
        super.getBody().getBody().setType(BodyDef.BodyType.KinematicBody);
        getStats().setExist(true);
        getStats().setID("WallMover");
        this.getBody().getBody().setUserData(this);
        this.getBody().getFixtureList().get(0).setUserData(getStats().getID());
        ScriptManager.getObject().addScriptListener(this);
        wallLocation = ObjectFactory.getObject().getData();
        bodyArray = new Array<>();
        isEating = true;
        setSteerableComponent(new SteerableComponent(getBody().getBody(),10));
    }

    @Override
    public void startCollision(Contact contact) {

    }

    @Override
    public void endCollision(Contact contact) {

    }


    /**
     * This method will be call every game loop.
     *
     * @param deltaTime
     */
    @Override
    public void runObjectScript(float deltaTime) {
        this.deltaTime = deltaTime;
      if(getSteerableComponent()!=null){
          getSteerableComponent().update(deltaTime);
      }
    }


}
