package hohure.pedalking.artefacts.riders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class RiderHandler {

    private RiderHandler(){};

    public static void handle(Rider rider){
        float speed = 4f;
        float delta = Gdx.graphics.getDeltaTime();


        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            rider.getSprite().translateX(speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            rider.getSprite().translateX(-speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            rider.getSprite().translateY(speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            rider.getSprite().translateY(-speed * delta);
        }
    }

}
