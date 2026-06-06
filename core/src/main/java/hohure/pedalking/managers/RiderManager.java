package hohure.pedalking.managers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import hohure.pedalking.artefacts.race.matrice.Cell;
import hohure.pedalking.artefacts.race.matrice.ZoneMatrice;
import hohure.pedalking.artefacts.riders.OpponentRiderHandler;
import hohure.pedalking.artefacts.riders.PlayerRiderHandler;
import hohure.pedalking.artefacts.riders.Rider;
import hohure.pedalking.artefacts.riders.RiderData;
import hohure.pedalking.screen.BackgroundMap;

import java.util.ArrayList;
import java.util.List;

import static hohure.pedalking.enums.RiderType.IA;
import static hohure.pedalking.enums.RiderType.PLAYER;

public class RiderManager {

    private List<Rider> riders;
    private Rider player;
    private BackgroundMap backgroundMap;

    public RiderManager(){
        riders = new ArrayList<>();
        backgroundMap = BackgroundMap.getInstance();
    }

    public void generate() {

        // PLAYER
        player = new Rider(PLAYER, new RiderData(100, 100, "riders/rider.png"));
        riders.add(player);

        // IA
        for(int i =0; i <25; i++){
            riders.add(new Rider(IA, new RiderData(100, 100, "riders/decathlon-rider.png")));
        }

    }

    public List<Rider> getRiders() {
        return this.riders;
    }

    public void show(){
        ZoneMatrice zoneDepart = new ZoneMatrice(10f, 10f, 10f, 10f);
        Cell cell = zoneDepart.getFirstEmptyCell();
        for(Rider r : riders){
            r.getSprite().setCenter(cell.getX(), cell.getY());
            cell = zoneDepart.getNextCell(cell);
        }
    }

    public void update() {

        float coef = backgroundMap.interactWithMap((int)player.getSprite().getX(), (int)player.getSprite().getY());
        PlayerRiderHandler.handle(player, coef);

        for (Rider o1 : riders){
            if(IA.equals(o1.getType())) {
                coef = backgroundMap.interactWithMap((int)o1.getSprite().getX(), (int)o1.getSprite().getY());
                OpponentRiderHandler.handle(o1, coef);
            }
            for (Rider o2 : riders){
                if(o1 != o2){
                    PlayerRiderHandler.handleCollision(o1, o2);
                }
            }
        }
    }

    public void draw(SpriteBatch batch){
        riders.forEach(o -> o.draw(batch));
    }

    public void dispose() {
        riders.forEach(
            Rider::dispose
        );
    }

    public Sprite getPlayerSprite(){
        return player.getSprite();
    }
}
