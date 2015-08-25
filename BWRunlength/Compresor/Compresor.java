package Compresor;
import Compresor.RunLength;
import Compresor.BurrowsWheeler;
import java.io.IOException;
import java.util.Scanner; // Para tests
public class Compresor
{
    public static void main(String args[]){
         System.out.print("\nText to compress: ");
         BurrowsWheeler bw = new BurrowsWheeler();
         Scanner scn       = new Scanner(System.in);
         String text       = scn.nextLine();// Uncompressed
         String cmp        = "";  // Compressed
         String unc        = ""; // Uncompressed
         try{
             // Compress 
             cmp = bw.bwTransform(text.toCharArray());
             System.out.println("\nBurrowsWheelered: " + cmp);
             cmp = RunLength.compress(cmp,"compress.zpo");
             System.out.println("\nCompressed: " + cmp);
             // Uncompress
             unc = bw.bwTransformInv(RunLength.decompress("compress.zpo"));
             System.out.println("\nDecompressed: " + unc);
         }catch(IOException e){}
    }
}