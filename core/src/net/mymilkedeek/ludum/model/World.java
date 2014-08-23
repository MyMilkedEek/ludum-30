package net.mymilkedeek.ludum.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private String showResourcesString = "";
    private WorldInputListener inputListener;

    private boolean displayResources;
    private float radius;

    // todo improve rendering
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;


    public World(List<String> excesses, List<String> shortages) {
        this.excesses = excesses;
        this.shortages = shortages;

        if (excesses == null) {
            excesses = new ArrayList<String>();
        }

        if (shortages == null) {
            shortages = new ArrayList<String>();
        }

        for ( String s : excesses ) {
            showResourcesString += "+" + s + " ";
        }

        if ( !shortages.isEmpty() ) {
            showResourcesString += "\n";
        }

        for ( String s : shortages ) {
            showResourcesString += "-" + s + " ";
        }

        // todo rendering
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        setColor(Color.BLUE);

        inputListener = new WorldInputListener(this);
        addListener(inputListener);

        displayResources = false;
    }

    public List<String> getExcesses() {
        return excesses;
    }

    public List<String> getShortages() {
        return shortages;
    }

    public float getRadius() {
        return radius;
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

        if (displayResources) {
            renderAvailableResources(batch, shapeRenderer);
        }

        if ( inputListener.isDragging() ) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.line(getX()+radius, getY()+radius, inputListener.getLastDraggedPosition().x, inputListener.getLastDraggedPosition().y);
            shapeRenderer.end();
        }

        batch.begin();
    }

    private void renderAvailableResources(Batch batch, ShapeRenderer shapeRenderer) {
        BitmapFont.TextBounds bounds = font.getBounds(showResourcesString);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(getX() + radius, (float) (getY() + radius + (radius *1.25)), bounds.width, bounds.height + 2);
        shapeRenderer.end();

        batch.begin();

        font.setColor(Color.WHITE);
        font.draw(batch, showResourcesString, getX() + radius, (float) (getY() + radius + (radius *1.25) + bounds.height));

        batch.end();
    }

    public void setLocation(float x, float y, float radius) {
        setBounds(x - radius, y - radius, radius*2, radius*2);
        this.radius = radius;
    }

    public void displayResourcesView() {
        displayResources = !displayResources;
    }
}
