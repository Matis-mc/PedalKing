package hohure.pedalking.utils.race;

import com.badlogic.gdx.graphics.g2d.Sprite;
import hohure.pedalking.artefacts.riders.Rider;

import java.util.List;
import java.util.Optional;

public class PelotonUtils {

    public static Optional<Rider> findClosestFrontRider(Rider r, List<Rider> peloton){
        Rider closestRider = null;
        float distance = -1;
        for(Rider opponent : peloton){
            if(r.equals(opponent)) continue;
            if((distance == -1) || (distance > getDistanceOnYBetweenRider(r, opponent))){
                closestRider = opponent;
                distance = getDistanceOnYBetweenRider(r, opponent);
            }
        }
        return Optional.ofNullable(closestRider);
    }

    public static float getDistanceOnYBetweenRider(Rider a, Rider b){
        return a.getSprite().getY() - b.getSprite().getY();
    }

    public static boolean isInAspiration(Sprite riderFront, Sprite riderBehind ){
        return isInAspirationOnX(riderFront, riderBehind) && isInAspirationOnY(riderFront, riderBehind);
    }

    private static boolean isInAspirationOnX(Sprite riderFront, Sprite riderBehind ){
        float riderWidth = riderFront.getWidth();
        return ( (riderFront.getX() - riderWidth / 2) < riderBehind.getX())
            && (riderBehind.getX() < (riderFront.getX() + riderWidth * 1.5));
    }

    private static boolean isInAspirationOnY(Sprite riderFront, Sprite riderBehind ){
        float riderHeight = riderFront.getHeight();
        return ( riderBehind.getY() < riderFront.getY() )
            && ( riderBehind.getY() > (riderFront.getY() - riderHeight ));
    }

}
