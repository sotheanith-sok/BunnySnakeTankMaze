package com.agilewhisperers.bunnysnaketankmaze.entities;

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
        this.getBody().getBody().setUserData(getStats());
        this.getBody().getFixtureList().get(0).setUserData(getStats().getID());
        ScriptManager.getObject().addScriptListener(this);
        CollisionManager.getObject().addCollider(this);
        wallLocation = ObjectFactory.getObject().getData();
        bodyArray = new Array<>();
        isEating = true;
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
      /*stageTimer+= deltaTime;
      startTimer+=deltaTime;
      if(stageTimer>1f){
         List<GameObject> gameObjectList=GameObjectManager.getObject().getAllGameObjects();
         //EAT WALL
         if (isEating){
            Body body=gameObjectList.get(random.nextInt(gameObjectList.size())).getBody();
            Vector2 position=body.getBody().getPosition();
            Stats userData= (Stats) body.getBody().getUserData();
            if(userData.ID.equals("Wall")
                    &&position.x>=1&&position.x<wallLocation[0].length-1
                    &&position.y>=1&&position.y<wallLocation.length-1){
               wallLocation[(int)position.y][(int)position.x]=0;
               bodyArray.add(body);
               body.setPosition(-10,-10);
               body.updateFilter(Physic.CATEGORY_ENVIRONMENT,(short)0);
               getBody().setPosition(position.x,position.y);
            }
            if(isEating&&bodyArray.size>10){
               isEating=false;
               getBody().setPosition(-10,-10);
            }

         }else{
            //PLACE WALL
         }
         stageTimer=0f;

      }*/


    }

    @Override
    public com.badlogic.gdx.physics.box2d.Body getBodyForCollisionTesting() {
        return getBody().getBody();
    }
}
