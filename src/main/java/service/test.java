package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author GrayCat.
 * @date 2022/04/27 15:19
 */
public class test {

    public static void main(String[] args) throws java.io.IOException, InterruptedException {
        try {
            String command = "cmd /c adb logcat -v time > d:\\ddd.txt ";
            Process p = Runtime.getRuntime().exec(command);
            StreamCaptureThread errorStream = new StreamCaptureThread(p.getErrorStream());
            StreamCaptureThread outputStream = new StreamCaptureThread(p.getInputStream());
            new Thread(errorStream).start();
            new Thread(outputStream).start();
            p.waitFor();

            String result = command + "\n" + outputStream.output.toString()
                    + errorStream.output.toString();
            System.out.print(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static class StreamCaptureThread implements Runnable {
        InputStream stream;
        StringBuilder output;

        public StreamCaptureThread(InputStream stream) {
            this.stream = stream;
            this.output = new StringBuilder();
        }

        public void run() {
            try {
                try {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(this.stream));
                    String line = br.readLine();
                    while (line != null) {
                        if (line.trim().length() > 0) {
                            output.append(line).append("\n");
                        }
                        line = br.readLine();
                    }
                } finally {
                    if (stream != null) {
                        stream.close();
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
    }

    }
