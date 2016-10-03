package net.mwplay.nativefont.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import net.mwplay.nativefont.NativeLabel;

/**
 * Created by tian on 2016/10/2.
 */

public class FontTest extends ScreenAdapter{
    Stage stage;
    NativeLabel label;
    FrameBuffer frameBuffer;
    net.mwplay.nativefont.NativeGame game;

    public FontTest(net.mwplay.nativefont.NativeGame game) {
        stage = new Stage(new StretchViewport(640, 960));
        this.game = game;
    }

    @Override
    public void show() {
        super.show();

        Image image = new Image(new Texture("1.png"));

        Vector2 base = new Vector2(100, 800);
        addLabel("Hello", base.cpy());
        addLabel("안녕하세요", base.cpy().sub(0, 40));
        addLabel("こんにちは", base.cpy().sub(0, 80));
        addLabel("привет", base.cpy().sub(0, 120));
        addLabel("你好", base.cpy().sub(0, 160));
        addLabel("Përshëndetje", base.cpy().sub(0, 200));
        addLabel("¡Hola", base.cpy().sub(0, 240));

        Vector2 base1 = new Vector2(400, 800);
        addLabel("Hello", base1.cpy(), Color.BROWN);
        addLabel("안녕하세요", base1.cpy().sub(0, 40), Color.BLUE);
        addLabel("こんにちは", base1.cpy().sub(0, 80), Color.GREEN);
        addLabel("привет", base1.cpy().sub(0, 120), Color.RED);
        addLabel("你好", base1.cpy().sub(0, 160), Color.RED);
        addLabel("Përshëndetje", base1.cpy().sub(0, 200), Color.RED);
        addLabel("¡Hola", base1.cpy().sub(0, 240), Color.RED);

        base1.set(100, 400);
        addLabel50("Hello", base1.cpy(), Color.BROWN);
        addLabel50("안녕하세요", base1.cpy().sub(0, 40), Color.BLUE);
        addLabel50("こんにちは", base1.cpy().sub(0, 100), Color.GREEN);
        addLabel50("привет", base1.cpy().sub(0, 160), Color.RED);
        addLabel50("你好", base1.cpy().sub(0, 220), Color.RED);
        addLabel50("Përshëndetje", base1.cpy().sub(0, 280), Color.RED);
        addLabel50("¡Hola", base1.cpy().sub(0, 340), Color.RED);
    }

    private void addLabel(String text, Vector2 pos){
        label = new NativeLabel("", new Label.LabelStyle(game.fonts.get("font"), Color.BLACK));
        label.setText(text);
        label.setPosition(pos.x, pos.y);
        stage.addActor(label);
    }

    private void addLabel(String text, Vector2 pos, Color strokeColor){
        label = new NativeLabel("", new Label.LabelStyle(game.fonts.get("font"), Color.BLACK));
        label.setText(text);
        label.setPosition(pos.x, pos.y);
        label.setStroke(strokeColor, 1);
        stage.addActor(label);
    }

    private void addLabel50(String text, Vector2 pos, Color strokeColor){
        label = new NativeLabel("", new Label.LabelStyle(game.fonts.get("font50"), Color.BLACK));
        label.setText(text);
        label.setPosition(pos.x, pos.y);
        label.setStroke(strokeColor, 1);
        stage.addActor(label);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height);
    }
}
