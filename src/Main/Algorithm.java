package Main;

import java.util.*;

//算法
public class Algorithm {
    public int nodeNum;     //节点数
    private HashSet<Integer> used= new HashSet<>();      //标记数组
    private Deque<Node> open = new LinkedList<>();       //open表
    private Deque<Node> close = new LinkedList<>();      //close表
    private String information;

    //工具函数
    public static boolean equal(final int[][] arr1, final int[][] arr2) {
        if (arr1 == null) {
            return (arr2 == null);
        }
        if (arr2 == null) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (!Arrays.equals(arr1[i], arr2[i])) {
                return false;
            }
        }
        return true;
    }

    public String getInformation(){
        return information;
    }
    //宽度优先搜索算法
    public ArrayList<int[][]> bfs(Node start, Node end) {
        Node cur;  //当前节点
        used.add(start.getCode());
        open.offerLast(start);
        nodeNum = 0;
        while(!open.isEmpty()){
            close.add(open.peekFirst());
            cur = open.pollFirst();
            for(int i = 1; i <= 4; i++){    //遍历四个方向
                if(cur.couldMove(i)){   //判断能否移动
                    Node temp = cur.move(i);
                    nodeNum++;
                    if(used.add(temp.getCode())){
                        if(temp.isEnd(end)){
                            information="nodeNum : " + nodeNum + "  depth : " + temp.getDepth();
                            System.out.println("Success, the route is below:");
                            long endtime=System.currentTimeMillis(); //获取结束时间
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
        Node cur;
        used.add(start.getCode());
        open.push(start);
        nodeNum = 0;
        while(!open.isEmpty()){
            close.add(open.peekFirst());
            cur = open.peek();
            open.pop();
            for(int i = 1; i <= 4; i++){    //遍历四个方向
                if(cur.couldMove(i)){   //判断能否移动
                    Node temp = cur.move(i);
                    nodeNum++;
                    if(used.add(temp.getCode())){
                        if(temp.isEnd(end)){
                            information="nodeNum : " + nodeNum + "  depth : " + temp.getDepth();
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

    //A*搜索算法
    public ArrayList<int[][]> Astar(Node start, Node end){
        //TODO
        ArrayList<Node> open_a = new ArrayList<Node>();
        ArrayList<Node> close_a = new ArrayList<Node>();

        if(start.isSolvable(end)){
            //初始化初始状态
            start.init(end);
            open_a.add(start);
            nodeNum=0;
            while(open_a.isEmpty() == false){
                //open排序
                Node best = open_a.get(0);    //从open表中取出最小估值的状态并移除open表
                open_a.remove(0);
                close_a.add(best);
                nodeNum++;
                if(used.add(best.getCode())) {
                    if (best.isEnd(end)) {//输出
                        information="nodeNum : " + nodeNum + "  depth : " + best.getDepth();
                        System.out.println("Success, the route is below:");
                        return best.printRoute();
                    }
                }
                //由best状态进行扩展并加入到open表中
                //0的位置移之后状态不在close和open中设定best为其父状态，并初始化f(n)估值函数
                for(int i = 1; i <= 4; i++){
                    if(best.couldMove(i)){
                        Node up = best.move(i);
                        up.operation(open_a, close_a, best, end);
                    }
                }
            }
        }else
            System.out.println("没有解，请重新输入。");
        return null;
    }
}
