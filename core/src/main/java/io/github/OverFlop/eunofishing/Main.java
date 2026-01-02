package io.github.OverFlop.eunofishing;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    public static AssetManager assets = new AssetManager();
    
    private OrthogonalTiledMapRenderer mapRenderer;
    private ExtendViewport viewport;
    private OrthographicCamera camera;
    private Sprite player;
    private MapLayer collision;
    private Array<Rectangle> collisionRects;

    private static final int FRAME_COLS = 3, FRAME_ROWS = 4;

	// Objects used
	private Animation<TextureRegion> walkRightAnimation; // Must declare frame type (TextureRegion)
    private Animation<TextureRegion> walkLeftAnimation;
    private Animation<TextureRegion> walkUpAnimation;
    private Animation<TextureRegion> walkDownAnimation;
    private Animation<TextureRegion> idleAnimation;

    private float stateTime;
    private int direction;
    private boolean moving;

    

    @Override
    public void create() {
        batch = new SpriteBatch();
        assets.load("gone_fishin_by_memoraphile_CC0.mp3",Music.class);
        assets.load("beary_fishy_menu_screen_by_memoraphile_CC0.mp3",Music.class);
        assets.load("the_reel_winner_by_memoraphile_CC0.mp3",Sound.class);

        assets.load("Pepper.png",Texture.class);
        assets.load("rod_casting.png",Texture.class);
        assets.load("rod_fishing.png",Texture.class);
        assets.load("rod_standard.png",Texture.class);
        assets.load("fishing_bobbles.png",Texture.class);
        
        assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assets.load("Harbour.tmx", TiledMap.class);
        assets.finishLoading();

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(480, 270,camera);
        viewport.getCamera().position.set(480,270, 0);
        mapRenderer = new OrthogonalTiledMapRenderer(assets.get("Harbour.tmx", TiledMap.class));

        TiledMap map = assets.get("Harbour.tmx", TiledMap.class);
        float tileWidth = 16f;
        float tileHeight = 16f;
        collision = map.getLayers().get("Collision");
        collisionRects = new Array<>();
        for (MapObject object : collision.getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = new Rectangle(((RectangleMapObject) object).getRectangle());
                rect.x /= tileWidth;
                rect.y /= tileHeight;
                rect.width /= tileWidth;
                rect.height /= tileHeight;
                collisionRects.add(rect);
            }
        }

        
        player = new Sprite(assets.get(("Pepper.png"),Texture.class), 24,16);
        player.setPosition(480, 250);
        camera.position.set(player.getX() + player.getWidth() / 2f,player.getY() + player.getHeight() / 2f,0);
        camera.update();

        Texture playerTexture = player.getTexture();
        TextureRegion[][] tmp = TextureRegion.split(playerTexture,playerTexture.getWidth() / FRAME_COLS,playerTexture.getHeight() / FRAME_ROWS);
        TextureRegion[] leftWalkFrames = new TextureRegion[FRAME_COLS];
        TextureRegion[] rightWalkFrames = new TextureRegion[FRAME_COLS];
        TextureRegion[] downWalkFrames = new TextureRegion[FRAME_COLS];
        TextureRegion[] upWalkFrames = new TextureRegion[FRAME_COLS];
        TextureRegion[] idleWalkFrames = new TextureRegion[1];
        idleWalkFrames[0] = tmp[2][1];
        

        for (int j = 0; j < FRAME_COLS; j++) {
            leftWalkFrames[j] = tmp[3][j];
            rightWalkFrames[j] = tmp[1][j];
            downWalkFrames[j] = tmp[2][j];
            upWalkFrames[j] = tmp[0][j];
        }

        walkLeftAnimation = new Animation<TextureRegion>(0.2f, leftWalkFrames);
        walkUpAnimation = new Animation<TextureRegion>(0.2f, upWalkFrames);
        walkRightAnimation = new Animation<TextureRegion>(0.2f, rightWalkFrames);
        walkDownAnimation = new Animation<TextureRegion>(0.2f, downWalkFrames);
        idleAnimation = new Animation<TextureRegion>(0.2f, idleWalkFrames);

		stateTime = 0f;
        direction = 0;

        System.out.println("Player: x=" + player.getX() + " y=" + player.getY() + " w=" + player.getWidth() + " h=" + player.getHeight());
        System.out.println("Number of collision rects: " + collisionRects.size);
        for (Rectangle rect : collisionRects) {
            System.out.println("Collision: x=" + rect.x + " y=" + rect.y + " w=" + rect.width + " h=" + rect.height);
        }

    }

    @Override
    public void render() {
        draw();
        input();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height,true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        assets.dispose();
    }

    public boolean collides(Rectangle playerRect){
        playerRect.x /= 16f;
        playerRect.y /= 16f;
        playerRect.width /= 16f;
        playerRect.height /= 16f;
        System.out.println("Checking collision - Player tile pos: x=" + playerRect.x + " y=" + playerRect.y);
        for (Rectangle rect : collisionRects) {
            if (playerRect.overlaps(rect)) {
                System.out.println("COLLISION DETECTED!");
                System.out.println("COLLISION with rect: x=" + rect.x + " y=" + rect.y + " w=" + rect.width + " h=" + rect.height);
                return true;
            }
        }   
        return false;
    }

    private void input() {
        float speed = 100f;
        float delta = Gdx.graphics.getDeltaTime(); // retrieve the current delta
        float dx = speed * delta;
        float halfWidth = viewport.getWorldWidth() / 2f;
        float halfHeight = viewport.getWorldHeight() / 2f;
        float mapWidth  = 60 * 16f;
        float mapHeight = 34 * 16f;
        float playerWidth = player.getWidth();
        float playerHeight = player.getHeight();
        float pastX = player.getX();
        float pastY = player.getY();
        Rectangle playerRect = player.getBoundingRectangle();
        Rectangle nextRect;


        moving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)|| Gdx.input.isKeyPressed(Input.Keys.D)) {
            nextRect = new Rectangle(
            player.getX() + dx,
            player.getY(),
            player.getWidth(),
            player.getHeight()
            );
            

            if (!collides(nextRect)) {
                player.translateX(dx);
                direction = 1;
                moving = true;
            }
            else{
                player.setX(pastX);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)|| Gdx.input.isKeyPressed(Input.Keys.A)) {
            nextRect = new Rectangle(
            player.getX() - dx,
            player.getY(),
            player.getWidth(),
            player.getHeight()
            );
        
            if (!collides(nextRect)) {
                player.translateX(-dx);
                direction = 2;
                moving = true;
            }
            else{
                player.setX(pastX);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)|| Gdx.input.isKeyPressed(Input.Keys.W)) {
            nextRect = new Rectangle(
            player.getX(),
            player.getY() + dx,
            player.getWidth(),
            player.getHeight()
            );

            if (!collides(nextRect)) {
                player.translateY(dx);
                direction = 3;
                moving = true;
                }
            }
            else{
                player.setY(pastY);
            }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
                nextRect = new Rectangle(
                player.getX(),
                player.getY() - dx,
                player.getWidth(),
                player.getHeight()
            );

            if (!collides(nextRect)) {
                player.translateY(-dx);
                direction = 4;
                moving = true;
            }
            else{
                player.setY(pastY);
            }
        }

        if (player.getX() > mapWidth-playerWidth){player.setX(mapWidth - playerWidth);}
        if (player.getX() < 0){player.setX(0);}
        if (player.getY() > mapHeight-playerHeight){player.setY(mapHeight - playerHeight);}
        if (player.getY() < 0){ player.setY(0);}
        camera.position.set(player.getX(), player.getY(), 0);
        camera.position.x = MathUtils.clamp(camera.position.x, halfWidth, mapWidth - halfWidth);
        camera.position.y = MathUtils.clamp(camera.position.y, halfHeight, mapHeight - halfHeight);

    
    }

    private void logic() {
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

    }

    private void draw() {
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        float x = player.getX();
        float y = player.getY();

        if (moving) {
            stateTime += Gdx.graphics.getDeltaTime();
        }
        
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        camera.update();

        mapRenderer.setView(camera);
        mapRenderer.render();

        TextureRegion idleCurrentFrame = walkDownAnimation.getKeyFrame(stateTime, true);
        TextureRegion leftCurrentFrame = walkLeftAnimation.getKeyFrame(stateTime, true);
        TextureRegion rightCurrentFrame = walkRightAnimation.getKeyFrame(stateTime, true);
        TextureRegion downCurrentFrame = walkDownAnimation.getKeyFrame(stateTime, true);
        TextureRegion upCurrentFrame = walkUpAnimation.getKeyFrame(stateTime, true);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        switch(direction){
            case 0:
                batch.draw(idleCurrentFrame, x, y);
                break;
            case 1:
                batch.draw(rightCurrentFrame, x, y);
                break;
            case 2:
                batch.draw(leftCurrentFrame, x, y);
                break;
            case 3:
                batch.draw(upCurrentFrame, x, y);
                break;
            case 4:
                batch.draw(downCurrentFrame,x, y);
                break;
        }
        batch.end();
    }
}
