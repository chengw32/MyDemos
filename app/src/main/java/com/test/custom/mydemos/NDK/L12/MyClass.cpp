//
// Created by Administrator on 2017/10/23.
//

#include "MyClass.h"
#include <android/log.h>

#define LOG_TAG "System.out.cpp"
#define loge(...) __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

int MyClass::getAge(){

}

void MyClass::MyClass() {
    loge("构造方法 ");
}