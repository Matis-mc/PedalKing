package hohure.pedalking.artefacts.riders;

import com.badlogic.gdx.Gdx;
import hohure.pedalking.enums.Direction;
import hohure.pedalking.enums.RiderEffort;

import java.util.Optional;

import static hohure.pedalking.utils.race.IAUtils.followRider;

public class OpponentRiderHandler {

    public static void handle(Rider opponent, float groundCoef, Optional<Rider> maybeTarget){
        opponent.updateEnvironnmentFactor(groundCoef);
        if(maybeTarget.isPresent()){
            followRider(opponent, maybeTarget.get());
        } else {
            opponent.ride(RiderEffort.PEDAL, Direction.UP);
        }
    }
}
