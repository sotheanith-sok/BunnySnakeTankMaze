package com.agilewhisperers.bunnysnaketankmaze.components;

import com.agilewhisperers.bunnysnaketankmaze.systems.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * This is the component used to interact with the rendering engine.
 */
public class Sprite {
   private TextureAtlas textureAtlas;
   private TextureRegion texture;

   public Sprite(String path, String name, int index) {
      textureAtlas = AssetManager.getObject().getAssetManager().get(path, TextureAtlas.class);
      texture = textureAtlas.findRegion(name, index);
   }

   public TextureRegion getTexture() {
      return texture;
   }
}
