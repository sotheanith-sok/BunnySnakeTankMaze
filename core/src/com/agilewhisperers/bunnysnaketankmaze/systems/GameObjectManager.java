package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.agilewhisperers.bunnysnaketankmaze.entities.Bullet;
import com.agilewhisperers.bunnysnaketankmaze.entities.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Database of all gameObject.
 */
public class GameObjectManager {
    private static GameObjectManager single_instance;
    private List<GameObject> gameObjectList;
    private BulletPool bulletPool;

    private GameObjectManager() {
        gameObjectList = new ArrayList<GameObject>();
        bulletPool = new BulletPool(250, 1000);
    }

    public static GameObjectManager getObject() {
        if (single_instance == null) {
            single_instance = new GameObjectManager();
        }
        return single_instance;
    }


    /**
     * Add gameObject to the database
     *
     * @param gameObject
     */
    public void addGameObject(GameObject gameObject) {

        gameObjectList.add(0,gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        gameObject.getState().isExist = false;
        gameObjectList.remove(gameObject);
        if (gameObject instanceof Script) {
            ScriptManager.getObject().removeScriptListener((Script) gameObject);
        }
    }


    /**
     * Get the list of all gameObject.
     *
     * @return gameObject list.
     */
    public List<GameObject> getAllGameObjects() {
        return gameObjectList;
    }

    public Bullet getBullet() {
        return bulletPool.obtain();
    }

    public void freeBullet(Bullet bullet) {
        bulletPool.free(bullet);
    }
}
