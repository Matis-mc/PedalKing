package hohure.pedalking.artefacts.riders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import hohure.pedalking.enums.Direction;

public class Rider {

    // rider
    private Texture riderTexture;
    private Sprite riderSprite;
    private Rectangle rectangle;
    private RiderData riderData;

    public Rider(RiderData riderData){
        this.riderData = riderData;
        riderTexture = new Texture(riderData.getTexture());
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

    public void move(Direction direction, float speed){
        riderData.setDirection(direction);
        riderData.setSpeed(speed);
        switch (direction){
            case UP -> riderSprite.translateY(speed);
            case DOWN -> riderSprite.translateY(-speed);
            case RIGHT -> riderSprite.translateX(speed);
            case LEFT -> riderSprite.translateX(-speed);
        }
    }

    public float sprint(float speed){
        if(riderData.endurance <=0) return speed;
        riderData.endurance -= 1;
        return speed * 2;
    }

    public Rectangle getRectangle(){
        rectangle.set(riderSprite.getX(), riderSprite.getY(), riderSprite.getWidth(), riderSprite.getHeight());
        return rectangle;
    }

    public RiderData getRiderData(){
        return riderData;
    }

}
