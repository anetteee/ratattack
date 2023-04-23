package com.ratattack.game.view.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class DisposeHelper {

    public static void HelpTexture(Texture texture){
        if(texture != null){
            texture.dispose();
            texture = null;
        }
    }

    public static void HelpTextureRegionDrawable(TextureRegionDrawable textureRegionDrawable){
        if(textureRegionDrawable != null){
            textureRegionDrawable.getRegion().getTexture().dispose();
            textureRegionDrawable = null;
        }
    }

    public static void HelpImage(Image image){
        if(image != null){
            image.getStage().dispose();
            image = null;
        }
    }

    public static void HelpFont(BitmapFont bitmapFont){
        if(bitmapFont != null){
            bitmapFont.dispose();
            bitmapFont = null;
        }
    }

}
