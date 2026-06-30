package hohure.pedalking.artefacts.riders;

import com.badlogic.gdx.Gdx;
import hohure.pedalking.enums.Direction;
import hohure.pedalking.enums.RiderEffort;

public class RiderData {

    private float endurance;
    private float maxEndurance;
    private float exhaustionFactor = 0.2f;
    private float regenFactor = 0.1f;
    private float power;

    private float sprintFactor = 10f;
    private float sprintEndurance;
    private float sprintMaxEndurance;

    Direction direction;
    String texturePath;

    public RiderData(float endurance, int power, String texturePath){ // todo : sera récupéré de la config de chaque joueur
        this.endurance = endurance;
        this.maxEndurance = endurance;
        this.sprintEndurance = endurance * 0.1f;
        this.sprintMaxEndurance = endurance * 0.1f;
        this.power = power;
        this.texturePath = texturePath;
        this.direction = Direction.UP;
    }

    public void updateEndurance(RiderEffort riderEffort){
        var delta = Gdx.graphics.getDeltaTime();
        switch (riderEffort){
            case BRAKE, FREE_WHEEL -> {
                this.endurance += regenFactor * delta;
                this.endurance = Math.min(endurance, maxEndurance);
                this.sprintEndurance += regenFactor * delta;
                this.sprintEndurance = Math.min(sprintEndurance, sprintMaxEndurance);
            }
            case PEDAL -> {
                this.endurance -= exhaustionFactor * delta;
                this.endurance = Math.max(endurance, 0f);
            }
            case SPRINT -> {
                this.endurance -= exhaustionFactor * delta;
                this.endurance = Math.max(endurance, 0f);
                this.sprintEndurance -= exhaustionFactor * sprintFactor * delta;
                this.sprintEndurance = Math.max(sprintEndurance, 0f);
            }
        }
    }

    public float getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public float getPower() {
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

    public float getSprintEndurance() {
        return sprintEndurance;
    }
}
