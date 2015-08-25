package Algoritmos;

public class MergeSort{
    
    /* MergeSort */
    public static <T extends Comparable<T>> void mergeSort(T[] v) {
        T[] aux = mergeSort(v, 0, v.length - 1);
        // Se copia el array resultante en el original //
        for (int i = 0; i < v.length; i++) v[i] = aux[i];
    }    
    
    /* PRECONDICION: i<=f */
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> T[] mergeSort(T[] v, int i, int f) {
        T[] res;
        if (i+1 >= f){
            if(i==f){
                res = (T[]) new Comparable[1];
                res[0]=v[i];
            }
            else{
                res = (T[]) new Comparable[2];
                if(v[i].compareTo(v[f]) < 0){
                    res[0]=v[i];
                    res[1]=v[f];
                }else{
                    res[0]=v[f];
                    res[1]=v[i];
                }
            }
        }else { // if (i < f)
            int m = (i + f) / 2;
            T[] v1 = mergeSort(v, i, m);
            T[] v2 = mergeSort(v, m + 1, f);
            res = merge(v1, v2);
        }
        return res;
    }
    
    
    // Mezcla
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> T[] merge(T[] v1, T[] v2) {
        int contv1 = 0;
        int contv2 = 0;
        int k = 0;
        T[] res = (T[]) new Comparable[v1.length + v2.length];
        while (contv1<v1.length && contv2<v2.length){
            if (v1[contv1].compareTo(v2[contv2]) < 0){
                res[k] = v1[contv1]; contv1++;
            }else {
                res[k] = v2[contv2]; contv2++;
            }
            k++;
        }           
        while (contv1<v1.length) {res[k] = v1[contv1]; contv1++; k++;}
        while (contv2<v2.length) {res[k] = v2[contv2]; contv2++; k++;}         
        return res;
    }
}
      