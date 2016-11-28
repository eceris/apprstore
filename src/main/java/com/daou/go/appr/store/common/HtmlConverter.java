package com.daou.go.appr.store.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlConverter {

    private static final Logger logger = LoggerFactory.getLogger(HtmlConverter.class);

    public boolean runCmd(String strCmd) {
        boolean returnVal = false;
        Runtime run = Runtime.getRuntime();
        Process p = null;
        try {
            p = run.exec(strCmd);
            StreamPrintThread errprint = new StreamPrintThread(p.getErrorStream());
            StreamPrintThread okprint = new StreamPrintThread(p.getInputStream());
            p.getOutputStream().close();
            errprint.start();
            okprint.start();
            int rst = p.waitFor();
            if (0 == rst) {
                logger.info("RunProgram success : {}", strCmd);
                returnVal = true;
            } else {
                logger.info("RunProgram fail (rst:{}) :  {}", rst, strCmd);
                returnVal = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != p)
                p.destroy();
        }
        return returnVal;
    }
}

class StreamPrintThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(StreamPrintThread.class);

    BufferedReader br = null;

    private StreamPrintThread() {
    }

    public StreamPrintThread(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
    }

    void close() {
        try {
            if (br != null)
                br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void run() {
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            logger.error("[HtmlConverter] Inner Class StreamPrintThread Run fail : {} ", e.getMessage());
        } finally {
            close();
        }
    }
}
