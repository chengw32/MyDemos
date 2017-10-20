//#include "com_test_custom_mydemos_NDK_L6_MyJni.h"
#include <jni.h>
#include <stdio.h>
#include <string.h>
#include <android/log.h>
#include <stdlib.h>
#include <unwind.h>
#include <dlfcn.h>

#define TAG "native-lib.c"
#define loge(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

// jni访问java的成员变量
JNIEXPORT jstring JNICALL Java_com_test_custom_mydemos_NDK_L6_L6_testC
        (JNIEnv * env, jobject jobj){
    //1、从 jobject 获取 jclass
    jclass clz = (*env)->GetObjectClass(env,jobj);
    //2、从 jclass 获取 变量的id ,第三个参数是变量名，第四个参数是类型签名
    //关于类型签名可以用 [javap -s xx.class] 查看。 (javap -s 只能对 .class 文件有效。 calss文件 通过 javac 编译)
    jfieldID  jfid = (*env)->GetFieldID(env,clz,"t","Ljava/lang/String;");
    //3、从 jobject 得到成员变量对应的值 这个值只有 jni 认识
    jstring jstr = (*env)->GetObjectField(env,jobj,jfid);
    //4、将 jni 认识的字符串转换成 c 认识字符串
    //最后一个参数是boolean类型表示这个指针是否是复制了一份的
    //是这个函数的返回值 而不是传进去的 用来判断这个指针是否需要释放
    char *c_char = (*env)->GetStringUTFChars(env,jstr,NULL);
    //5、用 c 语言修改值
    char *c = "wtf" ;
    strcat(c_char,c);//需要引入 string.h 头文件
    //6、将 c 语言的字符串转换成 jin 认识的字符串
    jstring jstrBack = (*env)->NewStringUTF(env,c_char);
    //7、将转换好的字符串赋值回从java端来的 jobject
    (*env)->SetObjectField(env,jobj,jfid,jstrBack);
    //8、释放指针
    (*env)->ReleaseStringChars(env,jstrBack,c_char);
    return jstrBack;
}

//jni访问java的方法
JNIEXPORT jint JNICALL Java_com_test_custom_mydemos_NDK_L6_L6_getMethodFromJava
        (JNIEnv * env, jobject jobj){

    jclass clz = (*env)->GetObjectClass(env,jobj);
    jmethodID jid = (*env)->GetMethodID(env,clz,"getNumFromJava","(I)I");
    //第四个参数开始是指需要传的参数，多个参数在后面添加就好
    jint valueFromJava = (*env)->CallIntMethod(env,jobj,jid);
    return valueFromJava;
}
//jni访问java静态的方法
JNIEXPORT jint JNICALL Java_com_test_custom_mydemos_NDK_L6_L6_getStaticMethodFromJava
        (JNIEnv * env, jobject jobj){
    jclass clz = (*env)->GetObjectClass(env,jobj);
    jmethodID jid = (*env)->GetStaticMethodID(env,clz,"getStaticNumFromJava","(I)I");
    //第四个参数开始是指需要传的参数，多个参数在后面添加就好
    jint valueFromJava = (*env)->CallStaticIntMethod(env,clz,jid,10);
    return valueFromJava;
}
//jni访问java的构造方法
JNIEXPORT jlong JNICALL Java_com_test_custom_mydemos_NDK_L6_L6_getClassFromJava
        (JNIEnv * env, jobject jobj){
    //通过类的路径从jvm找到这个类 第三个参数 可以看这个类的包名 包名就是这类的路径
    jclass  jclz = (*env)->FindClass(env,"java/util/Date");
    //找到构造方法的id 第三个参数 构造方法都穿这个
    jmethodID jmid = (*env)->GetMethodID(env,jclz,"<init>","()V");
    //拿到构造方法后构造一个对象
    jobject jobject = (*env)->NewObject(env,jclz,jmid);
    //用类获取类里面的某个方法的id
    jmethodID methodId = (*env)->GetMethodID(env,jclz,"getTime","()J");
    //获取方法返回的值
    jlong  jlongValue = (*env)->CallLongMethod(env,jobject,methodId);

    return jlongValue;
}

JNIEXPORT jstring JNICALL Java_com_test_custom_mydemos_NDK_L6_L6_getStringFromJava
        (JNIEnv * env, jobject jobj, jstring jstr){
    char * chars = (*env)->GetStringUTFChars(env,jstr,NULL);//utf-8 对应c/c++
//    jchar jchar = (*env)->GetStringChars(env,jstr,NULL);//utf-16 JNI识别
    jstring jstrValue = (*env)->NewStringUTF(env,chars);

   loge("%s \n",chars);
    return  jstrValue ;
}


int compare(jint *a,jint *b){
    return  *a - *b ;
}
//访问java数组
JNIEXPORT void JNICALL Java_com_test_custom_mydemos_NDK_L6_L6_getArrayFromJava
        (JNIEnv * env, jobject jobj, jintArray jiarray){

    //1、拿到数组元素
    jintArray * elements = (*env)->GetIntArrayElements(env,jiarray,NULL);
    jsize * length = (*env)->GetArrayLength(env,jiarray);

//    2、排序
//    第一个参数 base 是 需要排序的目标数组名（或者也可以理解成开始排序的地址，因为可以写&s[i]这样的表达式）
//    第二个参数 num 是 参与排序的目标数组元素个数++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    第三个参数 width 是单个元素的大小（或者目标数组中每一个元素长度），推荐使用sizeof(s[0]）这样的表达式
//    第四个参数 compare 就是让很多人觉得非常困惑的比较函数啦。
    qsort(elements,length, sizeof(jint),compare);
//    3、重新赋值 并释放内存
//    第二个参数是被赋值对象
//    第三个参数是需要赋的值
//    第四个参数是模式
    (*env)->ReleaseIntArrayElements(env,jiarray,elements,JNI_COMMIT);
}
//引用类型数组
JNIEXPORT jobjectArray JNICALL Java_com_test_custom_mydemos_NDK_L6_L6_getStringArrayFromJNI
        (JNIEnv * env, jobject jobj, jint jsize){

    jobjectArray jarray;
    int i;
    jclass jclz = (*env)->FindClass(env,"java/lang/String");
    if (jclz == NULL){
        return NULL;
    }
    jarray = (*env)->NewObjectArray(env,jsize,jclz,NULL);
    for ( i = 0; i < jsize; i++) {
        char *strs = (char *)malloc(25);
        if (strs == NULL){
            loge("*strs=null");
            return NULL;
        }
        memset(strs,0,25);
        sprintf(strs,"JNI-%d",i);
        jstring jstr = (*env)->NewStringUTF(env,strs);
        if (jstr == NULL){
            loge("jstr=null");
            return NULL;
        }
        (*env)->SetObjectArrayElement(env,jarray,i,jstr);
        free(strs);
        strs = NULL ;
    }
    return jarray;
}
//JNI引用
jobject globalObj ;
JNIEXPORT jobject JNICALL Java_com_test_custom_mydemos_NDK_L6_L6_creatRefInJNI
        (JNIEnv * env, jobject jobj){
//    局部引用
//    jobject  jobj = (*env)->NewObject(env,jclz,methodId);
//    全局引用
    jobject bojt = (*env)->NewStringUTF(env,"not global ref");
    return globalObj;
}


//JNI全局引用
JNIEXPORT jobject JNICALL Java_com_test_custom_mydemos_NDK_L6_L6_getGlobalRefFromJNI
        (JNIEnv * env, jobject jobje){
    jobject  obj = (*env)->NewStringUTF(env,"wtf");
    globalObj = (*env)->NewGlobalRef(env,obj);
    return globalObj;
}


//异常处理
JNIEXPORT jobject JNICALL Java_com_test_custom_mydemos_NDK_L6_L6_exceptionFromJNI
        (JNIEnv * env, jobject jobje){

    jclass clz =(*env)->GetObjectClass(env,jobje);
    (*env)->GetFieldID(env,clz,"tt","Ljava/lang/String");
    jthrowable exthrow = (*env)->ExceptionOccurred(env);
    if (exthrow !=  NULL){
        (*env)->ExceptionClear(env);
        jclass  clz ;
        clz = (*env)->FindClass(env,"java/lang/Exception");
        if (clz == NULL){
            return NULL;
        }
        (*env)->ThrowNew(env,clz,"GetFieldID Exception");
    }
    return globalObj;
}


//缓存
JNIEXPORT jobject JNICALL Java_com_test_custom_mydemos_NDK_L6_L6_cacheFromJNI
                          (JNIEnv * env, jobject jobje){


}

