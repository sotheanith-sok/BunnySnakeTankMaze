package TestingAshley.Components;

import TestingAshley.Utilities.Utils;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;


public class HealthBarComponent extends ProgressBar implements Component {
   public HealthBarComponent(int width, int height){
      super(0f, 1f, 0.01f, false, new ProgressBarStyle());
      getStyle().background = Utils.getColoredDrawable(width, height, Color.RED);
      getStyle().knob = Utils.getColoredDrawable(0, height, Color.GREEN);
      getStyle().knobBefore = Utils.getColoredDrawable(width, height, Color.GREEN);
      setWidth(width);
      setHeight(height);
      setAnimateDuration(0.0f);
      setValue(1f);
      setAnimateDuration(0.25f);
   }

}
