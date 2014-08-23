package net.mymilkedeek.ludum.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import net.mymilkedeek.ludum.listener.WorldInputListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MyMilkedEek
 */
public class World extends Actor {

    private List<String> excesses;
    private List<String> shortages;

    // todo improve rendering
    private ShapeRenderer shapeRenderer;
    private float radius;

    public World(List<String> excesses, List<String> shortages) {
        this.excesses = excesses;
        this.shortages = shortages;

        if (excesses == null) {
            excesses = new ArrayList<String>();
        }

        if (shortages == null) {
            shortages = new ArrayList<String>();
        }

        // todo rendering
        shapeRenderer = new ShapeRenderer();
        setColor(Color.BLUE);

        addListener(new WorldInputListener());
    }

    public List<String> getExcesses() {
        return excesses;
    }

    public List<String> getShortages() {
        return shortages;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(getColor());
        shapeRenderer.circle(getX()+radius, getY()+radius, radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
        shapeRenderer.end();

        batch.begin();
    }

    public void setLocation(float x, float y, float radius) {
        setBounds(x - radius, y - radius, radius*2, radius*2);
        this.radius = radius;
    }
}
