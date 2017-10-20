


#include <jni.h>
#include <stdio.h>
#include <malloc.h>
#include <string.h>

int add(int a ,int b){
    return  a+b ;
}
jstring appendStr(JNIEnv * env,jstring jstr1,jstring jstr2){
    char * str1 = (*env)->GetStringUTFChars(env,jstr1,NULL);
    char * str2 = (*env)->GetStringUTFChars(env,jstr2,NULL);

    //申请空间拼接成一个新的字符串
//    char * result = (char *) malloc(strlen(str1)+ strlen(str2) + 1);
//    memset(result,0,strlen(str1)+ strlen(str2) + 1);
//    strcat(result,str1);
//    strcat(result,str2);
//用 sprintf 直接拼接
//    sprintf(result,"%s%s",str1,str2);
//    free(result);
//用 strcat 直接把 str2拼接到str1 上
    strcat(str1,str2);
//    strcpy(result,str1);
//    strcpy(result,str2);
    return (*env)->NewStringUTF(env,str1);
}
