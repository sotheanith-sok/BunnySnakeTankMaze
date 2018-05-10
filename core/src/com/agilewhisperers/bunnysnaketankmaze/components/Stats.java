package com.agilewhisperers.bunnysnaketankmaze.components;

public class Stats {

   //Identifier
   private String ID;
   private boolean isExist = true;


   //Weapon
   //Round per second
   private float RPS = 1f;
   private float bulletSpeed = 50f;
   private float reloadTime = 5f;
   private float capacity = 50;
   private float capacityCounter = 0;

   //Health
   private float currentHP = 100;
   private float maxHP = 100;

   //Movement and Rotation
   private float movingSpeed = 5f;
   private float rotatingSpeed = 2.5f;

   public String getID() {
      return ID;
   }

   public void setID(String ID) {
      this.ID = ID;
   }

   public boolean isExist() {
      return isExist;
   }

   public void setExist(boolean exist) {
      isExist = exist;
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
      if (currentHP < 0) {
         this.currentHP = 0;
      }
   }

   public float getMaxHP() {
      return maxHP;
   }

   public void setMaxHP(float maxHP) {
      this.maxHP = maxHP;
   }

   public float getMovingSpeed() {
      return ((maxHP - ((maxHP -currentHP)/2))/maxHP) * movingSpeed ;
   }

   public void setMovingSpeed(float movingSpeed) {
      this.movingSpeed = movingSpeed;
   }

   public float getRotatingSpeed() {
      return rotatingSpeed;
   }

   public void setRotatingSpeed(float rotatingSpeed) {
      this.rotatingSpeed = rotatingSpeed;
   }

   public float getCapacityCounter() {
      return capacityCounter;
   }

   public void setCapacityCounter(float capacityCounter) {
      this.capacityCounter = capacityCounter;
   }
}
