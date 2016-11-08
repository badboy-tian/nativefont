package net.mwplay.nativefont;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

final public class MyWidget {
    public static void setTouchTrack(final Actor actor) {
        float x = 0;
        float y = 0;
        setTouchTrack(actor, new Vector2(x, y));
    }

    private static void setTouchTrack(final Actor actor, final Vector2 v) {
        final String name = actor.getName() == null ? "null" : actor.getName();
        actor.setTouchable(Touchable.enabled);
        // if (!Settings.TEST_MODE)
        // return;
        actor.clearListeners();
        actor.addListener(new InputListener() {
            float startx, starty;
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                startx = x;
                starty = y;
                Gdx.app.log(name, "down: {" + (actor.getX() - v.x /*- MyGame.worldWidth / 2*/) +
                        "," + (actor.getY() - v.y) + "}");

                actor.setDebug(true);
                return true;
            }

            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                actor.moveBy(x - startx, y - starty);
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(name, "up: { " + (actor.getX() - v.x /*- MyGame.worldWidth / 2*/) + "f,"
                        + (actor.getY() - v.y ) + "f }");

                Vector2 v = new Vector2(startx, starty);
                v.set(actor.localToParentCoordinates(v.cpy()));
                actor.setDebug(false);
            }
        });
    }
}