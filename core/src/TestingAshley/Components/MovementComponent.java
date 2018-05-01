package TestingAshley.Components;

import com.badlogic.ashley.core.Component;

public class MovementComponent implements Component {
    private boolean moveForward = false;
    private boolean moveBackward = false;
    private boolean rotateClockwise = false;
    private boolean rotateCounterClockwise = false;

    public boolean isMoveForward() {
        return moveForward;
    }

    public void setMoveForward(boolean moveForward) {
        this.moveForward = moveForward;
    }

    public boolean isMoveBackward() {
        return moveBackward;
    }

    public void setMoveBackward(boolean moveBackward) {
        this.moveBackward = moveBackward;
    }

    public boolean isRotateClockwise() {
        return rotateClockwise;
    }

    public void setRotateClockwise(boolean rotateClockwise) {
        this.rotateClockwise = rotateClockwise;
    }

    public boolean isRotateCounterClockwise() {
        return rotateCounterClockwise;
    }

    public void setRotateCounterClockwise(boolean rotateCounterClockwise) {
        this.rotateCounterClockwise = rotateCounterClockwise;
    }
}
