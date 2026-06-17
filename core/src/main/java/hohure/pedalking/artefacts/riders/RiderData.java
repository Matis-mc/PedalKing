package hohure.pedalking.artefacts.riders;

import hohure.pedalking.enums.Direction;

public class RiderData {

    int endurance;
    int power;
    Direction direction;
    String texturePath;

    public RiderData(int endurance, int power, String texturePath){
        this.endurance = endurance;
        this.power = power;
        this.texturePath = texturePath;
        this.direction = Direction.UP;
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

    public String getTexturePath() {
        return texturePath;
    }

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
