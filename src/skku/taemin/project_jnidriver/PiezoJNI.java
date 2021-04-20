package skku.taemin.project_jnidriver;

public class PiezoJNI {
	static {
		System.loadLibrary("JNIDriver");
	}
	
	public native void open();
	public native void write(char data, int time);
	public native void close();
}
