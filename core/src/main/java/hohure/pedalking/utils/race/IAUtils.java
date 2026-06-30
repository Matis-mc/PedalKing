package hohure.pedalking.utils.race;

import com.badlogic.gdx.graphics.g2d.Sprite;
import hohure.pedalking.artefacts.riders.Rider;
import hohure.pedalking.enums.Direction;
import hohure.pedalking.enums.RiderEffort;

import static hohure.pedalking.utils.race.PelotonUtils.getDistanceOnYBetweenRider;

public class IAUtils {

    public static void followRider(Rider subject, Rider target){
        if (isRidersCloseEnought(subject, target)) {
            if (targetIsOnTheRight(subject.getSprite(), target.getSprite())) {
                subject.ride(RiderEffort.PEDAL, Direction.RIGHT);
            } else if (targetIsOnTheLeft(subject.getSprite(), target.getSprite())) {
                subject.ride(RiderEffort.PEDAL, Direction.LEFT);
            }
        }
        subject.ride(RiderEffort.PEDAL, Direction.UP);
    }

    private static boolean isRidersCloseEnought(Rider subject, Rider target){
        float distance = getDistanceOnYBetweenRider(subject, target);
        return distance < 0 && distance >  subject.getSprite().getHeight() * -2;  // target is behind and not too far
    }

    private static boolean targetIsOnTheLeft(Sprite target, Sprite subject){
        return target.getX() > subject.getX() + subject.getWidth() / 2;
    }

    private static boolean targetIsOnTheRight(Sprite target, Sprite subject){
        return target.getX() < subject.getX() - subject.getWidth() / 2;
    }

}
