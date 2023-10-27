#include <jni.h>
#include <string>
#include <android/log.h>
#include <stdio.h>
#include <stdlib.h>
#include<time.h>

#define LOG_TAG "luckyboy"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
#define NELEM(...) sizeof(__VA_ARGS__) / sizeof(JNINativeMethod)


int get_lucky_number(JNIEnv *env, jclass clsLucky, jintArray balls){
    int len = env->GetArrayLength(balls);
    jboolean isCopy = false;
    jint* jBalls = env->GetIntArrayElements(balls, &isCopy);  //获取数组的元素
    int a = rand();
    int index = a % len;
    LOGI("get_lucky_number = %d  len=%d  index=%d", jBalls[index], len, index);
    return jBalls[index];
}


JNIEXPORT jint JNI_OnLoad(JavaVM* vm, void* reserved){

    JNIEnv *env = nullptr;
    vm->GetEnv((void**)&env, JNI_VERSION_1_6);

    //动态注册JNI函数
    jclass clsLucky = env->FindClass("com/yooha/luckyboy/LuckyUtil");
    if (env->ExceptionCheck()) {
        env->ExceptionClear();
        env->ExceptionDescribe();
        //LOGE("JNI_OnLoad FindClass error");
        return JNI_ERR;
    }

    JNINativeMethod methods[] = {{"get_lucky_number", "([I)I", (void*)get_lucky_number}};

    if (env->RegisterNatives(clsLucky, methods, NELEM(methods)) < 0){
        //LOGE("JNI_OnLoad RegisterNatives error");
    }

    //LOGI("JNI_OnLoad finish");
    return JNI_VERSION_1_6;
}
