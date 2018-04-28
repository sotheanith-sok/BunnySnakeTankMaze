package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.PlayerAnimator;
import com.agilewhisperers.bunnysnaketankmaze.systems.GameObjectManager;
import com.agilewhisperers.bunnysnaketankmaze.systems.Physic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;

public class Player2 extends Player {
    public Player2(float posX, float posY) {

        super(posX, posY);
        this.getBody().updateFilter(Physic.CATEGORY_PLAYER2, (short) -1);

    }

    @Override
    public void movement() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.getBody().setAngularVelocity(getStats().getRotatingSpeed());
            ((PlayerAnimator) getAnimator()).updateState(PlayerAnimator.State.ROTATE_COUNTERCLOCKWISE);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.getBody().setAngularVelocity(-getStats().getRotatingSpeed());
            ((PlayerAnimator) getAnimator()).updateState(PlayerAnimator.State.ROTATE_CLOCKWISE);
        } else {
            this.getBody().setAngularVelocity(0);
            ((PlayerAnimator) getAnimator()).updateState(PlayerAnimator.State.STANDING);

        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.getBody().getBody().setLinearVelocity(MathUtils.cosDeg(this.getBody().getAngle()) * getStats().getMovingSpeed(), MathUtils.sinDeg(this.getBody().getAngle()) * getStats().getMovingSpeed());

        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.getBody().getBody().setLinearVelocity(MathUtils.cosDeg(this.getBody().getAngle()) * -getStats().getMovingSpeed(), MathUtils.sinDeg(this.getBody().getAngle()) * -getStats().getMovingSpeed());
        } else {
            this.getBody().getBody().setLinearVelocity(0, 0);
        }
    }

    @Override
    public void fire() {
        rateTimer += getDeltaTime();
        if ((capacityCounter <= getStats().getCapacity()) && rateTimer > 1 / getStats().getRPS() && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            GameObjectManager.getObject().getBullet(false).update(this.getBody().getBody().getPosition().x,
                    this.getBody().getBody().getPosition().y,
                    this.getBody().getAngle(), getStats().getBulletSpeed());
            rateTimer = 0;
            capacityCounter++;
        }

        //Reload
        if (capacityCounter > getStats().getCapacity()) {
            reloadTimer += getDeltaTime();
            if (reloadTimer >= getStats().getReloadTime()) {
                capacityCounter = 0;
                reloadTimer = 0;
            }
        }
    }
}
