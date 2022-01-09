package ru.gb.star.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.gb.star.screen.utils.Assets;

public class Background {
    private class Star {
        Vector2 position;
        Vector2 velocity;
        float scale;

        public Star() {
            this.position = new Vector2(MathUtils.random(-200, Constants.width + 200),
                    MathUtils.random(-200, Constants.height + 200));
            this.velocity = new Vector2(MathUtils.random(-40, -5), 0);
            this.scale = Math.abs(velocity.x) / 40f * 0.8f;
        }

        public void update(float dt) {
            position.x += (velocity.x - gc.getHero().getLastDisplacement().x * 15) * dt;
            position.y += (velocity.y - gc.getHero().getLastDisplacement().y * 15) * dt;

            if (position.x < -200) {
                position.x = Constants.width + 200;
                position.y = MathUtils.random(-200, Constants.height + 200);
            }
        }
    }

    private final int STAR_COUNT = 1000;
    private GameController gc;
    private Texture textureCosmos;
    private TextureRegion textureStar;
    private Star[] stars;

    public Background(GameController gc) {
        this.gc = gc;
        this.textureCosmos = new Texture("images/space.png");
        this.textureStar = Assets.getInstance().getAtlas().findRegion("star16");
        this.stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(textureCosmos, 0, 0);
        for (int i = 0; i < stars.length; i++) {
            batch.draw(textureStar,
                stars[i].position.x - 8, stars[i].position.y - 8,
                8, 8,
                16, 16,
                stars[i].scale, stars[i].scale,
                0
            );

            if (MathUtils.random(0, 300) < 1) {
                batch.draw(textureStar,
                    stars[i].position.x - 8, stars[i].position.y - 8,
                    8, 8,
                    16, 16,
                    stars[i].scale * 2, stars[i].scale * 2,
                    0
                );
            }
        }
    }

    public void update(float dt) {
        for (int i = 0; i < stars.length; i++) {
            stars[i].update(dt);
        }
    }

    public void dispose() {
        textureCosmos.dispose();
    }
}
