package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Animator;
import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.components.Stats;
import com.agilewhisperers.bunnysnaketankmaze.systems.GameObjectManager;

/**
 * The base class of all gameobjects.
 */
public class GameObject {
    private Sprite sprite;
    private Body body;
    private Stats stats;
    private Animator animator;

    public GameObject() {
        sprite = null;
        body = null;
        stats = new Stats();
        GameObjectManager.getObject().addGameObject(this);
    }

    public GameObject(Sprite sprite, Body body) {
        this.sprite = sprite;
        this.body = body;
        stats = new Stats();
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

    public void setBody(Body body) {
        this.body = body;
    }

    public Stats getStats() {
        return stats;
    }

    public boolean isExist() {
        return stats.isExist();
    }

    public String getID() {
        return stats.getID();
    }

    public Animator getAnimator() {
        return animator;
    }

    public void setAnimator(Animator animator) {
        this.animator = animator;
    }
}
