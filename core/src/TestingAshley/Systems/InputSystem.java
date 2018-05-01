package TestingAshley.Systems;

import TestingAshley.Components.AttackComponent;
import TestingAshley.Components.MovementComponent;
import TestingAshley.Components.TagComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import javax.swing.text.html.HTML;

public class InputSystem extends IteratingSystem {
    private ComponentMapper<MovementComponent> movementComponents = ComponentMapper.getFor(MovementComponent.class);
    private ComponentMapper<AttackComponent> attackComponents = ComponentMapper.getFor(AttackComponent.class);
    private ComponentMapper<TagComponent>tagComponents=ComponentMapper.getFor(TagComponent.class);

    public InputSystem() {
        super(Family.all(MovementComponent.class,AttackComponent.class).get(), 10);
    }

    /**
     * This method is called on every entity on every update call of the EntitySystem. Override this to implement your system's
     * specific processing.
     *
     * @param entity    The current Entity being processed
     * @param deltaTime The delta time between the last and current frame
     */
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        movement(entity);
        attack(entity);
    }

    private void movement(Entity entity){
        MovementComponent mc = movementComponents.get(entity);
        //Player 1
       if(tagComponents.get(entity).getName().equals("Player1")){
          if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
             mc.setMoveForward(true);
          } else {
             mc.setMoveForward(false);
          }
          if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
             mc.setMoveBackward(true);
          } else {
             mc.setMoveBackward(false);
          }
          if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
             mc.setRotateCounterClockwise(true);
          } else {
             mc.setRotateCounterClockwise(false);
          }
          if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
             mc.setRotateClockwise(true);
          } else {
             mc.setRotateClockwise(false);
          }
       }
       //Player 2
       if(tagComponents.get(entity).getName().equals("Player2")){
          if (Gdx.input.isKeyPressed(Input.Keys.W)) {
             mc.setMoveForward(true);
          } else {
             mc.setMoveForward(false);
          }
          if (Gdx.input.isKeyPressed(Input.Keys.S)) {
             mc.setMoveBackward(true);
          } else {
             mc.setMoveBackward(false);
          }
          if (Gdx.input.isKeyPressed(Input.Keys.A)) {
             mc.setRotateCounterClockwise(true);
          } else {
             mc.setRotateCounterClockwise(false);
          }
          if (Gdx.input.isKeyPressed(Input.Keys.D)) {
             mc.setRotateClockwise(true);
          } else {
             mc.setRotateClockwise(false);
          }
       }
    }
    private void attack(Entity entity){
       AttackComponent ac= attackComponents.get(entity);
       if(tagComponents.get(entity).getName().equals("Player1")){
          if(Gdx.input.isKeyPressed(Input.Keys.M)){
             ac.setAttack(true);
          }else{
             ac.setAttack(false);
          }
       }
       if(tagComponents.get(entity).getName().equals("Player2")){
          if(Gdx.input.isKeyPressed(Input.Keys.Q)){
             ac.setAttack(true);
          }else{
             ac.setAttack(false);
          }
       }
    }
}
