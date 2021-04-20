#include <unistd.h>
#include <fcntl.h>
#include <assert.h>
#include <jni.h>
#include <string.h>
#include <stdio.h>
#include "font.h"

#define FULL_LED1	9
#define	FULL_LED2	8
#define FULL_LED3	7
#define FULL_LED4	6
#define ALL_LED		5

JNIEXPORT void JNICALL Java_skku_taemin_project_1jnidriver_LEDJNI_on
(JNIEnv * env, jobject obj, jchar data){
	int fd;

	fd = open("/dev/fpga_led", O_WRONLY);
	assert(fd != 0);

	write(fd, &data, 1);
	close(fd);
}


static int piezo_fd;

JNIEXPORT void JNICALL Java_skku_taemin_project_1jnidriver_PiezoJNI_open
  (JNIEnv * env, jobject obj){
	piezo_fd = open("/dev/fpga_piezo", O_WRONLY);
	assert(piezo_fd != -1);
}

JNIEXPORT void JNICALL Java_skku_taemin_project_1jnidriver_PiezoJNI_write
  (JNIEnv * env, jobject obj, jchar data, jint time){
	write(piezo_fd, &data, 1);
	usleep(time);

	data=0;
	write(piezo_fd, &data, 1);
}

JNIEXPORT void JNICALL Java_skku_taemin_project_1jnidriver_PiezoJNI_close
  (JNIEnv * env, jobject obj){
	close(piezo_fd);
}


JNIEXPORT void JNICALL Java_skku_taemin_project_1jnidriver_DotMatrixJNI_DotMatrixControl
  (JNIEnv* env, jobject thiz, jstring str){
	const char *pStr;
	int fd, len;

	pStr = (*env)->GetStringUTFChars(env, str, 0);
	len = (*env)->GetStringLength(env, str);

	printf("%s", pStr);

	fd = open("/dev/fpga_dotmatrix", O_RDWR | O_SYNC);

	write(fd, pStr, len);
	close(fd);

	(*env)->ReleaseStringUTFChars(env, str, pStr);
}

/*
JNIEXPORT void JNICALL Java_skku_taemin_project_1jnidriver_DotMatrixJNI_DotMatrixControl
  (JNIEnv* env, jobject thiz, jstring str){
	char *input;
	int fd, len;

	int dev, i, j, offset = 20, ch;//, len;
	char result[600], tmp[2];

	input = (*env)->GetStringUTFChars(env, str, 0);
	len = (*env)->GetStringLength(env, str);

	fd = open("/dev/fpga_dotmatrix", O_RDWR | O_SYNC);

//	write(fd, pStr, len);


	for (j = 0; j < 20; j++)
			result[j] = '0';

	for (i = 0; i < len; i++) {
		ch = input[i];

		ch -= 0x20;

		for (j = 0; j < 5; j++) {
			sprintf(tmp, "%x%x", font[ch][j] / 16, font[ch][j] % 16);

			result[offset++] = tmp[0];
			result[offset++] = tmp[1];
		}
		result[offset++] = '0';
		result[offset++] = '0';
	}

	for (j = 0; j < 20; j++)
		result[offset++] = '0';

	for (i = 0; i < (offset - 18) / 2; i++) {
		for (j = 0; j < 20; j++) {
			write(dev, &result[2 * i], 20);
		}
	}


	close(fd);

	(*env)->ReleaseStringUTFChars(env, str, input);
}*/


static int segment_fd;

JNIEXPORT void JNICALL Java_skku_taemin_project_1jnidriver_SegmentJNI_open
  (JNIEnv * env, jobject obj){
	segment_fd = open("/dev/fpga_segment", O_WRONLY);
	assert(segment_fd != -1);
}

JNIEXPORT void JNICALL Java_skku_taemin_project_1jnidriver_SegmentJNI_print
  (JNIEnv * env, jobject obj, jint num){
	char buf[7];
	sprintf(buf, "%06d", num);
	printf(stdout, "num: %s\n", num);
	write(segment_fd, buf, 6);
}

JNIEXPORT void JNICALL Java_skku_taemin_project_1jnidriver_SegmentJNI_close
  (JNIEnv * env, jobject obj){
	close(segment_fd);
}


JNIEXPORT void JNICALL Java_skku_taemin_project_1jnidriver_FullcolorLEDJNI_FLEDControl
(JNIEnv* env, jobject thiz, jint led_num, jint val1, jint val2, jint val3)
{
	int fd,ret;
	char buf[3];

	fd = open("/dev/fpga_fullcolorled", O_WRONLY);
	if (fd < 0)
	{
		exit(-1);
	}
	ret = (int)led_num;
	switch(ret)
	{
		case FULL_LED1:
			ioctl(fd,FULL_LED1);
			break;
		case FULL_LED2:
			ioctl(fd,FULL_LED2);
			break;
		case FULL_LED3:
			ioctl(fd,FULL_LED3);
			break;
		case FULL_LED4:
			ioctl(fd,FULL_LED4);
			break;
		case ALL_LED:
			ioctl(fd,ALL_LED);
			break;
	}
	buf[0] = val1;
	buf[1] = val2;
	buf[2] = val3;

	write(fd,buf,3);

	close(fd);
}
