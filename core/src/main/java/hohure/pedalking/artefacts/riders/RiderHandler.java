package hohure.pedalking.artefacts.riders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class RiderHandler {

    private RiderHandler(){};

    public static void handle(Rider rider){
        float speed = 4f;
        float delta = Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            speed = rider.sprint(speed);
        }

        float distance = speed * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            rider.getSprite().translateX(distance);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            rider.getSprite().translateX(-distance);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            rider.moveForward(distance);
        }
    }

}
