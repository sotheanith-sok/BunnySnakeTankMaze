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
       assetManager.load("gameObjects/Background.png",Texture.class);
       assetManager.load("gameObjects/Projectiles.atlas",TextureAtlas.class);

    }

    public com.badlogic.gdx.assets.AssetManager getAssetManager() {
        return assetManager;
    }
}
