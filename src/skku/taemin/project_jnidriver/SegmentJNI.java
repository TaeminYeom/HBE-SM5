package skku.taemin.project_jnidriver;

public class SegmentJNI {
	static {
		System.loadLibrary("JNIDriver");
	}
	
	public native void open();
	public native void print(int num);
	public native void close();
}
