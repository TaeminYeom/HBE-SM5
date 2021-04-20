package skku.taemin.project;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import skku.taemin.project_jnidriver.*;

public class MainActivity extends Activity {

	Random rand = new Random();
	CheckBox Check[] = new CheckBox[17];
	
//	ImageView Image1;
	TextView textlevel;
	
	boolean image_set1 = true;	
	boolean image_set[] = new boolean[20];
	boolean solving = false;
	boolean flag = true;
	
	int level = 4;
	int count = 0;
	int score = 0;
	int answer[] = new int[20];
		
	Timer time;
	TimerTask mTask;
	BackThread thread;
	
	LEDJNI LEDjni;
	PiezoJNI Piezojni;
	DotMatrixJNI DotMatrixjni;
	SegmentJNI Segmentjni;
	FullcolorLEDJNI FullcolorLEDjni;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.i("TAG", "Start");
						
		Check[1] = (CheckBox) findViewById(R.id.CheckBox01);
		Check[2] = (CheckBox) findViewById(R.id.CheckBox02);
		Check[3] = (CheckBox) findViewById(R.id.CheckBox03);
		Check[4] = (CheckBox) findViewById(R.id.CheckBox04);
		Check[5] = (CheckBox) findViewById(R.id.CheckBox05);
		Check[6] = (CheckBox) findViewById(R.id.CheckBox06);
		Check[7] = (CheckBox) findViewById(R.id.CheckBox07);
		Check[8] = (CheckBox) findViewById(R.id.CheckBox08);
		Check[9] = (CheckBox) findViewById(R.id.CheckBox09);
		Check[10] = (CheckBox) findViewById(R.id.CheckBox10);
		Check[11] = (CheckBox) findViewById(R.id.CheckBox11);
		Check[12] = (CheckBox) findViewById(R.id.CheckBox12);
		Check[13] = (CheckBox) findViewById(R.id.CheckBox13);
		Check[14] = (CheckBox) findViewById(R.id.CheckBox14);
		Check[15] = (CheckBox) findViewById(R.id.CheckBox15);
		Check[16] = (CheckBox) findViewById(R.id.CheckBox16);
		
		textlevel = (TextView) findViewById(R.id.textView1);
		
//		Image1 = (ImageView) findViewById(R.id.ImageBox01);
		
		for(int i = 1; i < 16; ++i)
			image_set[i] = true;
		
		LEDjni = new LEDJNI();
		Piezojni = new PiezoJNI();
		Piezojni.open();
		
		DotMatrixjni = new DotMatrixJNI();
		Segmentjni = new SegmentJNI();
		
		Segmentjni.open();
		Segmentjni.print(0);
		
		FullcolorLEDjni = new FullcolorLEDJNI();
		FullcolorLEDjni.FLEDControl(5, 50, 50, 50);
		
		thread = new BackThread();
		thread.start();
	}

/*
	protected void onResume() {
		Piezojni.open();
		Segmentjni.open();
		super.onResume();
	}
	
	protected void onPause() {
		Piezojni.close();
		Segmentjni.close();
		super.onPause();
	}
*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_1:
            	Piezojni.write((char)1, 500000);
            	judge(1);
//            	Check[1].toggle();
                return true;
            case KeyEvent.KEYCODE_2:
            	Piezojni.write((char)1, 500000);
            	judge(2);
//            	Check[2].toggle();
                return true;
            case KeyEvent.KEYCODE_3:
            	Piezojni.write((char)1, 500000);
            	judge(3);
//            	Check[3].toggle();
                return true;
            case KeyEvent.KEYCODE_4:
            	Piezojni.write((char)1, 500000);
            	judge(4);
//            	Check[4].toggle();
                return true;
            case KeyEvent.KEYCODE_5:
            	Piezojni.write((char)1, 500000);
            	judge(5);
//            	Check[5].toggle();
                return true;
            case KeyEvent.KEYCODE_6:
            	Piezojni.write((char)1, 500000);
            	judge(6);
//            	Check[6].toggle();
                return true;
            case KeyEvent.KEYCODE_7:
            	Piezojni.write((char)1, 500000);
            	judge(7);
//            	Check[7].toggle();
                return true;
            case KeyEvent.KEYCODE_8:
            	Piezojni.write((char)1, 500000);
            	judge(8);
//            	Check[8].toggle();
                return true;
            case KeyEvent.KEYCODE_9:
            	Piezojni.write((char)1, 500000);
            	judge(9);
//            	Check[9].toggle();
                return true;
            case KeyEvent.KEYCODE_0:
            	Piezojni.write((char)1, 500000);
            	judge(10);
//            	Check[10].toggle();
                return true;
            case KeyEvent.KEYCODE_Q:
            	Piezojni.write((char)1, 500000);
            	judge(11);
//            	Check[11].toggle();
                return true;
            case KeyEvent.KEYCODE_W:
            	Piezojni.write((char)1, 500000);
            	judge(12);
//            	Check[12].toggle();
                return true;
            case KeyEvent.KEYCODE_E:
            	Piezojni.write((char)1, 500000);
            	judge(13);
//            	Check[13].toggle();
                return true;
            case KeyEvent.KEYCODE_R:
            	Piezojni.write((char)1, 500000);
            	judge(14);
//            	Check[14].toggle();
                return true;
            case KeyEvent.KEYCODE_T:
            	Piezojni.write((char)1, 500000);
            	judge(15);
//            	Check[15].toggle();
                return true;
            case KeyEvent.KEYCODE_Y:
            	Piezojni.write((char)1, 500000);
            	judge(16);
//            	show_answer();
                return true;
        }
        
        return false;
    }
    
    void turn_LED(int n)
    {
    	char value = (char) ((1 << n) - 1);
    	LEDjni.on(value);
    }
    
    void off_LED()
    {
    	char value = (char) (((1 << level) - 1) - ((1<<count) - 1));
    	LEDjni.on(value);
    }
    
    void make_answer(int n)
    {
		for(int i=0; i<n; ++i)
			answer[i] = rand.nextInt(16) + 1;
		
    }

    void show_answer()
    {
    	make_answer(level);
    	
    	if(count > 0 || solving)
    		return;
    	
    	time = new Timer();
    	time.schedule(new TimerTask() {
            @Override
            public void run() {
            	textlevel.post(new Runnable(){
            		public void run()
            		{
                        Check[answer[count]].toggle();                    
                        turn_LED(count+1);
                        if(!flag)
                        	++count;                        
                        flag = !flag;
                        
                        if(count >= level)
                        {
                        	count = 0;
                        	time.cancel();
                    	}
            		}
            	});
            }
        }, 0, 375);
    }
    
    void judge(int n)
    {
    	boolean correct = true;
    	Log.v("judge", "judge");
    	if(answer[count++] == n)
    		Log.v("answer", "correct");
    	else
    	{
    		correct = false;
    		Log.v("answer", "incorrect");
    	}
    	
    	off_LED();
    	if(count == level)
    	{	
    		count = 0;
     
    		if(correct)
    			Correct();
    		else
    			Incorrect();
    	}    	
    }
    
    void solve()
    {
    	solving = true;
    	count = 0; 
    }
    
    void Correct()
    {
//    	++score;
    	score += (level*100);
//    	Segmentjni.print(score);
    	
		FullcolorLEDjni.FLEDControl(5, 0, 100, 0);
    	
    	DotMatrixjni.DotMatrixControl("000OOO");
    	
    	Piezojni.write((char)0, 200000);
    	
    	Piezojni.write((char)1, 200000);
    	Piezojni.write((char)3, 200000);
    	Piezojni.write((char)5, 200000);
    	Piezojni.write((char)17, 200000);
    	
		Toast toast = Toast.makeText(getApplicationContext(), "출력할 문자열", Toast.LENGTH_LONG);
    	toast = Toast.makeText(getApplicationContext(), "정답", Toast.LENGTH_LONG);	
    	toast.show();
    }
        
    void Incorrect()
    {
    	DotMatrixjni.DotMatrixControl("000XXX");
    	
		FullcolorLEDjni.FLEDControl(5, 100, 0, 0);
    	
    	Piezojni.write((char)0, 200000);
     	
    	Piezojni.write((char)49, 200000);
    	Piezojni.write((char)0, 100000);
    	Piezojni.write((char)49, 200000);
    	
		Toast toast = Toast.makeText(getApplicationContext(), "출력할 문자열", Toast.LENGTH_LONG);
    	toast = Toast.makeText(getApplicationContext(), "오답", Toast.LENGTH_LONG);    	    	
    	toast.show();
    }
    
    public void onClickStart(View v)
    {
    	show_answer();
    }
    
    public void onClickInit(View v)
    {
    	count = 0;
    	for(int i=1; i<=16; ++i)
    		Check[i].setChecked(false);
    	turn_LED(0);
	}
    
    public void onClickLeft(View v)
    {
    	String temp = new String();
    	if(level>4)
    	{
    		--level;
    		temp = "";
    		temp += (level-3);
    		textlevel.setText(temp);
    	}    	
    }
    
    public void onClickRight(View v)
    {
    	String temp = new String();
    	if(level<8)
    	{
    		++level;
    		temp = "";
    		temp += (level-3);
    		textlevel.setText(temp);
    	}    	
    }
    
    class BackThread extends Thread {
    	public void run() {
    		while(true)
    		{
    			Segmentjni.print(score);
    			try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	}
    }

}

