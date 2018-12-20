package net.pmosoft.pony.comm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FileUtil {

    public static void main(String[] args) {
        readWebSrcList("c:/pony/", ".*.java");
        //ft.readWebSrcList("D:/workspace/", ".*BSCP.*ORA04.*.sh");
        //ft.readWebSrcList("D:/workspace/", ".*BSCP.*.sh");
    }

    /*
     * 해당 파일 경로 밑에 모든 파일 및 경로 정보를 출력한다
     * */
    public static List<HashMap<String, String>> readWebSrcList(String filePathName, String fileNm){

        List<HashMap<String, String>> srcInfoList = new ArrayList<HashMap<String, String>>();
        
        File dir = new File(filePathName);
        File[] fileList = dir.listFiles();
        
        try {
            for (int i = 0; i < fileList.length; i++) {
                File file = fileList[i];
                if (file.isFile() && file.getName().matches(fileNm)) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("fileName",  file.getName());
                    map.put("filePathName",  file.getPath().replace('\\', '/'));
                    
                    srcInfoList.add(map);
                    
                    System.out.println(file.getPath().replace('\\', '/').replace("D:/workspace/", ""));
                    
                } else if (file.isDirectory()) {
                    readWebSrcList(file.getCanonicalPath().toString(),fileNm);
                }
                
            }
            
            
        } catch (Exception e) {
        }
        
        return srcInfoList;
    } 
    
    
    // 파일을 존재여부를 확인하는 메소드
    public static Boolean fileIsLive(String isLivefile) {
        File f1 = new File(isLivefile);

        if (f1.exists()) {
            return true;
        } else {
            return false;
        }
    }

    // 파일을 생성하는 메소드
    public static void fileMake(String makeFileName) {
        File f1 = new File(makeFileName);
        try {
            f1.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 파일을 삭제하는 메소드
    public static void fileDelete(String deleteFileName) {
        File I = new File(deleteFileName);
        I.delete();
    }

    
    // 파일을 복사하는 메소드
    public static void fileCopy(String inPathNm, String inFileNm, String outPathNm, String outFileNm) {
        try {
            
            makeDir(outPathNm);
            
            FileInputStream fis = new FileInputStream(inPathNm+"/"+inFileNm);
            FileOutputStream fos = new FileOutputStream(outPathNm+"/"+outFileNm);

            int data = 0;
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }
            fis.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파일을 이동하는 메소드
    public static void fileMove(String inFileName, String outFileName) {
        try {
            FileInputStream fis = new FileInputStream(inFileName);
            FileOutputStream fos = new FileOutputStream(outFileName);

            int data = 0;
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }
            fis.close();
            fos.close();

            // 복사한뒤 원본파일을 삭제함
            fileDelete(inFileName);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 디렉토리의 파일 리스트를 읽는 메소드
    public static List<File> getDirFileList(String dirPath) {
        // 디렉토리 파일 리스트
        List<File> dirFileList = null;

        // 파일 목록을 요청한 디렉토리를 가지고 파일 객체를 생성함
        File dir = new File(dirPath);

        // 디렉토리가 존재한다면
        if (dir.exists()) {
            // 파일 목록을 구함
            File[] files = dir.listFiles();

            // 파일 배열을 파일 리스트로 변화함
            dirFileList = Arrays.asList(files);
        }

        return dirFileList;
    }

    // 디렉토리 생성
    public static void makeDir(String dirPath) {
        File dir = new File(dirPath);
        if(!dir.exists()) dir.mkdirs();
    }
    
    
    // 디렉토리 존재여부
    public static boolean isExistDir(String dirPath) {
        File dir = new File(dirPath);
        return dir.exists();
    }
    
    
    // 파일 존재여부
    public static boolean isExistFile(String pathFileNm) {
        File fileNm = new File(pathFileNm);
        return fileNm.exists();
    }

    /*
     * 파일을 읽어서 String으로 변환(UTF-8)
     * */
    public static String readFile(String filePathName) {
        BufferedReader br = null;
        String src = "";
        try {

            File file = new File(filePathName);
            if (file.isFile()) {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(filePathName)));
                while (true) {
                    String str = br.readLine();
                    if (str != null)
                        src += str + "\n";
                    else
                        break;
                }
                br.close();
            }
        } catch (Exception e) {
            System.out.println("e=" + e.getMessage());
        }

        return src;
    }   

    /*
     * 파일을 읽어서 String으로 변환(EUC-KR)
     * */
    public static String readFileEucKr(String filePathName) {
        BufferedReader br = null;
        String src = "";
        try {

            File file = new File(filePathName);
            if (file.isFile()) {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(filePathName),"EUC-KR"));
                while (true) {
                    String str = br.readLine();
                    if (str != null)
                        src += str + "\n";
                    else
                        break;
                }
                br.close();
            }
        } catch (Exception e) {
            System.out.println("e=" + e.getMessage());
        }

        return src;
    }   
    
    
    
    /*
     * 파일을 읽어서 리스트로 반환
     * */
    public ArrayList<String> readFileList(String file_path) {
        ArrayList<String> al = new ArrayList<String>();
        FileInputStream from = null;
        BufferedReader br = null;
        File f = new File(file_path);
        if(!f.exists()) System.out.println("No exists File");
        if(!f.canRead()) System.out.println("Read protected");
        
        try {
            from = new FileInputStream(f);
            br = new BufferedReader(new InputStreamReader(from));
            
            while( br.ready()) {
                al.add(br.readLine());
            }
            //System.out.println("al="+al.get(1));
        } catch(Exception ex){
            System.out.println("selectFileResult error: " + ex);
        } finally {
            if(from!=null) try{from.close();}catch(IOException e){}
            if(br!=null) try{br.close();}catch(IOException e){}            
        }
        return al;
    }    
    
}
