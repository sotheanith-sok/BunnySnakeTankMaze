package TestingAshley.Components;

import TestingAshley.Components.Animators.Animator;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class AnimatorComponent implements Component {
    public Animator animator;

    public AnimatorComponent(Animator animator) {
        this.animator = animator;
    }

    public TextureRegion getFrame(float stateTimer) {
        return animator.getFrame(stateTimer);
    }
}
