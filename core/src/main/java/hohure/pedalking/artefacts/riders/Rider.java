package hohure.pedalking.artefacts.riders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import hohure.pedalking.enums.Direction;
import hohure.pedalking.enums.RiderEffort;
import hohure.pedalking.enums.RiderType;

public class Rider {

    // rider
    private RiderType type;
    private Texture riderTexture;
    private Sprite riderSprite;
    private Rectangle rectangle;
    private RiderData riderData;
    private RiderSpeed riderSpeed;

    public Rider(RiderType type, RiderData riderData){
        this.type = type;
        this.riderData = riderData;
        riderTexture = new Texture(riderData.getTexturePath());
        riderSpeed = new RiderSpeed();
        riderSprite = new Sprite(riderTexture);

        float riderWitdh = (riderTexture.getWidth() / 32f) / 2;
        float riderHeight = (riderTexture.getHeight() / 32f) / 2;
        riderSprite.setSize(riderWitdh, riderHeight);
        rectangle = new Rectangle();
    }

    public Sprite getSprite(){
        return riderSprite;
    }

    public void draw(SpriteBatch batch){
        riderSprite.draw(batch);
    }

    public void dispose(){
        riderTexture.dispose();
    }

    public void updateSpeed(RiderEffort riderEffort, float delta){
        this.riderSpeed.update(riderEffort, delta);
    }

    public void updateEnvironnmentFactor(float factor){
        this.riderSpeed.setEnvironmentFactor(factor);
    }

    public void move(Direction direction){
        riderData.setDirection(direction);
        var distance = riderSpeed.getCurrentSpeed();
        switch (direction){
            case UP,DOWN -> riderSprite.translateY(distance); // on freine = on avance vers le haut moins vite
            case RIGHT -> riderSprite.translateX(distance);
            case LEFT -> riderSprite.translateX(-distance);
        }
    }

    public void moveAfterCollision(Direction direction){
        // on gère le déplacement suite à un choc en une frame, sans vitesse prise en compte
        switch (direction){
            case UP -> riderSprite.translateY(0.2f);
            case DOWN -> riderSprite.translateY(-0.2f);
            case RIGHT -> riderSprite.translateX(0.2f);
            case LEFT -> riderSprite.translateX(-0.2f);
        }
    }

    public Rectangle getRectangle(){
        rectangle.set(riderSprite.getX()+0.1f, riderSprite.getY()+0.3f, riderSprite.getWidth()-0.2f, riderSprite.getHeight()-0.5f);
        return rectangle;
    }

    public RiderData getRiderData(){
        return riderData;
    }

    public RiderSpeed getRiderSpeed() {
        return riderSpeed;
    }

    public RiderType getType(){
        return type;
    }

    public float getYPosition(){
        return this.riderSprite.getY();
    }

}
