package TestingAshley.Components;

import TestingAshley.Utilities.AssetManagingSystem;
import com.agilewhisperers.bunnysnaketankmaze.systems.AssetManager;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class SpriteComponent implements Component {
   private TextureAtlas textureAtlas;
   private TextureRegion texture;

   public SpriteComponent(String path, String name, int index) {
      textureAtlas = AssetManagingSystem.getObject().getAssetManager().get(path, TextureAtlas.class);
      texture = textureAtlas.findRegion(name, index);
   }

   public TextureRegion getTexture() {
      return texture;
   }
}
