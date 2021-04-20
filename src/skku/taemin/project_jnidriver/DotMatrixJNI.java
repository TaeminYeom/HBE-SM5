package skku.taemin.project_jnidriver;

public class DotMatrixJNI {
	static {
		System.loadLibrary("JNIDriver");
	}
	
	public native void DotMatrixControl(String str);
}
