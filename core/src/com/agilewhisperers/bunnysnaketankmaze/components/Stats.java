package com.agilewhisperers.bunnysnaketankmaze.components;

public class Stats {

   //Identifier
   public String ID;
   public boolean isExist = true;


   //Weapon
   //Round per second
   public float RPS = 10f;
   public float bulletSpeed = 100f;
   public float reloadTime = 0f;
   public float capacity = 50;

   //Health
   public float currentHP = 100;
   public float maxHP = 100;

   //Movement and Rotation
   public float movingSpeed = 25 * (currentHP / maxHP);
   public float rotatingSpeed = 2.5f;


}
