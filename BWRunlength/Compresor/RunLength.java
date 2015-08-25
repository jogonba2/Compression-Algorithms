package Compresor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class RunLength
{
    
    public static String compress(String txt,String dname) throws IOException {
        String res = "";
        int count = 0;
        FileOutputStream fos = new FileOutputStream(dname);
        DataOutputStream dos = new DataOutputStream(fos);
        try{
            for(int i=0;i<txt.length();i++){
                for(int j=i+1;j<txt.length();j++)
                {
                    if(txt.charAt(i)==txt.charAt(j)) count++;
                    else break;
                }
                if(count==0){
                    dos.writeInt(1);
                    dos.writeChar(txt.charAt(i));
                    res += "1" + txt.charAt(i);
                }
                else{
                    dos.writeInt(count+1);
                    dos.writeChar(txt.charAt(i));
                    res += String.valueOf((count+1)) + txt.charAt(i);
                    i += count;
                }
                count = 0;
            }
        }catch(IOException e){}
        finally{
            dos.close();
            fos.close();
            return res;
        }
    }
 
    public static String decompress(String fname) throws IOException {
        FileInputStream fos = new FileInputStream(fname);
        DataInputStream dos = new DataInputStream(fos);
        String res = "";
        int numRep = 1;
        char actChr;
        try{
            while(true){
                numRep = dos.readInt();
                actChr = dos.readChar();
                for(int i=0;i<numRep;i++) res += actChr;
            }
        }catch(IOException e){}
        finally{
            dos.close();
            fos.close();
            return res;
        }
    }

}
    