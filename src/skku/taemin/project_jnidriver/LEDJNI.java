package skku.taemin.project_jnidriver;

public class LEDJNI {
	static {
		System.loadLibrary("JNIDriver");
	}
	
	
	public native void on(char data);
}
