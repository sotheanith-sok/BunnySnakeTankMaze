package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Use to load asset.
 */
public class AssetManager {
    private static AssetManager manager;
    private com.badlogic.gdx.assets.AssetManager assetManager;

    private AssetManager() {
        assetManager = new com.badlogic.gdx.assets.AssetManager();
    }

    public static AssetManager getObject() {
        if (manager == null) {
            manager = new AssetManager();
        }
        return manager;
    }

    public void loadAssets() {
        assetManager.load("gameObjects/Environment.atlas", TextureAtlas.class);
        assetManager.load("gameObjects/Player.atlas", TextureAtlas.class);
        assetManager.load("gameObjects/Snake.atlas",TextureAtlas.class);
        assetManager.load("gameObjects/RedFireball.atlas",TextureAtlas.class);
       assetManager.load("gameObjects/BlueFireball.atlas",TextureAtlas.class);
       assetManager.load("gameObjects/Background.png",Texture.class);

    }

    public com.badlogic.gdx.assets.AssetManager getAssetManager() {
        return assetManager;
    }
}
