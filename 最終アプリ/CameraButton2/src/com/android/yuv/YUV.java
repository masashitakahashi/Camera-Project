package com.android.yuv;

import com.android.camerabutton2.ButtonActivity;

public class YUV {
	/* YUVの値を返す */
	public int[] YuvPix(){
		ButtonActivity set = new ButtonActivity();
		int width = set.widthSet(); //横セット
		int height = set.heightSet(); //縦セット
		int[] r,g,b;  //赤情報、緑情報、青情報配列
		int[] y,u,v; //yuv
		
		int[] rgb = new int[width*height]; //rgb情報
		int[] yuv = new int[width*height*3];; //yuv情報
		
		rgb = set.rgbSet(); //rgbセット
		
		r = new int[width*height]; //r
		g = new int[width*height]; //g
		b = new int[width*height]; //b

		y = new int[width*height]; //y
		u = new int[width*height]; //u
		v = new int[width*height]; //v
		
		//r,g,b配列、y,u,v配列にそれぞれ格納
		for(int i = 0;i < width*height;i++){
			
			//r,g,bを計算
			r[i] = (rgb[i] & 0x00ff0000) >> 16;
			g[i] = (rgb[i] & 0x0000ff00) >> 8;
			b[i] = (rgb[i] & 0x000000ff) >> 0;

			//y.u.vに変換
			y[i] =  (int) (0.299*r[i] + 0.587*g[i] + 0.114*b[i]);
			u[i] = (int) (-0.169*r[i] - 0.331*g[i] + 0.500*b[i]);
			v[i] =  (int) (0.500*r[i] - 0.419*g[i] - 0.081*b[i]);
			}
		
		//yuv配列に順番に格納
		for(int i=0,j=0;i<width*height;i++,j=j+3){
			yuv[j]=y[i];
			yuv[j+1]=u[i];
			yuv[j+2]=v[i];
		}
		
		return yuv;
		}
}