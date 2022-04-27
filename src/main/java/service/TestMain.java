package service;

/**
 * @Author GrayCat.
 * @date 2022/04/27 11:07
 */
import java.io.*;
import java.util.Scanner;


public class TestMain {
    public static void main(String[] args)  {
        String cmdStr = "adb devices";
        String clearLogCmd = "adb logcat -c";
        String logcatCmdStr = "adb logcat -v time *:E | findstr com.growatt.mygro";
        String packageListStr = "adb shell pm list packages -3 ";

        int lineCount = 0, noneCount= 0;
        Process process;
        Runtime run = Runtime.getRuntime();     // run.exec(str)可以执行命令

        try {
            process = run.exec(cmdStr);
            InputStream in = process.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(reader);
            StringBuffer sb = new StringBuffer();
            String message;
            while((message = br.readLine()) != null) {
                sb.append(    message + "\n");
                lineCount++;
            }
//            System.out.println(sb);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("没安装adb，这你咋搞.");
            e.printStackTrace();
        } finally {
//            System.out.println(lineCount+" "+noneCount);
        }
        //判断是否能通过adb连接手机
        if (lineCount == 2){
            System.out.println("未检测到连了手机，确定连了的话重试下.手机要开启USB调试.");
            System.exit(0);
        }
        //输出待测试的app列表
        try {
            process = run.exec(packageListStr);
            InputStream in = process.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(reader);
            StringBuffer sb = new StringBuffer();
            String message;
            while((message = br.readLine()) != null) {
                sb.append(    message + "\n");
            }
            System.out.println("选择待测试的app log.\n"+sb);
        }catch (IOException e) {
            e.printStackTrace();
        }
        String userChoose = null;
        //读取用户输入的包名
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            System.out.println("请输入用户的包名（上面package：后的）：");
            userChoose = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //执行logcat命令，输出到文件log.txt
        try {
            //执行之前先清除之前的log
            run.exec(clearLogCmd);
            String logcatCmd = "cmd /c adb logcat -v time *:E | findstr " + userChoose + " > d:\\log.txt";
            process = run.exec(logcatCmd);
            System.out.println("日志保存位置：d:\\log.txt");
            System.out.println("程序正常运行中，到待测app里操作就行了...");
            process.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("出问题了，不知道是啥问题.重新跑下.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结果保存在D:\\log.txt");



    }
}