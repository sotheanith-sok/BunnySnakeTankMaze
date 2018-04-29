package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.agilewhisperers.bunnysnaketankmaze.entities.Bullet;
import com.agilewhisperers.bunnysnaketankmaze.entities.GameObject;
import com.badlogic.gdx.utils.Array;


/**
 * Database of all gameObject.
 */
public class GameObjectManager {
    private static GameObjectManager single_instance;
    private Array<GameObject> gameObjectList;
    private BulletPool bulletPoolForPlayer1,bulletPoolForPlayer2;

    private GameObjectManager() {
        gameObjectList = new Array<>();
        bulletPoolForPlayer1 = new BulletPoolForPlayer1();
        bulletPoolForPlayer2 =new BulletPoolForPlayer2();
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

        gameObjectList.insert(0, gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        gameObject.getStats().setExist(false);
        gameObjectList.removeValue(gameObject, true);
        if (gameObject instanceof Script) {
            ScriptManager.getObject().removeScriptListener((Script) gameObject);
        }
    }


    /**
     * Get the list of all gameObject.
     *
     * @return gameObject list.
     */
    public Array<GameObject> getAllGameObjects() {
        return gameObjectList;
    }

    public Bullet getBullet(boolean isPlayer1) {
        if(isPlayer1){
            return bulletPoolForPlayer1.obtain();
        }else {
            return bulletPoolForPlayer2.obtain();
        }

    }

    public void freeBullet(Bullet bullet, boolean isPlayer1) {

        if(isPlayer1){
            bulletPoolForPlayer1.free(bullet);
        }else {
           bulletPoolForPlayer2.free(bullet);
        }
    }
}
