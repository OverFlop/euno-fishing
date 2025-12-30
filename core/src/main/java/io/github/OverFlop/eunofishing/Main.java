package io.github.OverFlop.eunofishing;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    public static AssetManager assets = new AssetManager();
    TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private ExtendViewport viewport;
    private OrthographicCamera camera;
    

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

        TiledMap map = assets.get("Harbour.tmx", TiledMap.class);

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(480, 270,camera);
        viewport.getCamera().position.set(480, 270, 0);
        mapRenderer = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void render() {
        //if (assets.update()) { 
        //}
        draw();
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

    private void input() {

    }

    private void logic() {

    }

    private void draw() {
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        camera.update();

        mapRenderer.setView(camera);
        mapRenderer.render();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        //batch.draw(assets.get("Pepper.png",Texture.class),0,0,180,128);
        batch.end();
    }
}
