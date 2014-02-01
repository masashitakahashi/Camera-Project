package com.android.yuv;

import com.android.camerabutton2.ButtonActivity;

public class YUV {
	/* YUV�̒l��Ԃ� */
	public int[] YuvPix(){
		ButtonActivity set = new ButtonActivity();
		int width = set.widthSet(); //���Z�b�g
		int height = set.heightSet(); //�c�Z�b�g
		int[] r,g,b;  //�ԏ��A�Ώ��A���z��
		int[] y,u,v; //yuv
		
		int[] rgb = new int[width*height]; //rgb���
		int[] yuv = new int[width*height*3];; //yuv���
		
		rgb = set.rgbSet(); //rgb�Z�b�g
		
		r = new int[width*height]; //r
		g = new int[width*height]; //g
		b = new int[width*height]; //b

		y = new int[width*height]; //y
		u = new int[width*height]; //u
		v = new int[width*height]; //v
		
		//r,g,b�z��Ay,u,v�z��ɂ��ꂼ��i�[
		for(int i = 0;i < width*height;i++){
			
			//r,g,b���v�Z
			r[i] = (rgb[i] & 0x00ff0000) >> 16;
			g[i] = (rgb[i] & 0x0000ff00) >> 8;
			b[i] = (rgb[i] & 0x000000ff) >> 0;

			//y.u.v�ɕϊ�
			y[i] =  (int) (0.299*r[i] + 0.587*g[i] + 0.114*b[i]);
			u[i] = (int) (-0.169*r[i] - 0.331*g[i] + 0.500*b[i]);
			v[i] =  (int) (0.500*r[i] - 0.419*g[i] - 0.081*b[i]);
			}
		
		//yuv�z��ɏ��ԂɊi�[
		for(int i=0,j=0;i<width*height;i++,j=j+3){
			yuv[j]=y[i];
			yuv[j+1]=u[i];
			yuv[j+2]=v[i];
		}
		
		return yuv;
		}
}