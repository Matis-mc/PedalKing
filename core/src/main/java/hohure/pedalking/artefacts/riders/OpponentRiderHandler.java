package hohure.pedalking.artefacts.riders;

import com.badlogic.gdx.Gdx;
import hohure.pedalking.enums.Direction;
import hohure.pedalking.enums.RiderEffort;

public class OpponentRiderHandler {

    public static void handle(Rider opponent, float groundCoef){
        opponent.updateEnvironnmentFactor(groundCoef);
        opponent.updateSpeed(RiderEffort.PEDAL, Gdx.graphics.getDeltaTime());
        opponent.move(Direction.UP);
    }
}
