package hohure.pedalking.artefacts.riders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import hohure.pedalking.enums.Direction;

import static hohure.pedalking.enums.RiderEffort.*;

public class PlayerRiderHandler {

    private PlayerRiderHandler(){};

    public static void handle(Rider rider, float groundCoef){

        var riderEffort = FREE_WHEEL;
        rider.updateEnvironnmentFactor(groundCoef);

        if(Gdx.graphics.getDeltaTime() == 0) return; // à l'initialisation, pour la première frame, cette valeur vaut 0 et casse tout le mécanisme de vitesse si elle est prise en compte

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            riderEffort = SPRINT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(riderEffort!=SPRINT) riderEffort = PEDAL;
            rider.updateSpeed(riderEffort, Gdx.graphics.getDeltaTime());
            rider.move(Direction.RIGHT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(riderEffort!=SPRINT) riderEffort = PEDAL;
            rider.updateSpeed(riderEffort, Gdx.graphics.getDeltaTime());
            rider.move(Direction.LEFT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if(riderEffort!=SPRINT) riderEffort = PEDAL;
            rider.updateSpeed(riderEffort, Gdx.graphics.getDeltaTime());
            rider.move(Direction.UP);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            riderEffort = BRAKE;
            rider.updateSpeed(riderEffort, Gdx.graphics.getDeltaTime());
            rider.move(Direction.DOWN);
        }

        if(riderEffort == FREE_WHEEL) { // aucune touche n'a été enregistré
            rider.updateSpeed(FREE_WHEEL, Gdx.graphics.getDeltaTime());
            rider.move(Direction.UP);
        }
    }

}
