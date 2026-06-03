package hohure.pedalking.artefacts.riders;

import com.badlogic.gdx.Gdx;
import hohure.pedalking.enums.Direction;

public class OpponentRiderHandler {

    public static void handle(Rider opponent){

        float speed = 4f * Gdx.graphics.getDeltaTime();

        opponent.move(Direction.UP, speed);


    }
}
