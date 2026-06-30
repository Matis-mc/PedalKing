package hohure.pedalking.artefacts.riders;

import com.badlogic.gdx.Gdx;
import hohure.pedalking.enums.RiderEffort;

public class RiderSpeed {

    // todo : ces données devront être propre à chaque rider;
    private float currentSpeed = 0f;
    private float maxSpeed = 5f;
    private float maxSpeedTheoric = 5f;
    private float maxSprintSpeedTheoric = 10f;
    private float maxSprintSpeed = 10f;
    private float acceleration = 2f;
    private float deceleration = 2f;
    private float brakeFactor = 4f;
    private float sprintFactor = 5f;
    private float environmentFactor = 1f;

    public void update(RiderEffort riderEffort){
        float delta = Gdx.graphics.getDeltaTime();
        switch (riderEffort){
            case BRAKE -> {
                currentSpeed -= deceleration * brakeFactor * delta;
                currentSpeed  = Math.max(currentSpeed, 0f);
            }
            case FREE_WHEEL -> {
                currentSpeed -= deceleration * delta;
                currentSpeed  = Math.max(currentSpeed, 0f);
            }
            case PEDAL -> {
                currentSpeed += acceleration * delta;
                currentSpeed  = Math.min(currentSpeed, maxSpeed);
            }
            case SPRINT -> {
                currentSpeed += acceleration * sprintFactor * delta;
                currentSpeed  = Math.min(currentSpeed, maxSprintSpeed);
            }
        }
    }

    public float getCurrentSpeed() {
        return currentSpeed * Gdx.graphics.getDeltaTime();
    }

    public void setEnvironmentFactor(float environmentFactor) {
        this.environmentFactor = environmentFactor;
        maxSpeed = maxSpeedTheoric * this.environmentFactor;
        maxSprintSpeed = maxSprintSpeedTheoric * this.environmentFactor;
    }
}
