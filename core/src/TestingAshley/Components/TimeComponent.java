package TestingAshley.Components;

import com.badlogic.ashley.core.Component;

public class TimeComponent implements Component {
    public float alphaTime = 0;


   private float rateTimer = 0;
   private float reloadTimer = 0;
   private float capacityCounter = 0;

   public float getAlphaTime() {
      return alphaTime;
   }

   public void setAlphaTime(float alphaTime) {
      this.alphaTime = alphaTime;
   }

   public float getRateTimer() {
      return rateTimer;
   }

   public void setRateTimer(float rateTimer) {
      this.rateTimer = rateTimer;
   }

   public float getReloadTimer() {
      return reloadTimer;
   }

   public void setReloadTimer(float reloadTimer) {
      this.reloadTimer = reloadTimer;
   }

   public float getCapacityCounter() {
      return capacityCounter;
   }

   public void setCapacityCounter(float capacityCounter) {
      this.capacityCounter = capacityCounter;
   }
}
