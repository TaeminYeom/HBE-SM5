package skku.taemin.project_jnidriver;

public class FullcolorLEDJNI {
	static {
		System.loadLibrary("JNIDriver");
	}
	
	public native void FLEDControl(int led_num, int val1, int val2, int val3);
}
