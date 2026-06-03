package hohure.pedalking.artefacts.riders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import hohure.pedalking.enums.CollisionZone;
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
            switch (getContactZone(riderA, riderB)){
                case TOP -> riderB.move(Direction.DOWN, riderA.getRiderData().getSpeed());
                case BOTTOM -> riderA.move(Direction.DOWN, riderA.getRiderData().getSpeed());
                case CENTER -> {
                    Direction directionChoc = riderA.getRiderData().getDirection();
                    riderB.move(directionChoc, riderA.getRiderData().getSpeed()/2);
                    riderA.move(directionChoc.opposite(), riderA.getRiderData().getSpeed()/2);
                }
            }
        }
    }

    private static CollisionZone getContactZone(Rider self, Rider other) {

        var ySelf = self.getRectangle().getY();
        var yOther = other.getRectangle().getY();
        var riderHeight = self.getRectangle().getHeight();

        if(ySelf > yOther){
            if((ySelf - yOther) > riderHeight/4) return CollisionZone.TOP;
            return CollisionZone.CENTER;
        }
        else {
            if((yOther - ySelf) > riderHeight/4) return CollisionZone.BOTTOM;
            return CollisionZone.CENTER;
        }
    }

}
