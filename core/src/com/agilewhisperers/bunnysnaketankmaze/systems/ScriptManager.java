package com.agilewhisperers.bunnysnaketankmaze.systems;


import com.agilewhisperers.bunnysnaketankmaze.entities.GameObject;
import com.badlogic.gdx.utils.Array;

/**
 * The Script system.
 */
public class ScriptManager {
    private static ScriptManager single_instance;
    Array<Script> scriptList;

    private ScriptManager() {
        scriptList = new Array<>();
    }

    public static ScriptManager getObject() {
        if (single_instance == null) {
            single_instance = new ScriptManager();
        }
        return single_instance;
    }

    /**
     * Add listener to script engine so it knows who to call.
     *
     * @param script object that implement script.
     */
    public void addScriptListener(Script script) {
        scriptList.add(script);
    }


    /**
     * Run script of all listeners.
     *
     * @param timeStep
     */
    public boolean runScripts(float timeStep) {
        for (int i = 0; i < scriptList.size; i++) {
            scriptList.get(i).runObjectScript(timeStep);
        }
        return true;
    }

    public void removeScriptListener(Script script) {
        scriptList.removeValue(script, true);
    }

    public void resetPreviousPosition() {
        Array<GameObject> gameObjectArray = GameObjectManager.getObject().getAllGameObjects();
        for (int i = 0; i < gameObjectArray.size; i++) {
            gameObjectArray.get(i).getBody().updatePreviousState();
        }
    }

    public void clean() {
        scriptList.clear();
        single_instance = null;
    }
}
