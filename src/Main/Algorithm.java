package Main;

import java.util.*;

//算法
public class Algorithm {
    public int nodeNum;     //节点数
    private HashSet<Integer> used= new HashSet<>();      //标记数组
    private Deque<Node> open = new LinkedList<>();       //open表
    private Deque<Node> close = new LinkedList<>();      //close表

    //宽度优先搜索算法
    public ArrayList<int[][]> bfs(Node start, Node end) {
        Node cur;  //当前节点
        used.add(start.getCode());
        open.offerLast(start);
        nodeNum = 0;
        while(!open.isEmpty()){
            close.add(open.peekFirst());
            cur = open.pollFirst();
            System.out.println("nodeNum : " + (nodeNum++) + "  depth : " + cur.getDepth());
            for(int i = 1; i <= 4; i++){    //遍历四个方向
                if(cur.couldMove(i)){   //判断能否移动
                    Node temp = cur.move(i);
                    if(used.add(temp.getCode())){
                        if(temp.isEnd(end)){
                            System.out.println("Success, the route is below:");
                            return temp.printRoute();
                        }else{
                            open.add(temp);
                        }
                    }
                }
            }
        }
        System.out.println("Open is empty,failed");
        return null;
    }

    //深度优先搜索算法
    public ArrayList<int[][]> dfs(Node start, Node end){
        //TODO

        return null;
    }

    //A*搜索算法
    public ArrayList<int[][]> Astar(Node start, Node end){
        //TODO

        return null;
    }
}
