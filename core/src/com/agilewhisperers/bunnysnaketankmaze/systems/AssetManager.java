package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.badlogic.gdx.graphics.Texture;

/**
 * Use to load asset.
 */
public class AssetManager {
   private static AssetManager manager;
   private com.badlogic.gdx.assets.AssetManager assetManager;
   private AssetManager(){
      assetManager=new com.badlogic.gdx.assets.AssetManager();
   }

   public static AssetManager getObject(){
      if(manager==null){
         manager=new AssetManager();
      }
      return manager;
   }
   public void loadAssets(){
      assetManager.load("gameObjects/Bullet.png", Texture.class);
      assetManager.load("gameObjects/Player.jpg", Texture.class);
      assetManager.load("gameObjects/Wall.png", Texture.class);

   }

   public com.badlogic.gdx.assets.AssetManager getAssetManager() {
      return assetManager;
   }
}
