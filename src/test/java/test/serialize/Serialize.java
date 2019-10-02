package test.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Serialize {

    public static void main(String[] args) {
        Serialize s = new Serialize();
        //s.readBin();
        //s.writeBinTest01();
        //s.readBinTest01();
        s.writeBinTest02();
    }

    void readBin(){
        try {
            File pathFileNm = new File("d:/fframe/workspace/pony/src/test/java/test/serialize/LOS.bin");
            if(pathFileNm.exists()) System.out.println("aaaa");

            FileInputStream fis = new FileInputStream(pathFileNm);

            int data = 0;
            //while ((data = fis.read()) != -1) {
            while ((data = fis.read()) != -1) {
                System.out.println(data);
            }
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void writeBinTest01(){
        File file = new File("d:/fframe/workspace/pony/src/test/java/test/serialize/file.bin") ;
        FileOutputStream fos = null ;
        byte[] buf = new byte[5] ;

        // prepare data.
        buf[0] = 0x01 ;
        buf[1] = 0x02 ;
        buf[2] = 0x03 ;
        buf[3] = 0x04 ;
        buf[4] = 0x05 ;

        try {
            // open file.
            fos = new FileOutputStream(file) ;  // fos = new FileOutputStream("file.bin") ;

            // write file.
            fos.write(buf) ;

        } catch (Exception e) {
            e.printStackTrace() ;
        }

        // close file.
        if (fos != null) {
            // catch Exception here or throw.
            try {
                fos.close() ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void writeBinTest02(){
        File file = new File("d:/fframe/workspace/pony/src/test/java/test/serialize/file2.bin") ;
        FileOutputStream fos = null ;
        byte[] buf = new byte[5] ;

        // prepare data.
        buf[0] = 0x01 ;
        buf[1] = 0x02 ;
        buf[2] = 0x03 ;
        buf[3] = 0x04 ;
        buf[4] = 0x7f ;

        try {
            // open file.
            fos = new FileOutputStream(file) ;  // fos = new FileOutputStream("file.bin") ;

            // write file.
            fos.write(buf) ;

        } catch (Exception e) {
            e.printStackTrace() ;
        }

        // close file.
        if (fos != null) {
            // catch Exception here or throw.
            try {
                fos.close() ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    void readBinTest01(){
        File file = new File("d:/fframe/workspace/pony/src/test/java/test/serialize/file.bin") ;
        FileInputStream fis = null ;
        int data = 0 ;

        if (file.exists() && file.canRead()) {
            try {
                // open file.
                fis = new FileInputStream(file) ;

                // read file.
                while ((data = fis.read()) != -1) {
                    // TODO : use data
                    System.out.println("data : " + data) ;
                }

                // close file.
                fis.close() ;
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }
    }
}
