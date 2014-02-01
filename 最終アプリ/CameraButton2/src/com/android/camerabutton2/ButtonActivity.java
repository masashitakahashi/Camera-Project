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
	private Camera camera; //�J����
	private SurfaceView mySurfaceView; //�J�����p�T�[�t�F�C�X�r���[
	static Bitmap bitmap; //�ǂݍ��މ摜
	static Bitmap b; //�\������摜
	
    /* �J�����p�T�[�t�F�C�X�̃C�x���g���� */
    private SurfaceHolder.Callback mSurfaceListener = new SurfaceHolder.Callback() {
    	//�J�����̃v���r���[��\������SurfaceView�̃��X�i�[
    	
    	@Override
    	public void surfaceDestroyed(SurfaceHolder holder) {
    		//�J�������~����
    		camera.stopPreview();
            camera.release();
            camera = null;
            }
    	
    	@Override
        public void surfaceCreated(SurfaceHolder holder) {
    		//�J�������N������
            camera = Camera.open();
            try {
            	camera.setPreviewDisplay(holder);
            	} catch (Exception e) {
            		e.printStackTrace();
            		}
            }
    	
    	@Override
    	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    		//SurfaceView�̃T�C�Y�Ȃǂ��ύX���ꂽ�Ƃ��ɌĂяo����郁�\�b�h�B
            camera.stopPreview();
                
            Camera.Parameters parameters = camera.getParameters();
                
            // �p�����[�^��ݒ肵�ăJ�������ĊJ
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

    	//�J�����v���r���[�̐ݒ�
    	mySurfaceView = (SurfaceView)findViewById(R.id.surface_view);
    	SurfaceHolder holder = mySurfaceView.getHolder();
    	holder.addCallback(mSurfaceListener);
        
    	/**���ꂼ���id��ݒ�**/
     	Button button1=(Button)findViewById(R.id.button1);
     	Button button2=(Button)findViewById(R.id.button2);
      	/**�{�^���������ꂽ��onClick�����삷��悤�ݒ�**/
      	button1.setOnClickListener(this);
     	button2.setOnClickListener(this);
     	
     	//�摜�����\�[�X����ǂݍ���
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.yoyogi);
        b = bitmap.copy(Bitmap.Config.ARGB_4444, true);
        
        int width = widthSet(); //���Z�b�g
    	int height = heightSet(); //�c�Z�b�g
        
        int[] rgb = new int[width*height]; //�ϊ����Ă���rgb
        Jpeg jp = new Jpeg();
        rgb = jp.Imageset(); //�ϊ����Ă���rgb�Z�b�g

     	ImageView imageView = (ImageView)findViewById(R.id.image_view); //ImageView�w��
     	
        //b�ɃZ�b�g
     	b.setPixels(rgb, 0, width, 0, 0, width, height);
     	
     	// Bitmap��ݒ�
     	imageView.setImageBitmap(b);
     	}
            
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.button1:
			showMessage("�����ŕ\�����܂�");
			break;
     	case R.id.button2:
            showMessage("�E���ŕ\�����܂�");
            break;
            }
		}
	
	/**�g�[�X�g�ݒ�**/
    protected void showMessage(String msg){
    	Toast.makeText(
    			this,
    			msg, Toast.LENGTH_LONG).show();
    	}
    
    //�����𓾂�
    public int widthSet(){
    	return bitmap.getWidth();
    	}
    //�c���𓾂�
    public int heightSet(){
    	return bitmap.getHeight();
    	}
    
    public int[] rgbSet(){
    	int width = widthSet(); //���Z�b�g
    	int height = heightSet(); //�c�Z�b�g
    	
    	int[] rgb = new int[width*height];
		//�s�N�Z���Q�b�g
    	bitmap.getPixels(rgb, 0, width, 0, 0, width, height);
    	return rgb;
    	}
}