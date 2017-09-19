//#include "com_test_custom_mydemos_NDK_L6_MyJni.h"
#include <jni.h>


JNIEXPORT jstring JNICALL Java_com_test_custom_mydemos_NDK_L6_MyJni_getMyC
        (JNIEnv * env, jclass clz){
    return (*env) ->NewStringUTF(env,"wtf");
}