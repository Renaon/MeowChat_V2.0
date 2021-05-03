import java.util.ArrayList;
import java.util.Random;

public class ArrayFocus {

    public static int[] fourCut(int[] arr){
        int last4 = 0;
        for (int i = 0; i< arr.length; i++){
            if(arr[i] == 4) last4 = i;
        }
        ArrayList<Integer> afterCut =  new ArrayList<>();
        if(last4 == 0) throw new RuntimeException();
        else{
            for(int i  = 0; i<arr.length; i++){
                if(i <= last4) continue;
                else{
                    int tmp = arr[i];
                    afterCut.add(tmp);
                }
            }
        }
        int[] ans = new int[afterCut.size()];
        for (int i = 0; i<afterCut.size(); i++){
            ans[i] = afterCut.get(i);
        }
        return ans;
    }

    public static boolean oneAndFour(int[] arr){
        boolean one = false;
        boolean four = false;
        for(int i: arr){
            if(i == 1) one = true;
            else if(i == 4) four = true;
            else return false;
        }
        return one == four;
    }
}
