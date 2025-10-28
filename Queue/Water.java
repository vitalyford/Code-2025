import java.util.*;

/*

queue = [(3,7)]
visited = []
curr_looking_at = 
char[][] room = {
      0   1   2   3   4   5   6   7   8   9
  0 { 1   1   1   1   1   1   1   1   1   1 }
  1 { 1                                   1 }
  2 { 1   1   1   1   1                   1 }
  3 { 1               1           X       1 }
  4 { 1               1                   1 }
  5 { 1       1   1   1                   1 }
  6 { 1                                   1 }
  7 { 1   1   1   1       1   1   1   1   1 }
  8 { 1           1       1               1 }
  9 { 1   1   1   1   1   1   1   1   1   1 }
};
*/

class Pair {
    int row;
    int col;
    int count;

    public Pair(int row, int col, int count) {
        this.row = row;
        this.col = col;
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        Pair p = (Pair) obj;
        return row == p.row && col == p.col;
    }
}

public class Water {
    public static int fillRoomWithWater(char[][] room, int startX, int startY) {
        Queue<Pair> possible = new LinkedList<>();
        Pair start = new Pair(startX, startY, 0);
        possible.offer(start);
        boolean goUp = false;

        ArrayList<Pair> visted = new ArrayList<>();
        int count = 0;

        while (!(possible.isEmpty())) {
            Pair currNode = possible.poll();
            visted.add(currNode);

            if (((room[currNode.row - 1][currNode.col]) == ' ')) {
                Pair nextNode = new Pair(currNode.row - 1, currNode.col, currNode.count + 1);
                if(!(visted.contains(nextNode)))
                {
                    possible.offer(nextNode);
                    goUp = true;
                }
            }
            if (((room[currNode.row + 1][currNode.col]) == ' ')) {
                Pair nextNode = new Pair(currNode.row + 1, currNode.col, currNode.count + 1);
                if(!(visted.contains(nextNode)))
                 {
                    possible.offer(nextNode);
                    goUp = true;
                 }
            }
            if (((room[currNode.row][currNode.col - 1]) == ' ')) {
                Pair nextNode = new Pair(currNode.row, currNode.col - 1, currNode.count + 1);
                if(!(visted.contains(nextNode)))
                    {
                        possible.offer(nextNode);
                        goUp = true;
                    }
            }
            if (((room[currNode.row][currNode.col + 1]) == ' ')) {
                Pair nextNode = new Pair(currNode.row, currNode.col + 1, currNode.count + 1);
                if(!(visted.contains(nextNode)))
                    {
                        possible.offer(nextNode);
                        goUp = true;
                    }
            }

            if(possible.isEmpty())
                count = currNode.count;

        }
        return count;
    }

    public static void main(String[] args) {
        char[][] room = {
                { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
                { '1', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '1' },
                { '1', '1', '1', '1', '1', ' ', ' ', ' ', ' ', '1' },
                { '1', ' ', ' ', ' ', '1', ' ', ' ', ' ', ' ', '1' },
                { '1', ' ', ' ', ' ', '1', ' ', ' ', ' ', ' ', '1' },
                { '1', ' ', '1', '1', '1', ' ', ' ', ' ', ' ', '1' },
                { '1', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '1' },
                { '1', '1', '1', '1', ' ', '1', '1', '1', '1', '1' },
                { '1', ' ', ' ', '1', ' ', '1', ' ', ' ', ' ', '1' },
                { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' }
        };

        int startX = 1, startY = 1;

        System.out.println(fillRoomWithWater(room, startX, startY));
    }
}
