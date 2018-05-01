package TestingAshley.Components;

import com.badlogic.ashley.core.Component;

public class AttackComponent implements Component {
    private boolean attack = false;

    public boolean isAttack() {
        return attack;
    }

    public void setAttack(boolean attack) {
        this.attack = attack;
    }
}
