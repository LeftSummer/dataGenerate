import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Main {

    final static String dbName = "sakila_new";
    final static String filePath = "C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Data\\"+dbName+"\\";
    static Random random = new Random();
    public static void main(String[] args){
        int generateCount = 200000;
        try {
            File file = new File(filePath+"address.txt");
            FileWriter fileWriter =new FileWriter(file);
            if(file.exists()){
                fileWriter.write("");
            }else {
                file.createNewFile();
            }
            for (int i = 0; i < generateCount; i++) {
                fileWriter.write(getAddressLine());
            }
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String getAddressLine(){
        String str = "";
        str+=getRandomChinese(5)+",";//address
        str+=getRandomString(3,15)+",";//address2
        str+="district-"+Math.abs(random.nextInt())+",";//district
        str+=random.nextInt(11)+1+",";//cityId
        //str+="310000"; //postCode
        str+=RandomValue.getTel()+";";//+"\r\n";          //phone
        return  str;
    }

    public static String getFormatDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    public static String getRandomChinese(int nameLength){
        String str = "";
        try {
            for (int i = 0; i < nameLength; i++) {
                int highCode;
                int lowCode;
                highCode = (176 + Math.abs(random.nextInt(39))); //B0 + 0~39(16~55) 一级汉字所占区
                lowCode = (161 + Math.abs(random.nextInt(93))); //A1 + 0~93 每区有94个汉字
                byte[] b = new byte[2];
                b[0] = (Integer.valueOf(highCode)).byteValue();
                b[1] = (Integer.valueOf(lowCode)).byteValue();
                str += new String(b, "GBK");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
    public static String getMobilePhone(){
        String mobile = "";

        return mobile;
    }
    /**
     * get string that length between minLength and maxLength (closed interval)
     *
     * @param minLength random string min length
     * @param maxLength random string max length
     * */
    public static String getRandomString(int minLength,int maxLength){
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        int randomLength = random.nextInt(maxLength-minLength + 1)+minLength;
        String randStr = "";
        for (int i = 0; i < randomLength; i++) {
            randStr+=str.charAt(random.nextInt(str.length()));
        }
        return  randStr;
    }
}
