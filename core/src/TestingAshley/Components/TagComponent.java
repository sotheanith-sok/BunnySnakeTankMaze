package TestingAshley.Components;

import com.badlogic.ashley.core.Component;

public class TagComponent implements Component {
    private boolean visible = true;
    private int layer = 0;
    private boolean exist = true;
    private String name;

    public TagComponent(String name) {
        this.name = name;
    }

    public boolean isVisible() {
        return visible;
    }

    public int getLayer() {
        return layer;
    }

    public boolean isExist() {
        return exist;
    }

    public String getName() {
        return name;
    }
}
