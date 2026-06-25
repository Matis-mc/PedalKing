package hohure.pedalking.managers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import hohure.pedalking.artefacts.race.matrice.Cell;
import hohure.pedalking.artefacts.race.matrice.ZoneMatrice;
import hohure.pedalking.artefacts.riders.*;
import hohure.pedalking.screen.BackgroundMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static hohure.pedalking.enums.RiderType.IA;
import static hohure.pedalking.enums.RiderType.PLAYER;
import static hohure.pedalking.utils.constant.GameConstant.NB_OPPONENT;
import static hohure.pedalking.utils.race.PelotonUtils.findClosestFrontRider;

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
        for(int i =0; i < NB_OPPONENT; i++){
            riders.add(new Rider(IA, new RiderData(100, 100, "riders/decathlon-rider.png")));
        }

    }

    public List<Rider> getRiders() {
        return this.riders;
    }

    public void show(){
        ZoneMatrice zoneDepart = new ZoneMatrice(10f, 10f, 15f, 10f);
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
                Optional<Rider> maybeTarget = findClosestFrontRider(o1, riders);
                OpponentRiderHandler.handle(o1, coef, maybeTarget);
            }
            for (Rider o2 : riders){
                if(o1 != o2){
                    CollisionRiderHandler.handleCollision(o1, o2);
                }
            }
        }
        riders = riders.stream().sorted(Comparator.comparing(Rider::getYPosition).reversed()).toList();
    }

    public void draw(SpriteBatch batch){
        riders.forEach(o -> o.draw(batch));
    }

    public void drawShape(ShapeRenderer renderer){
        riders.forEach(o ->
        {
            var rect = o.getRectangle();
            renderer.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        });
    }

    public void dispose() {
        riders.forEach(
            Rider::dispose
        );
    }

    public Rider getPlayer(){
        return this.player;
    }

    public int getRankRider(Rider r){
        return this.riders.indexOf(r) + 1;
    }

    public Sprite getPlayerSprite(){
        return player.getSprite();
    }
}
