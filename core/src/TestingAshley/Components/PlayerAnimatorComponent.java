package TestingAshley.Components;

import TestingAshley.Utilities.AssetManagingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class PlayerAnimatorComponent extends AnimatorComponent {
   private PlayerAnimatorComponent.State currentState, previousState;
   private TextureAtlas textureAtlas;

   public PlayerAnimatorComponent() {
      textureAtlas = AssetManagingSystem.getObject().getAssetManager().get("gameObjects/Player.atlas", TextureAtlas.class);
      currentState = PlayerAnimatorComponent.State.STANDING;
      previousState = PlayerAnimatorComponent.State.STANDING;
      Array<TextureRegion> textureRegions = new Array<>();
      //Create standing animation
      textureRegions.add(textureAtlas.findRegion("Player", 5));
      super.addAnimation(new Animation<TextureRegion>(1 / 10f, textureRegions));
      textureRegions.clear();

      //Create counter clockwise rotation
      for (int i = 4; i > 0; i--) {
         textureRegions.add(textureAtlas.findRegion("Player", i));
      }
      super.addAnimation(new Animation<TextureRegion>(1 / 10f, textureRegions));
      textureRegions.clear();

      //Create clockwise rotation
      for (int i = 6; i < 10; i++) {
         textureRegions.add(textureAtlas.findRegion("Player", i));
      }
      super.addAnimation(new Animation<TextureRegion>(1 / 10f, textureRegions));
      textureRegions.clear();

   }

   @Override
   public TextureRegion getFrame(float stateTimer) {
      TextureRegion textureRegion = null;
      switch (currentState) {
         case ROTATE_COUNTERCLOCKWISE:
            textureRegion = getAnimation(1).getKeyFrame(stateTimer, false);
            break;
         case ROTATE_CLOCKWISE:
            textureRegion = getAnimation(2).getKeyFrame(stateTimer, false);
            break;
         default:
            textureRegion = getAnimation(0).getKeyFrame(stateTimer, true);
            break;
      }
      return textureRegion;
   }

   public void updateState(PlayerAnimatorComponent.State state) {
      previousState = currentState;
      currentState = state;
   }

   public enum State {
      ROTATE_COUNTERCLOCKWISE, ROTATE_CLOCKWISE, STANDING;
   }
}
