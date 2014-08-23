package net.mymilkedeek.ludum.listener;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.Array;
import net.mymilkedeek.ludum.model.World;

/**
 * @author MyMilkedEek
 */
public class WorldInputListener extends InputListener {

    private final World world;
    private boolean dragging = false;
    private Vector2 lastDraggedPosition;

    public WorldInputListener(World world) {
        this.world = world;
        lastDraggedPosition = new Vector2();
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        world.displayResourcesView();
        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        super.touchDragged(event, x, y, pointer);

        if ( !dragging ) {
            dragging = true;
        }

        x += world.getX();
        y += world.getY();

        lastDraggedPosition.x = x;
        lastDraggedPosition.y = y;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);

        Stage stage = event.getStage();
        Array<Actor> actors = stage.getActors();

        x += world.getX();
        y += world.getY();

        for ( Actor actor : actors ) {
            if ( actor != world ) {
                if ( x > actor.getX() && x < actor.getX() + actor.getWidth() ) {
                    if ( y > actor.getY() && y < actor.getY() + actor.getHeight() ) {
                        world.connect((World)actor);
                    }
                }
            }
        }

        dragging = false;
    }

    public boolean isDragging() {
        return dragging;
    }

    public Vector2 getLastDraggedPosition() {
        return lastDraggedPosition;
    }
}