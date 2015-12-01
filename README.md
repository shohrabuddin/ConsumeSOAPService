# ConsumeSOAPService
From this project you can learn how to call a SOAP web service from android. I have used KSOAP2 library to make SOAP web service call from android application. You will easily understand the source code because I put a lot of comments throughout the project.

**Prerequisites**
  1. Android Studio
  2. ksoap2-android-assembly-2.6.0-jar-with-dependencies.jar or latest version
  3. Butteer Knife: com.jakewharton:butterknife:6.1.0
  4. Android Support Design Libraries: com.android.support:design:23.1.0
  
  
**Optional Prerequisites**
  1. Eclipse for Java EE
  2. Apache Tomcat server 7.0
  
I have used soap_webservice project as the SOAP webserive. You can download it from my Github repo https://github.com/shohrabuddin/soap_webservice.git. 

**Build.gradle**
````java
apply plugin: 'com.android.application'

android {
    compileSdkVersion 23
    buildToolsVersion "23.0.2"

    defaultConfig {
        applicationId "com.consumesoapservice"
        minSdkVersion 16
        targetSdkVersion 23
        versionCode 1
        versionName "1.0"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.0.1'
    compile 'com.jakewharton:butterknife:6.1.0'
    compile 'com.android.support:design:23.1.0'

}
```
 

