package com.example.denesleal.precificacao;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;


public class CustomViewInicial extends View {
    private Bitmap figura;
    private AssetManager assets;
    private Rect srcBg;
    private Rect dstBg;


    public CustomViewInicial(Context ctx){
        super(ctx);

      try {
            assets = ctx.getAssets();
          srcBg = new Rect();
          dstBg = new Rect();
            figura = BitmapFactory.decodeStream(assets.openFd("dev_logo2.png").createInputStream());
        }
  catch(Exception ex){
            Log.d("CustomViewInicial", "Problema definição dos parâmetros");
        }
    }

    protected void onDraw(Canvas canvas){
        srcBg.top    = 0;
        srcBg.left   = 0;
        srcBg.right  = figura.getWidth();
        srcBg.bottom = figura.getHeight();
        dstBg.left   = 0;
        dstBg.top    = 0;
        dstBg.right  = Parameters.SCREEN_WIDTH;
        dstBg.bottom = Parameters.SCREEN_HEIGHT;

        canvas.drawBitmap(figura,srcBg, dstBg,null);

    }
   }







