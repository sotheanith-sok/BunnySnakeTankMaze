package com.agilewhisperers.bunnysnaketankmaze.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * This is the component used to interact with the rendering engine.
 */
public class Sprite{
   private Texture texture;
   private com.badlogic.gdx.graphics.g2d.Sprite sprite;
   public Sprite(String path){
      texture=new Texture(Gdx.files.internal(path));
      texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
      sprite=new com.badlogic.gdx.graphics.g2d.Sprite(texture);
      sprite.flip(false,true);
      sprite.setOriginCenter();
   }

    /**
     * Is the sprite flipped by the x-axis
     * @return isFlipX?
     */
   public boolean isFlipX() {
      return sprite.isFlipX();
   }

    /**
     * Mortify if the sprite is flipped by the x-axis.
     * @param flipX
     */
   public void setFlipX(boolean flipX) {
      sprite.setFlip(flipX,sprite.isFlipY());
   }

    /**
     * Is the sprite flipped by the y-axis
     * @return isFlipY?
     */
   public boolean isFlipY() {
       return sprite.isFlipY();
   }

    /**
     * Mortify if the sprite is flipped by the x-axis.
     * @param flipY
     */
   public void setFlipY(boolean flipY) {
       sprite.setFlip(sprite.isFlipX(),flipY);
   }

    /**
     * Get the texture of this object
     * @return Texture
     */
   public Texture getTexture() {
      return texture;
   }

    /**
     * Set the texture of this object
     * @param texture
     */
   public void setTexture(Texture texture) {
      this.texture = texture;
   }

    /**
     * Get the sprite of this sprite
     * @return sprite
     */
   public com.badlogic.gdx.graphics.g2d.Sprite getSprite() {
      return sprite;
   }

    /**
     * Set the sprite of this sprite
     * @param sprite
     */
   public void setSprite(com.badlogic.gdx.graphics.g2d.Sprite sprite) {
      this.sprite = sprite;
   }

    /**
     * Set the position of this sprite
     * @param x
     * @param y
     */
   public void setPosition(float x, float y){
       sprite.setPosition(x,y);
   }

    /**
     * Set the boundery of this sprite
     * @param width
     * @param height
     */
   public void setSize(float width, float height){
       sprite.setSize(width,height);
   }

    /**
     * Set the rotation of this sprite.
     * @param degree
     */
   public void setRotation(float degree){
       sprite.setRotation(degree);
       sprite.setOriginCenter();
   }

}
