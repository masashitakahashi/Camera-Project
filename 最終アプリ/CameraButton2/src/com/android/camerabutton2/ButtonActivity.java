package com.android.camerabutton2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.app.Activity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ButtonActivity extends Activity implements OnClickListener {
	private Camera camera; //カメラ
	private SurfaceView mySurfaceView; //カメラ用サーフェイスビュー
	static Bitmap bitmap; //読み込む画像
	static Bitmap b; //表示する画像
	
    /* カメラ用サーフェイスのイベント処理 */
    private SurfaceHolder.Callback mSurfaceListener = new SurfaceHolder.Callback() {
    	//カメラのプレビューを表示するSurfaceViewのリスナー
    	
    	@Override
    	public void surfaceDestroyed(SurfaceHolder holder) {
    		//カメラを停止する
    		camera.stopPreview();
            camera.release();
            camera = null;
            }
    	
    	@Override
        public void surfaceCreated(SurfaceHolder holder) {
    		//カメラを起動する
            camera = Camera.open();
            try {
            	camera.setPreviewDisplay(holder);
            	} catch (Exception e) {
            		e.printStackTrace();
            		}
            }
    	
    	@Override
    	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    		//SurfaceViewのサイズなどが変更されたときに呼び出されるメソッド。
            camera.stopPreview();
                
            Camera.Parameters parameters = camera.getParameters();
                
            // パラメータを設定してカメラを再開
            camera.setParameters(parameters);
            camera.startPreview();
            }
    	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
    	setContentView(R.layout.activity_button);

    	//カメラプレビューの設定
    	mySurfaceView = (SurfaceView)findViewById(R.id.surface_view);
    	SurfaceHolder holder = mySurfaceView.getHolder();
    	holder.addCallback(mSurfaceListener);
        
    	/**それぞれのidを設定**/
     	Button button1=(Button)findViewById(R.id.button1);
     	Button button2=(Button)findViewById(R.id.button2);
      	/**ボタンが押されたらonClickが動作するよう設定**/
      	button1.setOnClickListener(this);
     	button2.setOnClickListener(this);
     	
     	//画像をリソースから読み込む
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.yoyogi);
        b = bitmap.copy(Bitmap.Config.ARGB_4444, true);
        
        int width = widthSet(); //横セット
    	int height = heightSet(); //縦セット
        
        int[] rgb = new int[width*height]; //変換してきたrgb
        Jpeg jp = new Jpeg();
        rgb = jp.Imageset(); //変換してきたrgbセット

     	ImageView imageView = (ImageView)findViewById(R.id.image_view); //ImageView指定
     	
        //bにセット
     	b.setPixels(rgb, 0, width, 0, 0, width, height);
     	
     	// Bitmapを設定
     	imageView.setImageBitmap(b);
     	}
            
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.button1:
			showMessage("左側で表示します");
			break;
     	case R.id.button2:
            showMessage("右側で表示します");
            break;
            }
		}
	
	/**トースト設定**/
    protected void showMessage(String msg){
    	Toast.makeText(
    			this,
    			msg, Toast.LENGTH_LONG).show();
    	}
    
    //横幅を得る
    public int widthSet(){
    	return bitmap.getWidth();
    	}
    //縦幅を得る
    public int heightSet(){
    	return bitmap.getHeight();
    	}
    
    public int[] rgbSet(){
    	int width = widthSet(); //横セット
    	int height = heightSet(); //縦セット
    	
    	int[] rgb = new int[width*height];
		//ピクセルゲット
    	bitmap.getPixels(rgb, 0, width, 0, 0, width, height);
    	return rgb;
    	}
}