package com.agilewhisperers.bunnysnaketankmaze.components;

import com.agilewhisperers.bunnysnaketankmaze.systems.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * This is the component used to interact with the rendering engine.
 */
public class Sprite {
    private TextureAtlas textureAtlas;
    private Animation<TextureRegion> animation;

    public Sprite(String path, float frameRate) {
        textureAtlas = AssetManager.getObject().getAssetManager().get(path, TextureAtlas.class);
        animation=new Animation(frameRate,textureAtlas.getRegions());
    }

   public Animation getAnimation() {
      return animation;
   }
}
