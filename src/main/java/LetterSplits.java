import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by baathreya on 1/6/16.
 *
 * https://www.reddit.com/r/dailyprogrammer/comments/3xye4g/20151223_challenge_246_intermediate_letter_splits/
 *
 * The trick is to loop through the input integer one digit at a time and for each digit consider 2 possibilities -
 * the digit on its own + rest of the digits and
 * the digit and next digit + rest of the digits.
 *
 * memoize intermediate results and add all combinations to a list and return the  result list
 *
 * tbd: memoize intermediate results. handle strings ending with 0.
 */
public class LetterSplits {

    public static void main(String[] args) {
        Set<String> set = letterSplitter("1234");
        for(String str : set) {
            System.out.println(str);
        }
    }

    public static Set<String> letterSplitter(String str) {
        Set<String> result = new HashSet<String>();
        if(str.length() == 1) {
            String s = getChar(str.charAt(0));
            result.add(s);
            return result;
        }
        else if(str.length() == 2) {
            String firstChar = getChar(str.charAt(0));
            String secondChar = getChar(str.charAt(1));
            String firstTwoChar = getChar(str);
            if(firstTwoChar.equalsIgnoreCase(firstChar+secondChar)){
                result.add(firstTwoChar);
            }
            else {
                result.add(firstChar+secondChar);
                result.add(firstTwoChar);
            }
            return result;
        }
        else {
            String prefix1 = getChar(str.charAt(0));
            String prefix2 = getChar(str.substring(0,2));
            Set<String> set1 = letterSplitter(str.substring(1));
            Set<String> set2 = letterSplitter(str.substring(2));
            for(String s : set1 ){
                result.add(prefix1+s);
            }
            for(String s : set2 ){
                result.add(prefix2+s);
            }
            return result;
        }

    }


    public static String getChar(char i) {
        return String.valueOf((char)(i + 16));
    }

    public static String getChar(String x) {
        int y = Integer.parseInt(x);
        if(y > 26) {
            return getChar(x.charAt(0))+getChar(x.charAt(1));
        }
        return String.valueOf((char)(y + 64));
    }
}
