# Android-Crash-Capture-Tools
安卓app错误捕捉小工具

### 简介
捕捉安卓app错误的小工具，可以用来测试闪退、错误啥的。原理是通过adb实现的，所以会用adb的话这个东西好像没啥用。

### 运行环境
1. adb调试桥
2. java 8

### 运行步骤
1. 下载上面那个Crash-Capture.jar。然后打开命令行，运行 `java -jar Crash-Capture.jar`
2. 根据提示选择输入手机上安装的包名，eg：com.xxxx.xxxx
3. 然后在手机上操作就行了，如果有错误级别为error以上的错误时，会自动保存相关的信息到d盘的log.txt文件，发给开发就完事了。

### 常见问题
- adb没安装

    安装包放在  \\\20.60.6.32\虚拟桌面软件\zktest\platform-tools_r33.0.1-windows.zip

- adb连接不到手机

    检查下手机，需要开启开发者模式 -> usb调试. 
