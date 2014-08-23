package net.mymilkedeek.ludum.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

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
        shapeRenderer.circle(getX(), getY(), 50);
        shapeRenderer.end();

        batch.begin();
    }
}
