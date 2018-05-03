package com.agilewhisperers.bunnysnaketankmaze.Utilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Utils {
   public static Drawable getColoredDrawable(int width, int height, Color color) {
      Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
      pixmap.setColor(color);
      pixmap.fill();

      TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));

      pixmap.dispose();

      return drawable;
   }

   public static Vector2 angleToVector(Vector2 outVector, float angle){
        outVector.x=-MathUtils.sin(angle);
        outVector.y=MathUtils.cos(angle);



        return outVector;
   }
    public static float vectorToAngle(Vector2 vector){
        return MathUtils.atan2(vector.y,vector.x);
    }
}
