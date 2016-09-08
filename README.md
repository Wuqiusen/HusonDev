# HusonDev
#Android自用开发框架
##How to
####To get a Git project into your build:
####Step 1. Add the JitPack repository to your build file
####Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
####Step 2. Add the dependency

	dependencies {
	         compile 'com.github.Wuqiusen:HusonDev:v1.0.5'
	}

##关于项目
####参考：
https://github.com/PleaseCallMeCoder/PrettyGirls

https://github.com/jiangqqlmj/FastDev4Android
