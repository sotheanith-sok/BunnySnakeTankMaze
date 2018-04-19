package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.State;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.GameObjectManager;

/**
 * The base class of all gameobjects.
 */
public class GameObject {
    private Sprite sprite;
    private Body body;
    private State state;

    public GameObject() {
        sprite = null;
        body = null;
        state = new State();
        GameObjectManager.getObject().addGameObject(this);
    }

    public GameObject(Sprite sprite, Body body) {
        this.sprite = sprite;
        this.body = body;
        state = new State();
        GameObjectManager.getObject().addGameObject(this);
    }

    /**
     * Get the sprite component of this object.
     *
     * @return sprite
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Set the sprite component of this object.
     *
     * @param sprite
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Set the body component of this object.
     *
     * @return body
     */
    public Body getBody() {
        return body;
    }

    /**
     * Get the body component of this object.
     *
     * @param body
     */
    public void setBody(Body body) {
        this.body = body;
    }

    public State getState() {
        return state;
    }

    public boolean isExist() {
        return state.isExist;
    }

    public String getID() {
        return state.ID;
    }
}
