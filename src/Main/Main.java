package Main;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] agrs) {
        test();     //测试
    }

    private static void test(){
        Algorithm Algorithm = new Algorithm();
        int[][] startMap = {    {0,8,7},
                                {6,5,4},
                                {3,2,1}};

        int[][] endMap = {      {1,2,3},
                                {4,5,6},
                                {7,8,0}};

        Node start = new Node();
        Node end = new Node();
        start.setMap(startMap);
        end.setMap(endMap);
        if(start.isSolvable(end)){
            ArrayList<int[][]> res = Algorithm.bfs(start, end);
        }else{
            System.out.println("Unsolvable, END!");
        }
    }
}
