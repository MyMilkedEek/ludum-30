package net.mymilkedeek.ludum.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import net.mymilkedeek.ludum.io.ImageDictionary;
import net.mymilkedeek.ludum.listener.WorldInputListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MyMilkedEek
 */
public class World extends Actor {

    private List<String> excesses;
    private List<String> bonuses;
    private String showResourcesString = "";
    private WorldInputListener inputListener;

    private List<World> connectedWorlds;

    private boolean displayResources;
    private float radius;
    private boolean needsClick;

    private List<String> goals;

    // todo improve rendering
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;


    public World(List<String> excesses, List<String> goals) {
        this.excesses = excesses;

        if (excesses == null) {
            excesses = new ArrayList<String>();
        }

        rebuildVisualString();

        // todo rendering
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        setColor(Color.BLUE);

        inputListener = new WorldInputListener(this);
        addListener(inputListener);

        displayResources = false;

        connectedWorlds = new ArrayList<World>();
        bonuses = new ArrayList<String>();

        this.goals = goals;
        if ( goals.contains("click")) {
            needsClick = true;
        }
    }

    public List<String> getExcesses() {
        return excesses;
    }

    public List<String> getBonuses() {
        return bonuses;
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
        shapeRenderer.circle(getX() + radius, getY() + radius, radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());

        for (World otherWorld : connectedWorlds) {
            shapeRenderer.line(getX() + radius, getY() + radius, otherWorld.getX() + otherWorld.radius, otherWorld.getY() + otherWorld.radius);
        }

        shapeRenderer.end();

        renderAvailableResources(batch, shapeRenderer);

        if (inputListener.isDragging()) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.line(getX() + radius, getY() + radius, inputListener.getLastDraggedPosition().x, inputListener.getLastDraggedPosition().y);
            shapeRenderer.end();
        }

        batch.begin();
    }

    private void renderAvailableResources(Batch batch, ShapeRenderer shapeRenderer) {
        BitmapFont.TextBounds bounds = font.getBounds(showResourcesString);

        /* draw excess */
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(getX() + radius, (float) (getY() + radius + (radius * 1.25)), ( excesses.size() * 32 ) + (excesses.size() * 3), 34);
        shapeRenderer.end();

        batch.begin();

        for ( String excess : excesses ) {
            Texture img = ImageDictionary.getImage(excess);
            if ( img != null ) {
                batch.draw(img, getX() + ( radius * ( excesses.indexOf(excess) + 1 )) , (float) (getY() + radius + (radius * 1.25)) + 1);
            }
        }

        batch.end();

        /* draw goals */
        if ( !(goals.size() == 1 && goals.get(0).equalsIgnoreCase("empty"))) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.ORANGE);
            shapeRenderer.rect(getX() + radius, (float) (getY() - (radius * 1.50)), (goals.size() * 32) + (excesses.size() * 3), 34);
            shapeRenderer.end();

            batch.begin();

            for (String goal : goals) {
                Texture img = ImageDictionary.getImage(goal);
                if (img != null) {
                    batch.draw(img, getX() + (radius * (goals.indexOf(goal) + 1)), (float) (getY() - (radius * 1.5)) - 1);
                }
            }

            batch.end();
        }
    }

    public void setLocation(float x, float y, float radius) {
        x = Gdx.graphics.getWidth() * x;
        y = Gdx.graphics.getHeight() * y;
        setBounds(x - radius, y - radius, radius * 2, radius * 2);
        this.radius = radius;
    }

    public void displayResourcesView() {
        displayResources = !displayResources;
    }

    public void connect(World otherworld) {
        if (!connectedWorlds.contains(otherworld) && !otherworld.connectedWorlds.contains(this)) {
            connectedWorlds.add(otherworld);

            for (String excess : excesses) {
                String addResource = ResourceProcessor.addResource(excess, otherworld);

                if (addResource != null) {
                    otherworld.getBonuses().add(addResource);
                    rebuildVisualString();
                    otherworld.rebuildVisualString();
                }
            }
        } else {
            boolean removed = connectedWorlds.remove(otherworld);

            if ( removed ) {
                for (String excess : excesses) {
                    if (ResourceProcessor.removeResource(excess, otherworld)) {
                        rebuildVisualString();
                    }
                }
            }
        }
    }

    public void rebuildVisualString() {
        showResourcesString = "";

        if ( goals == null ) {
            goals = new ArrayList<String>();
        }

        for ( String g : goals ) {
            if ( g.equalsIgnoreCase("empty")) {
                continue;
            }
            showResourcesString += "g" + g + " ";
        }

        for (String s : excesses) {
            showResourcesString += "+" + s + " ";
        }

        if (bonuses == null) {
            bonuses = new ArrayList<String>();
        }
        for (String b : bonuses) {
            showResourcesString += "*" + b + " ";
        }
    }

    public boolean planetCompleted() {
        if ( bonuses == null ) {
            bonuses = new ArrayList<String>();
        }

        if ( goals.size() == 1 && goals.get(0).equalsIgnoreCase("empty") ) {
            return true;
        }

        return bonuses.containsAll(goals);
    }

    public boolean needsClick() {
        return needsClick;
    }
}
