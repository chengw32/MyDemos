
#include <jni.h>
#include <dlfcn.h>
#include <stdio.h>




// --------------------------这块代码是  c 文件调用 c 文件的------------------------
//extern int add(int a ,int b);
//extern jstring appendStr(JNIEnv * env,jstring jstr1,jstring jstr2);
//JNIEXPORT jint JNICALL Java_com_test_custom_mydemos_NDK_L16_L16_getSoPlugIntValue
//        (JNIEnv * env, jobject jobj, jint a, jint b){
//    return add(a,b);
//}
//
//
//JNIEXPORT jstring JNICALL Java_com_test_custom_mydemos_NDK_L16_L16_getSoPlugStrValue
//        (JNIEnv * env, jobject jobj, jstring jstr1, jstring jstr2){
//     return appendStr(env,jstr1,jstr2);
//}








//--------------------------这块代码是 c 文件调用 so 的------------------------

typedef int (* Add)(int a,int b);
JNIEXPORT jint JNICALL Java_com_test_custom_mydemos_NDK_L16_L16_getSoPlugIntValue
        (JNIEnv * env, jobject jobj, jint a, jint b){

    void * handle = dlopen("/data/user/0/com.test.custom.mydemos/lib/libsotest.so",RTLD_LAZY);
    if (handle == NULL){
        return -8 ;
    }

    Add add = NULL ;
    *(void **)(&add) = dlsym(handle,"add");

    return add(a,b);
}


JNIEXPORT jstring JNICALL Java_com_test_custom_mydemos_NDK_L16_L16_getSoPlugStrValue
        (JNIEnv * env, jobject jobj, jstring jstr1, jstring jstr2){
     return NULL;
}