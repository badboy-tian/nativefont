package net.mwplay.nativefont;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

/**
 * Created by tian on 2016/10/2.
 */

public class NativeLabel extends Label {

    private float[] dxs = new float[]{1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 0.0f, 0.0f};
    private float[] dys = new float[]{1.0f, -1.0f, 1.0f, -1.0f, 0.0f, 0.0f, 1.0f, -1.0f};
    private boolean isStroke = false;
    private Color strokeColor;
    private float strokeWidth;


    public NativeLabel(CharSequence text, NativeFont font) {
        this(text, font, Color.WHITE);
        init();
    }

    public NativeLabel(CharSequence text, NativeFont font, Color color) {
        this(text, new LabelStyle(font, color));
        init();
    }

    public NativeLabel(CharSequence text, LabelStyle style) {
        super(append(text, style), style);
        setSize(getPrefWidth(), getPrefHeight());
        setColor(style.fontColor);
        init();
    }

    private static CharSequence append(CharSequence text, LabelStyle style) {
        ((NativeFont) style.font).appendText(text + "");
        return text;
    }

    @Override
    public void setText(final CharSequence newText) {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                NativeLabel.super.setText(append(newText, getStyle()));
            }
        });
    }

    public NativeLabel text(String text){
        setText(text);
        return this;
    }

    public NativeLabel color(String hex){
        setColor(Color.valueOf(hex));
        return this;
    }

    public NativeLabel color(Color color){
        setColor(color);

        return this;
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        this.strokeColor = color.cpy();
    }

    public void setBold(float width) {
        setStroke(getColor().cpy(), width);
    }

    public void setStroke(Color strokeColor, float strokeWidth) {
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.isStroke = true;
    }

    public TextureRegion getRegion() {
        return getBitmapFontCache().getFont().getRegion();
    }

    @Override
    public void setFontScale(float fontScale) {
        super.setFontScale(fontScale);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        box.set(getX(), getY(), getWidth(), getHeight());

        if (this.isStroke) {
            validate();
            for (int i = 0; i < this.dxs.length; i++) {
                getBitmapFontCache().tint(this.strokeColor);
                getBitmapFontCache().setPosition(getX() + (this.dxs[i] * this.strokeWidth), (getY() + (this.dys[i] * this.strokeWidth)) + this.strokeWidth);
                getBitmapFontCache().draw(batch, getColor().a);
            }
            getBitmapFontCache().tint(getColor());
            getBitmapFontCache().setPosition(getX(), getY() + this.strokeWidth);
            getBitmapFontCache().draw(batch);
            return;
        }

        super.draw(batch, parentAlpha);
    }

    public NativeLabel toCenterOf(Actor actor) {
        setPosition(actor.getX() + actor.getWidth() / 2f - getWidth() / 2f,
                actor.getY() + actor.getHeight() / 2f - getHeight() / 2f);

        return this;
    }

    public NativeLabel width(float width){
        setWidth(width);
        return this;
    }

    public NativeLabel height(float height){
        setHeight(height);
        return this;
    }

    public float width(){
        return getWidth();
    }

    public float height(){
        return getHeight();
    }

    public NativeLabel disableTouch(){
        setTouchable(Touchable.disabled);
        return this;
    }

    public NativeLabel enableTouch(){
        setTouchable(Touchable.enabled);
        return this;
    }

    public NativeLabel toCenterXOf(Actor actor) {
        setX(actor.getX() + actor.getWidth() / 2f - getWidth() / 2f);
        return this;
    }

    public NativeLabel toStageCenter(Stage stage) {
        posCenter(stage.getWidth() / 2f, stage.getHeight() / 2f);
        return this;
    }

    public NativeLabel toStageXCenter(Stage stage) {
        x(stage.getWidth() / 2f - getWidth() / 2f);
        return this;
    }

    public NativeLabel toStageYCenter(Stage stage) {
        y(stage.getHeight() / 2f - getHeight() / 2f);
        return this;
    }

    public NativeLabel toLeftOf(Actor actor) {
        setPosition(actor.getX() - getWidth(), actor.getY());
        return this;
    }

    public NativeLabel toLeftOf(Actor actor, int align) {
        setPosition(actor.getX() - getWidth(), actor.getY(), align);
        return this;
    }

    public NativeLabel toRightOf(Actor actor) {
        setPosition(actor.getRight(), actor.getY());
        return this;
    }

    public NativeLabel toTopOf(Actor actor) {
        setPosition(actor.getX(), actor.getTop());
        return this;
    }

    public NativeLabel toBottomOf(Actor actor) {
        setPosition(actor.getX(), actor.getY() - getHeight());
        return this;
    }

    public NativeLabel toLeftTopOf(Actor actor) {
        setPosition(actor.getX() - getWidth(), actor.getTop());
        return this;
    }

    public NativeLabel toLeftBottomOf(Actor actor) {
        setPosition(actor.getX(), actor.getY());
        return this;
    }

    public NativeLabel addTo(Stage stage) {
        stage.addActor(this);
        return this;
    }

    public NativeLabel addTo(Group group) {
        group.addActor(this);
        return this;
    }

    public NativeLabel add(Group group) {
        group.addActor(this);
        return this;
    }

    public NativeLabel pos(Vector2 pos) {
        setPosition(pos.x, pos.y);

        return this;
    }

    public NativeLabel pos(Actor actor) {
        setPosition(actor.getX(), actor.getY());

        return this;
    }

    public NativeLabel pos(float x, float y) {
        setPosition(x, y);

        return this;
    }

    public NativeLabel pos(float x, float y, int align) {
        setPosition(x, y, align);

        return this;
    }

    /**
     * 代表可以拖动,方便调试
     * @return
     */
    public NativeLabel drag(){
        MyWidget.setTouchTrack(this);
        return this;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);

        box.setX(x);
        box.setY(y);
    }

    public NativeLabel scale(float scale) {
        setSize(getWidth() * scale, getHeight() * scale);
        origonCenter();
        return this;
    }

    public NativeLabel scale(float scale, int align) {
        setSize(getWidth() * scale, getHeight() * scale);
        //setAlign(align);
        return this;
    }

    public NativeLabel scale(float scaleX, float scaleY) {
        setSize(getWidth() * scaleX, getHeight() * scaleY);
        return this;
    }

    public NativeLabel scaleX(float scaleX) {
        setWidth(getWidth() * scaleX);
        return this;
    }

    public NativeLabel scaleY(float scaleY) {
        setHeight(getHeight() * scaleY);
        return this;
    }

    public NativeLabel origon(int align) {
        setOrigin(align);
        return this;
    }

    public NativeLabel origon(Actor actor) {
        setOrigin(actor.getOriginX(), actor.getOriginY());
        return this;
    }

    public NativeLabel origonCenter() {
        setOrigin(Align.center);
        return this;
    }

    public NativeLabel origon(int alignX, int alignY) {
        setOrigin(alignX, alignY);
        return this;
    }

    public NativeLabel posCenter(float x, float y) {
        setPosition(x - getWidth() / 2f, y - getHeight() / 2f);

        return this;
    }

    public NativeLabel posCenter(Vector2 pos) {
        setPosition(pos.x - getWidth() / 2f, pos.y - getHeight() / 2f);

        return this;
    }

    public NativeLabel offsetX(float x) {
        setX(getX() + x);
        return this;
    }

    public NativeLabel offsetY(float y) {
        setY(getY() + y);
        return this;
    }

    public NativeLabel size(float w, float h) {
        setSize(w, h);
        return this;
    }

    public NativeLabel size(Actor actor) {
        setSize(actor.getWidth(), actor.getHeight());
        return this;
    }

    /**
     * @param onClickListener
     * @return
     */
    public NativeLabel listener(EventListener onClickListener) {
        addListener(onClickListener);
        return this;
    }

    public NativeLabel isButton(final TClickListener clickListener) {
        origonCenter();
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                addAction(
                        Actions.sequence(
                                Actions.scaleTo(0.9f, 0.9f, 0.1f),
                                Actions.scaleTo(1, 1, 0.1f), Actions.run(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (clickListener != null)
                                            clickListener.onClicked(NativeLabel.this);
                                    }
                                })));
            }
        });

        return this;
    }

    public NativeLabel isColorButton(final TClickListener clickListener){
        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                event.getTarget().setColor(0.5f, 0.5f, 0.5f, 1f);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                event.getTarget().setColor(Color.WHITE);
                if (clickListener != null){
                    clickListener.onClicked(NativeLabel.this);
                }
            }
        });



        return this;
    }

    public float centerX() {
        return getX() + getWidth() / 2f;
    }

    public float centerY() {
        return getY() + getHeight() / 2f;
    }

    /**
     * 在actor的内部的右边
     *
     * @param actor
     * @return
     */
    public NativeLabel inRightOf(Actor actor) {
        setPosition(actor.getWidth() - getWidth(), 0);
        return this;
    }

    public NativeLabel inLeftOf(Actor actor) {
        setPosition(0, 0);
        return this;
    }

    public NativeLabel inTopOf(Actor actor) {
        setY(actor.getHeight() - getHeight());
        return this;
    }

    public NativeLabel inCenterXOf(Actor container) {
        setX(container.getWidth() / 2f - getWidth() / 2f);
        return this;
    }

    public NativeLabel x(float x) {
        setX(x);
        return this;
    }

    public NativeLabel y(float y) {
        setY(y);

        return this;
    }

    public NativeLabel name(Object name) {
        setName(name.toString());
        return this;
    }

    public String name() {
        return getName();
    }

    public NativeLabel replace(Actor actor) {
        addTo(actor.getParent());
        size(actor);
        pos(actor);
        origon(actor);
        setZIndex(actor.getZIndex());

        return this;
    }

    public NativeLabel debug() {
        super.debug();
        return this;
    }

    public Vector2 getPos() {
        return new Vector2(getX(), getY());
    }

    public Vector2 getCenterPos() {
        return new Vector2(getX() + getWidth() / 2f, getY() + getHeight() / 2f);
    }

    public NativeLabel hide() {
        if (isVisible())
            setVisible(false);
        return this;
    }

    public NativeLabel visiable() {
        if (!isVisible())
            setVisible(true);
        return this;
    }

    public Vector2 size() {
        return new Vector2(getWidth(), getHeight());
    }

    public NativeLabel front() {
        toFront();
        return this;
    }

    public Rectangle copyBox() {
        return new Rectangle(box);
    }

    public Rectangle makeLeftUp(float width) {
        return new Rectangle(getX() + 5, getTop() - width, width, width);
    }

    public Rectangle copyBox(float scale) {
        Rectangle rectangle = new Rectangle(box);
        rectangle.setSize(rectangle.getWidth() / 2f, rectangle.getHeight() / 2f);
        return rectangle;
    }

    public NativeLabel roateAction(float angle, float duration, boolean forever) {
        if (forever) {
            addAction(Actions.forever(Actions.rotateBy(angle, duration)));
        } else {
            addAction(Actions.rotateBy(angle, duration));
        }

        return this;
    }

    /**
     * 水平中线对齐
     * @param actor
     */
    public NativeLabel alignCenter(Actor actor) {
        setY(actor.getY() + actor.getHeight() / 2f - getHeight() / 2f);

        return this;
    }


    /**
     * 创建人：Administrator
     * 邮箱：tqj.zyy@gmail.com
     * 创建时间：2016/6/27 16:46
     * 修改人：Administrator
     * 修改时间：2016/6/27 16:46
     * 修改备注：
     */
    public interface TClickListener {
        public void onClicked(NativeLabel image);
    }

    public Rectangle box = new Rectangle();

    protected void init() {
        box.setSize(getWidth(), getHeight());
    }
}
