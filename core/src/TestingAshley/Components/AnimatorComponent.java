package TestingAshley.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public abstract class AnimatorComponent implements Component {
   private Array<Animation<TextureRegion>> animationArray;

   public AnimatorComponent() {
      animationArray = new Array<>();
   }

   public void addAnimation(Animation<TextureRegion> animation) {
      animationArray.add(animation);
   }

   public Animation<TextureRegion> getAnimation(int index) {
      return animationArray.get(index);
   }

   public abstract TextureRegion getFrame(float stateTimer);
}
