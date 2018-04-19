package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Identifier;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.GameObjectManager;

/**
 * The base class of all gameobjects.
 */
public class GameObject {
    private Sprite sprite;
    private Body body;
    private Identifier identifier;

    public GameObject() {
        sprite = null;
        body = null;
        identifier = new Identifier();
        GameObjectManager.getObject().addGameObject(this);
    }

    public GameObject(Sprite sprite, Body body) {
        this.sprite = sprite;
        this.body = body;
        identifier = new Identifier();
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

    public Identifier getIdentifier() {
        return identifier;
    }

    public boolean isExist() {
        return identifier.isExist;
    }

    public String getID() {
        return identifier.ID;
    }
}
