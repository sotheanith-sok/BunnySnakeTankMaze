package TestingAshley.Utilities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetManagingSystem {
    private static AssetManagingSystem manager;
    private AssetManager assetManager;

    private AssetManagingSystem() {

        assetManager = new com.badlogic.gdx.assets.AssetManager();
    }

    public static AssetManagingSystem getObject() {
        if (manager == null) {
            manager = new AssetManagingSystem();
        }
        return manager;
    }

    public void loadAssets() {
        assetManager.load("gameObjects/Environment.atlas", TextureAtlas.class);
        assetManager.load("gameObjects/Player.atlas", TextureAtlas.class);
        assetManager.load("gameObjects/Background.png", Texture.class);
        assetManager.load("gameObjects/Projectiles.atlas", TextureAtlas.class);
        assetManager.load("gameObjects/NPC.atlas", TextureAtlas.class);
        assetManager.finishLoading();
    }

    public com.badlogic.gdx.assets.AssetManager getAssetManager() {
        return assetManager;
    }
}
