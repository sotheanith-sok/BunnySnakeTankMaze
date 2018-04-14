package com.agilewhisperers.bunnysnaketankmaze.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Sprite{
   private boolean flipX, flipY;
   private Texture texture;
   private com.badlogic.gdx.graphics.g2d.Sprite sprite;
   public Sprite(String path){
      flipX=false;
      flipY=true;
      texture=new Texture(Gdx.files.internal(path));
      texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
      sprite=new com.badlogic.gdx.graphics.g2d.Sprite(texture);
      sprite.flip(false,true);
   }

   public boolean isFlipX() {
      return flipX;
   }

   public void setFlipX(boolean flipX) {
      this.flipX = flipX;
   }

   public boolean isFlipY() {
      return flipY;
   }

   public void setFlipY(boolean flipY) {
      this.flipY = flipY;
   }

   public Texture getTexture() {
      return texture;
   }

   public void setTexture(Texture texture) {
      this.texture = texture;
   }

   public com.badlogic.gdx.graphics.g2d.Sprite getSprite() {
      return sprite;
   }

   public void setSprite(com.badlogic.gdx.graphics.g2d.Sprite sprite) {
      this.sprite = sprite;
   }
}
