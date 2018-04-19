package com.agilewhisperers.bunnysnaketankmaze.components;

public class State {

   //Identifier
   public String ID;
   public boolean isExist = true;


   //Weapon
   //Round per second
   public float RPS = 10;
   public float bulletSpeed=100;
   public float reloadTime=0;
   public float capacity=50;

   //Health
   public float currentHP = 100;
   public float maxHP = 100;

   //Movement and Rotation
   public float movingSpeed=25*(currentHP/maxHP);
   public float rotatingSpeed=5;


}
