package ca.bcit.comp2522.termproject.valhalla.compnent;

import com.almasb.fxgl.entity.component.Component;

@Deprecated
public class ControllableComponent extends Component {
    private double speed;

    public ControllableComponent(final double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(final double speed) {
        this.speed = speed;
    }
}
