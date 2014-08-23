package net.mymilkedeek.ludum.listener;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import net.mymilkedeek.ludum.model.World;

/**
 * @author MyMilkedEek
 */
public class WorldInputListener extends InputListener {

    private final World world;

    public WorldInputListener(World world) {
        this.world = world;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        world.displayResourcesView();

        return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
    }
}