package net.mymilkedeek.ludum.listener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.Array;
import net.mymilkedeek.ludum.model.World;

/**
 * InputListener for dragging events on world instances.
 * Keeps track of the current position of the dragging
 * finger to draw the line and connects worlds if necessary.
 *
 * @author MyMilkedEek
 */
public class WorldInputListener extends InputListener {

    /**
     * The world instance this listener is monitoring.
     */
    private final transient World world;

    /**
     * Flag to indicate if the user is dragging his finger
     * starting from this world.
     */
    private transient boolean dragging;

    /**
     * The last known position of the dragging finger.
     */
    private final transient Vector2 lastDraggedPosition;

    /**
     * Default constructor for the listener.
     * Sets the wold it is going to monitor.
     *
     * @param world owner
     */
    public WorldInputListener(World world) {
        super();
        this.world = world;
        this.lastDraggedPosition = new Vector2();
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (this.world.needsClick()) {
            this.world.getBonuses().add("click");
        }
        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        super.touchDragged(event, x, y, pointer);

        if (! this.dragging) {
            this.dragging = true;
        }

        x += this.world.getX();
        y += this.world.getY();

        this.lastDraggedPosition.x = x;
        this.lastDraggedPosition.y = y;
    }

    @Override
    public void touchUp(final InputEvent event, final float x, final float y, final int pointer, final int button) {
        super.touchUp(event, x, y, pointer, button);

        final Stage stage = event.getStage();
        final Array<Actor> actors = stage.getActors();

        final float localX = x + this.world.getX();
        final float localY = y + this.world.getY();

        for (final Actor actor : actors) {
            if (actor != this.world && actor instanceof World
                    && localX > actor.getX() && localX < actor.getX() + actor.getWidth()
                    && localY > actor.getY() && localY < actor.getY() + actor.getHeight()) {
                this.world.connect((World) actor);

            }
        }

        this.dragging = false;
    }

    /**
     * Is the user dragging a line?
     *
     * @return dragging
     */
    public boolean isDragging() {
        return this.dragging;
    }

    /**
     * Returns the last known dragging position.
     *
     * @return Vector2
     */
    public Vector2 getLastDraggedPosition() {
        return this.lastDraggedPosition;
    }
}
