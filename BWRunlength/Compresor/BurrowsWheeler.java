package Compresor;
import Algoritmos.MergeSort;


public class BurrowsWheeler
{
    private char eof;
    
    public String bwTransform(char[] txt){
        this.eof = txt[txt.length-1];
        String spinneds[] = new String[txt.length];
        char first;
        spinneds[0] = new String(txt);
        // Rotate
        for(int i=1;i<txt.length;i++){      
            first = txt[0];
            for(int j=0;j<txt.length-1;j++) txt[j] = txt[j+1];
            txt[txt.length-1] = first;
            spinneds[i] = new String(txt);
        }
        // Sort
        MergeSort.mergeSort(spinneds);
        // Complete
        String res = "";
        for(String spin : spinneds) res += spin.charAt(spin.length()-1); 
        return res;
    }
    
    public String bwTransformInv(String txt){
        String[] fullTable = new String[txt.length()];
        // Init fullTable
        for(int i=0;i<txt.length();i++){
            fullTable[i] = "";
            fullTable[i] = fullTable[i]+txt.charAt(i);
        }
        // Process
        for(int i=1;i<txt.length();i++){
            MergeSort.mergeSort(fullTable);
            for(int j=0;j<txt.length();j++) fullTable[j] = txt.toCharArray()[j] + fullTable[j];
        }
        // Take Correct
        String msg = "There was an error, warn it";
        for(String e : fullTable) if(e.charAt(txt.length()-1)==eof) msg = e;
        return msg;
        
    }
        
}
