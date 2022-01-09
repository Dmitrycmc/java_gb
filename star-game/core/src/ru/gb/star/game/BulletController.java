package ru.gb.star.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.gb.star.pool.Pool;
import ru.gb.star.screen.utils.Assets;

public class BulletController extends Pool<Bullet> {
    TextureRegion bulletTexture = Assets.getInstance().getAtlas().findRegion("bullet");

    @Override
    protected Bullet newObject() {
        return new Bullet(this);
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Bullet b = activeList.get(i);

            batch.draw(bulletTexture,
                b.getPos().x - Bullet.RADIUS, b.getPos().y - Bullet.RADIUS,
                Bullet.RADIUS, Bullet.RADIUS,
                2 * Bullet.RADIUS, 2 * Bullet.RADIUS,
                2, 2,
                0f
            );
        }
    }

    private float t = 0;
    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
    }

    public void spawn(float x, float y, float vx, float vy){
        getInactiveElement().activate(x, y, vx, vy);
    }

    public void dispose() {

    }
}
