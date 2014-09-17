package net.mymilkedeek.ludum.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import net.mymilkedeek.ludum.io.ImageDictionary;
import net.mymilkedeek.ludum.listener.WorldInputListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a world object.
 *
 * @author MyMilkedEek
 */
public class World extends Actor {

    /**
     * Empty identifier.
     */
    private final String EMPTY = "empty";

    /**
     * List of excesses, which can be transported to another world.
     */
    private final List<String> excesses;

    /**
     * List of bonuses, which are received when combining goods.
     */
    private List<String> bonuses;

    /**
     * The input listener attached to this instance.
     */
    private final WorldInputListener inputListener;

    /**
     * The graphic of this world.
     */
    private Texture worldImg;

    /**
     * List of worlds that this world connects to.
     */
    private final List<World> connectedWorlds;

    /**
     * Radius of this world.
     */
    private float radius;

    /**
     * Is clicking this world one of its goals?
     */
    private boolean needsClick;

    /**
     * List of goals.
     */
    private List<String> goals;

    /**
     * Debug renderer.
     */
    private final ShapeRenderer shapeRenderer;

    /**
     * Default constructor.
     *
     * Initializes the world with its excesses and goals.
     *
     * @param excesses list of excesses
     * @param goals list of goals
     */
    public World(List<String> excesses, List<String> goals) {
        super();

        if (excesses == null) {
            excesses = new ArrayList<String>();
        }

        this.excesses = excesses;

        this.shapeRenderer = new ShapeRenderer();
        setColor(Color.BLUE);

        this.inputListener = new WorldInputListener(this);
        addListener(this.inputListener);

        this.connectedWorlds = new ArrayList<World>();
        this.bonuses = new ArrayList<String>();

        this.goals = goals;
        if ( goals.contains("click")) {
            this.needsClick = true;
        }
    }

    /**
     * Setter for the world graphic.
     * @param worldImg Texture
     */
    public final void setWorldImg(final Texture worldImg) {
        this.worldImg = worldImg;
    }

    /**
     * Getter for the excesses
     * @return List of excesses
     */
    public final List<String> getExcesses() {
        return this.excesses;
    }

    /**
     * Getter for the bonuses.
     * @return List of bonuses
     */
    public final List<String> getBonuses() {
        return this.bonuses;
    }

    @Override
    public final void draw(final Batch batch, final float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(this.worldImg, getX(), getY());
        batch.end();

        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        for (final World otherWorld : this.connectedWorlds) {
            this.shapeRenderer.line(getX() + this.radius, getY() + this.radius, otherWorld.getX() + otherWorld.radius, otherWorld.getY() + otherWorld.radius);
        }

        this.shapeRenderer.end();

        renderAvailableResources(batch, this.shapeRenderer);

        if (this.inputListener.isDragging()) {
            this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            this.shapeRenderer.setColor(Color.WHITE);
            this.shapeRenderer.line(getX() + this.radius, getY() + this.radius, this.inputListener.getLastDraggedPosition().x, this.inputListener.getLastDraggedPosition().y);
            this.shapeRenderer.end();
        }

        batch.begin();
    }

    /**
     * Helper method to draw this planet's available resources.
     * @param batch Batch to draw things
     * @param shapeRenderer Renderer to draw the boxes
     */
    private void renderAvailableResources(final Batch batch, final ShapeRenderer shapeRenderer) {
        /* draw excess */
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(getX() + this.radius, (float) (getY() + this.radius + (this.radius * 1.25)), ( this.excesses.size() * 32 ) + (this.excesses.size() * 3), 34);
        shapeRenderer.end();

        batch.begin();

        for ( final String excess : this.excesses ) {
            final Texture img = ImageDictionary.getImage(excess);
            if ( img != null ) {
                batch.draw(img, getX() + ( this.radius * ( this.excesses.indexOf(excess) + 1 )) , (float) (getY() + this.radius + (this.radius * 1.25)) + 1);
            }
        }

        batch.end();

        /* draw goals */
        if ( !(this.goals.size() == 1 && this.goals.get(0).equalsIgnoreCase(EMPTY))) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.ORANGE);
            shapeRenderer.rect(getX() + this.radius, (float) (getY() - (this.radius * 1.50)), (this.goals.size() * 32) + (this.goals.size() * 3), 34);
            shapeRenderer.end();

            batch.begin();

            for (final String goal : this.goals) {
                final Texture img = ImageDictionary.getImage(goal);
                if (img != null) {
                    batch.draw(img, getX() + (this.radius * (this.goals.indexOf(goal) + 1)), (float) (getY() - (this.radius * 1.5)) - 1);
                }
            }

            batch.end();
        }

        /* draw boni */

        if ( !(this.bonuses.size() == 1 && this.bonuses.get(0).equalsIgnoreCase(EMPTY))) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.OLIVE);
            shapeRenderer.rect(getX() + this.radius * 2, (float) (getY() + (this.radius * 0.33)), (this.bonuses.size() * 32) + (this.bonuses.size() * 3), 34);
            shapeRenderer.end();

            batch.begin();

            for (final String bonus : this.bonuses) {
                final Texture img = ImageDictionary.getImage(bonus);
                if (img != null) {
                    batch.draw(img, getX() + this.radius * 2 + (32 * (this.bonuses.indexOf(bonus))), (float) (getY() + (this.radius * 0.33)) - 1);
                }
            }

            batch.end();
        }
    }

    /**
     * Setter for the planets location
     * @param x coordinate
     * @param y coordinate
     * @param radius radius of the planet
     */
    public void setLocation(final float x, final float y, final float radius) {
        final float localX = Gdx.graphics.getWidth() * x;
        final float localY = Gdx.graphics.getHeight() * y;
        setBounds(localX - radius, localY - radius, radius * 2, radius * 2);
        this.radius = radius;
    }

    /**
     * Checks if a planet connects to another and connects them.
     * @param otherWorld World to connect to.
     */
    public void connect(final World otherWorld) {
        if (!this.connectedWorlds.contains(otherWorld) && !otherWorld.connectedWorlds.contains(this)) {
            this.connectedWorlds.add(otherWorld);

            for (final String excess : this.excesses) {
                final String addResource = ResourceProcessor.addResource(excess, otherWorld);

                if (addResource != null) {
                    otherWorld.getBonuses().add(addResource);
                }
            }

            for ( final String bonus : this.bonuses ) {
                String addResource = ResourceProcessor.addResource(bonus, otherWorld);

                if (addResource != null) {
                    if (!bonus.equalsIgnoreCase(addResource)) {
                        otherWorld.getBonuses().add(bonus);
                    }
                    otherWorld.getBonuses().add(addResource);

                    addResource = ResourceProcessor.addResource(bonus, otherWorld);

                    if (addResource != null && !otherWorld.getBonuses().contains(addResource)) {
                        otherWorld.getBonuses().add(addResource);
                    }
                }
            }
        }
    }

    /**
     * Checks if this planet's goals have been reached
     * @return completed
     */
    public boolean planetCompleted() {
        if ( this.bonuses == null ) {
            this.bonuses = new ArrayList<String>();
        }

        if ( this.goals.size() == 1 && this.goals.get(0).equalsIgnoreCase(EMPTY) ) {
            return true;
        }

        return this.bonuses.containsAll(this.goals);
    }

    /**
     * Getter for the click flag.
     * @return boolean
     */
    public boolean needsClick() {
        return this.needsClick;
    }
}
