package com.agilewhisperers.bunnysnaketankmaze.components;

import com.agilewhisperers.bunnysnaketankmaze.entities.GameObject;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;

public class SteerableComponent implements Steerable<Vector2> {

   public GameObject owner;

   public SteerableComponent(GameObject owner) {
      this.owner = owner;
   }

   /**
    * Returns the vector indicating the linear velocity of this Steerable.
    */
   @Override
   public Vector2 getLinearVelocity() {
      return owner.getBody().getBody().getLinearVelocity();

   }

   /**
    * Returns the float value indicating the the angular velocity in radians of this Steerable.
    */
   @Override
   public float getAngularVelocity() {
      return owner.getBody().getBody().getAngularVelocity();
   }

   /**
    * Returns the bounding radius of this Steerable.
    */
   @Override
   public float getBoundingRadius() {
      return 0;
   }

   /**
    * Returns {@code true} if this Steerable is tagged; {@code false} otherwise.
    */
   @Override
   public boolean isTagged() {
      return false;
   }

   /**
    * Tag/untag this Steerable. This is a generic flag utilized in a variety of ways.
    *
    * @param tagged the boolean value to set
    */
   @Override
   public void setTagged(boolean tagged) {

   }

   /**
    * Returns the threshold below which the linear speed can be considered zero. It must be a small positive value near to zero.
    * Usually it is used to avoid updating the orientation when the velocity vector has a negligible length.
    */
   @Override
   public float getZeroLinearSpeedThreshold() {
      return 0;
   }

   /**
    * Sets the threshold below which the linear speed can be considered zero. It must be a small positive value near to zero.
    * Usually it is used to avoid updating the orientation when the velocity vector has a negligible length.
    *
    * @param value
    */
   @Override
   public void setZeroLinearSpeedThreshold(float value) {

   }

   /**
    * Returns the maximum linear speed.
    */
   @Override
   public float getMaxLinearSpeed() {
      return 0;
   }

   /**
    * Sets the maximum linear speed.
    *
    * @param maxLinearSpeed
    */
   @Override
   public void setMaxLinearSpeed(float maxLinearSpeed) {

   }

   /**
    * Returns the maximum linear acceleration.
    */
   @Override
   public float getMaxLinearAcceleration() {
      return 0;
   }

   /**
    * Sets the maximum linear acceleration.
    *
    * @param maxLinearAcceleration
    */
   @Override
   public void setMaxLinearAcceleration(float maxLinearAcceleration) {

   }

   /**
    * Returns the maximum angular speed.
    */
   @Override
   public float getMaxAngularSpeed() {
      return 0;
   }

   /**
    * Sets the maximum angular speed.
    *
    * @param maxAngularSpeed
    */
   @Override
   public void setMaxAngularSpeed(float maxAngularSpeed) {

   }

   /**
    * Returns the maximum angular acceleration.
    */
   @Override
   public float getMaxAngularAcceleration() {
      return 0;
   }

   /**
    * Sets the maximum angular acceleration.
    *
    * @param maxAngularAcceleration
    */
   @Override
   public void setMaxAngularAcceleration(float maxAngularAcceleration) {

   }

   /**
    * Returns the vector indicating the position of this location.
    */
   @Override
   public Vector2 getPosition() {
      return null;
   }

   /**
    * Returns the float value indicating the orientation of this location. The orientation is the angle in radians representing
    * the direction that this location is facing.
    */
   @Override
   public float getOrientation() {
      return 0;
   }

   /**
    * Sets the orientation of this location, i.e. the angle in radians representing the direction that this location is facing.
    *
    * @param orientation the orientation in radians
    */
   @Override
   public void setOrientation(float orientation) {

   }

   /**
    * Returns the angle in radians pointing along the specified vector.
    *
    * @param vector the vector
    */
   @Override
   public float vectorToAngle(Vector2 vector) {
      return 0;
   }

   /**
    * Returns the unit vector in the direction of the specified angle expressed in radians.
    *
    * @param outVector the output vector.
    * @param angle     the angle in radians.
    * @return the output vector for chaining.
    */
   @Override
   public Vector2 angleToVector(Vector2 outVector, float angle) {
      return null;
   }

   /**
    * Creates a new location.
    * <p>
    * This method is used internally to instantiate locations of the correct type parameter {@code T}. This technique keeps the API
    * simple and makes the API easier to use with the GWT backend because avoids the use of reflection.
    *
    * @return the newly created location.
    */
   @Override
   public Location<Vector2> newLocation() {
      return null;
   }
}
