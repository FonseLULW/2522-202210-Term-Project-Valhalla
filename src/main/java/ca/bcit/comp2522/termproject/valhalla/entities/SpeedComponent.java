package ca.bcit.comp2522.termproject.valhalla.entities;

import com.almasb.fxgl.entity.component.Component;

public class SpeedComponent extends Component {
    private double speed;

    public SpeedComponent(final double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(final double speed) {
        this.speed = speed;
    }
}
