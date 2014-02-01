package com.android.camerabutton2;

import com.android.yuv.YUV;

public class Jpeg{
	/* RGBの値を返す */
	public int[] Imageset() {
		ButtonActivity set = new ButtonActivity();
		int width = set.widthSet(); //横セット
		int height = set.heightSet(); //縦セット
		
		int[] y,u,v; //y,u,v
		int[] r,g,b; //r,g,b
		int[] rgb = new int[width*height]; //rgb
		int[] yuv = new int[width*height*3]; //yuv
		
		y = new int[width*height]; //y
		u = new int[width*height]; //u
		v = new int[width*height]; //v
		
		YUV yu = new YUV();
        yuv = yu.YuvPix(); //yuvセット
        
		r = new int[width*height]; //r
		g = new int[width*height]; //g
		b = new int[width*height]; //b
        
		//yuvをy,u,vそれぞれに
        for(int i = 0,j = 0;i<width*height;i++,j = j+3){
        	y[i] = yuv[j];
        	u[i] = yuv[j+1];
        	v[i] = yuv[j+2];
        }
        
        //y,u,vからr,g,bに
        for(int i = 0;i<width*height;i++){
        	r[i] = (int) (1.000*y[i] + 1.402*v[i]);
        	g[i] = (int) (1.000*y[i] - 0.344*u[i] - 0.714*v[i]);
        	b[i] = (int) (1.000*y[i] + 1.772*u[i]);
        	
        	//1つにまとめる
        	rgb[i] = 0xFF000000 + (r[i] << 16) + (g[i] << 8) + b[i];
        }

        return rgb;
        }
}