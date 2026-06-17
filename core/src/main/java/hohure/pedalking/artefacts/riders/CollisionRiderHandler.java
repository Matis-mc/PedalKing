package hohure.pedalking.artefacts.riders;

import hohure.pedalking.enums.CollisionZone;
import hohure.pedalking.enums.Direction;

public class CollisionRiderHandler {

    private CollisionRiderHandler(){

    }

    public static void handleCollision(Rider riderA, Rider riderB){
        if(riderA.getRectangle().overlaps(riderB.getRectangle())){
            switch (getContactZone(riderA, riderB)){
                case TOP -> {
                    riderB.moveAfterCollision(Direction.DOWN);
                }
                case BOTTOM -> {
                    riderA.moveAfterCollision(Direction.DOWN);
                }
                case CENTER -> {
                    Direction directionChoc = riderA.getRiderData().getDirection();
                    riderB.moveAfterCollision(directionChoc);
                    riderA.moveAfterCollision(directionChoc.opposite());
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
