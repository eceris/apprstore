package com.daou.go.appr.store;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.daou.go.appr.store.common.HtmlConverter;

public class ImageConvertTest {
    public static void main(String[] args) {                            
        BufferedWriter bw;
        File htmlFile = null;
            try {
                htmlFile = new File("C:\\test.html");
                bw = new BufferedWriter(new FileWriter(htmlFile));
                bw.write("<html><head><title>New Page</title></head><body><p>This is Body</p></body></html>");
                bw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                                     
        String strImage = "C:\\test.jpg";
        String strCmd = "C:\\Program Files\\wkhtmltopdf\\bin\\wkhtmltoimage.exe";
        strCmd += " " + htmlFile.getAbsolutePath();
        strCmd += " " + strImage;
        
        HtmlConverter t = new HtmlConverter();          
        System.out.println(  t.runCmd(strCmd)  );
    }

}
