import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by baathreya on 1/5/16.
 */
public class FindingKthElement {
    static Random r = new Random();
    public static void main(String[] args) {
        int[] arr = {2,4,1,5,5,36,21,8,13,11,20};
        int[] ca = Arrays.copyOf(arr,arr.length);
        Arrays.sort(ca);
        for(int i = 0; i < arr.length; i++){
            int expected = ca[i];
            int actual = getelement(i,arr);
            System.out.print("expected: "+expected);
            System.out.println(" actual: "+actual);
            if(actual != expected){
                System.out.println("expected and actual not the same");
                System.exit(0);
            }
        }

    }

    public static int getelement(int i, int[] arr){
        if(arr.length == 1){
            return arr[0];
        }
        int randind = r.nextInt(arr.length);
        int pivot = arr[randind];
        List<List<Integer>> lists = splitByPivot(pivot,arr);
        List<Integer> lessthan  =lists.get(0);
        List<Integer> equalto  =lists.get(1);
        List<Integer> greaterthan  =lists.get(2);
        if(i < lessthan.size()) {
            int[] newarr = toIntArray(lessthan.toArray(new Integer[0]));
            return getelement(i,newarr);
        }
        else if(i >= lessthan.size() && i < lessthan.size()+equalto.size()){
            return equalto.get(0);
        }
        else {
            int[] newarr = toIntArray(greaterthan.toArray(new Integer[0]));
            int k = i-(lessthan.size()+equalto.size());
            return getelement(k, newarr);
        }

    }


    public static List<List<Integer>> splitByPivot(int pivot, int[] arr){
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        List<Integer> lessthan = new ArrayList<Integer>();
        List<Integer> equalto = new ArrayList<Integer>();
        List<Integer> greaterthan = new ArrayList<Integer>();
        lists.add(lessthan);
        lists.add(equalto);
        lists.add(greaterthan);
        for(int i = 0; i < arr.length; i++){
            int el = arr[i];
            if(el <pivot) {
                lessthan.add(el);
            }
            else if(el == pivot) {
                equalto.add(el);
            }
            else {
                greaterthan.add(el);
            }
        }
        return lists;
    }

    public static int[] toIntArray(Integer[] arr){
        int[] a = new int[arr.length];
        for(int i = 0; i < a.length;i++){
            a[i] = arr[i];
        }
        return a;
    }
}
