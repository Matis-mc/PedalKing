package hohure.pedalking.artefacts.riders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import hohure.pedalking.enums.Direction;

public class PlayerRiderHandler {

    private PlayerRiderHandler(){};

    public static void handle(Rider rider){
        float speed = 4f * Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            speed = rider.sprint(speed);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            rider.move(Direction.RIGHT, speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            rider.move(Direction.LEFT, speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            rider.move(Direction.UP, speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            rider.move(Direction.DOWN, speed);
        }
    }

    public static void handleCollision(Rider riderA, Rider riderB){
        if(riderA.getRectangle().overlaps(riderB.getRectangle())){
            Direction directionChoc = riderA.getRiderData().getDirection();
            riderB.move(directionChoc, riderA.getRiderData().getSpeed());
            riderA.move(directionChoc.opposite(), riderA.getRiderData().getSpeed());
        }
    }

}
