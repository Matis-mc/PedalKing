package hohure.pedalking.artefacts.riders;

import hohure.pedalking.enums.Direction;

public class RiderData {

    int endurance;
    int power;
    float speed;
    Direction direction;
    String texture;

    public RiderData(int endurance, int power, String texture){
        this.endurance = endurance;
        this.power = power;
        this.texture = texture;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
