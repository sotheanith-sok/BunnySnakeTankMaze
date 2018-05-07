package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.BodyLocation;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.components.SteerableComponent;
import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.utils.Array;


public class WallMover extends GameObject implements Script, Collider {
    Array<com.badlogic.gdx.physics.box2d.Body> wallRemoved;
    float accumulator = 0;
    int counter = 0;
    Array<com.badlogic.gdx.physics.box2d.Body> bodies;
    int[][] data;

    private Arrive<Vector2> arrive;

    public WallMover() {
        super();
        super.setBody(new Body(Physic.getObject().getWorld(), 15, 15, 1f, 0, "Spider"));
        super.setSprite(new Sprite("gameObjects/NPC.atlas", "Spider", 1));
        super.getBody().getBody().setType(BodyDef.BodyType.KinematicBody);
        getStats().setExist(true);
        getStats().setID("WallMover");
        this.getBody().getBody().setUserData(this);
        this.getBody().getFixtureList().get(0).setUserData(getStats().getID());
        ScriptManager.getObject().addScriptListener(this);
        setSteerableComponent(new SteerableComponent(getBody().getBody(), 1));
        getBody().updateFilter(Physic.MASK_WORLDMOVER, Physic.MASK_WORLDMOVER);
        wallRemoved = new Array<>();
        data = ObjectFactory.getObject().getData();
        arrive = new Arrive<>(getSteerableComponent());
        arrive.setArrivalTolerance(0.01f);
        getSteerableComponent().setSteeringBehavior(arrive);
        bodies = new Array<>();
        Physic.getObject().getWorld().getBodies(bodies);
        gatherAllWall();

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
        if (getSteerableComponent() != null && arrive.getTarget() != null) {
            getSteerableComponent().update(deltaTime);
            accumulator += deltaTime;
            if (accumulator > 1f) {
                accumulator = 0;
                bodies.get(counter).setTransform(getBody().getBody().getPosition(), 0);
                bodies.get(counter).setLinearVelocity(MathUtils.random(-1,1),MathUtils.random(-1,1));
                bodies.get(counter).setAngularVelocity(MathUtils.random(0,2*MathUtils.PI));
                counter++;
                if (counter >= bodies.size) {
                    counter = 0;
                }
            }
        }

        if (getSteerableComponent().getSteeringOutput().linear.isZero()) {
            int x = MathUtils.random(2, data[0].length - 2);
            int y = MathUtils.random(2, data.length - 2);
            arrive.setTarget(new BodyLocation(x, y, 0));
        }

    }

    private void gatherAllWall() {
        Array<com.badlogic.gdx.physics.box2d.Body> temp = new Array<>();
        for (int i = 0; i < bodies.size; i++) {
            if (((GameObject) bodies.get(i).getUserData()).getID().equals("Wall")) {
                if (bodies.get(i).getPosition().x - 0.5 != 0
                        && bodies.get(i).getPosition().x - 0.5 != data[0].length - 1
                        && bodies.get(i).getPosition().y - 0.5 != 0
                        && bodies.get(i).getPosition().y - 0.5 != data.length - 1) {
                    temp.add(bodies.get(i));
                    bodies.get(i).setType(BodyDef.BodyType.DynamicBody);
                    MassData massData = new MassData();
                    massData.mass = 1000;
                    massData.I = 10;
                    bodies.get(i).setMassData(massData);
                }
            }
        }
        bodies = temp;
    }


}
