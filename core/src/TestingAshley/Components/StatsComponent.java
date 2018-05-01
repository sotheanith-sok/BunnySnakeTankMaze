package TestingAshley.Components;

import com.badlogic.ashley.core.Component;

public class StatsComponent implements Component {

    //Movement
    private float linearVelocity = 10f;
    private float angularVelocity = 5f;

    //Weapon
    //Round per second
    private float RPS = 1f;
    private float bulletSpeed = 25;
    private float reloadTime = 0f;
    private float capacity = 50;

    //Health
    private float currentHP = 100;
    private float maxHP = 100;

    public float getLinearVelocity() {
        return linearVelocity;
    }

    public void setLinearVelocity(float linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public float getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(float angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public float getRPS() {
        return RPS;
    }

    public void setRPS(float RPS) {
        this.RPS = RPS;
    }

    public float getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(float bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public float getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(float reloadTime) {
        this.reloadTime = reloadTime;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public float getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(float currentHP) {
        this.currentHP = currentHP;
    }

    public float getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(float maxHP) {
        this.maxHP = maxHP;
    }
}
