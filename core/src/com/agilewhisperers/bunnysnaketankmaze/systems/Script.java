package com.agilewhisperers.bunnysnaketankmaze.systems;

/**
 * Need this implement this in order for game object to work with ScriptManager
 */
public interface Script {
    /**
     * This method will be call every game loop.
     */
    void runObjectScript();
}
