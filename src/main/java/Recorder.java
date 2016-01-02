import java.util.*;

/**
 * Created by baathreya on 1/1/16.
 *
 * https://www.reddit.com/r/dailyprogrammer/comments/3u6o56/20151118_challenge_242_intermediate_vhs_recording/
 *
 */
public class Recorder {
    public static void main(String[] args) {
        String[] input = {"1530 1600","1605 1630","1645 1725","1700 1730","1700 1745","1705 1745","1720 1815","1725 1810"};
        System.out.println(maxNoOfShows(sortShows(input)));
    }


    public static TreeSet<Show> sortShows(String[] input) {
        TreeSet<Show> set = new TreeSet<Show>();
        for(String in : input) {
            String[] times = in.split(" ");
            Show s = new Show(times[0],times[1]);
            set.add(s);
        }
        return set;
    }

    public static int maxNoOfShows(TreeSet<Show> sortedShows) {
        List<Show> list = new ArrayList<Show>();
        Show current = sortedShows.first();
        list.add(current);
        Iterator<Show> it = sortedShows.iterator();
        it.next();
        while (it.hasNext()) {
            Show next = it.next();
            int startTime = next.startTime;
            if(startTime>=current.endTime) {
                list.add(next);
                current = next;
            }
        }
        for(Show sh : list) {
            System.out.println(sh);
        }
        return list.size();
    }



    static class Show implements Comparable {
        private int startTime;
        private int endTime;

        public Show(String startTime, String endTime) {
            this.startTime = Integer.parseInt(startTime);
            this.endTime = Integer.parseInt(endTime);
        }

        public int compareTo(Object o1) {
            if(o1 == null) {
                throw new NullPointerException();
            }

            Show s = (Show) o1;
            if(s.endTime > this.endTime) {
                return -1;
            }
            else if(s.endTime == this.endTime) {
                return 0;
            }
            return 1;
        }

        @Override
        public String toString() {
            return startTime+" "+endTime;
        }
    }


}
