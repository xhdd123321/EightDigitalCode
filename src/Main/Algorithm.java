package Main;

import java.util.*;

//算法
public class Algorithm {
    public int nodeNum;     //节点数
    private HashSet<Integer> used= new HashSet<>();      //标记数组
    private Deque<Node> open = new LinkedList<>();       //open表
    private Deque<Node> close = new LinkedList<>();      //close表
    private String information;

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
        open.offerFirst(start);
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
                            return temp.printRoute();
                        }else{
                            open.offerFirst(temp);
                        }
                    }
                }
            }
        }
        System.out.println("Open is empty,failed");
        return null;
    }

    //A*搜索算法
    public ArrayList<int[][]> Astar(Node start, Node end) {
        ArrayList<Node> sonsOfN = new ArrayList<Node>();
        ArrayList<Node> open = new ArrayList<>();       //open表
        ArrayList<Node> close = new ArrayList<>();      //close表
        Node cur;
        used.add(start.getCode());
        open.add(start);
        nodeNum = 0;
        while(!open.isEmpty()) {
            close.add(open.get(0));
            used.add(open.get(0).getCode());//将CLOSE表中元素仍入哈希表
            cur = open.remove(0);
            //判断节点N能否扩展 并把扩展后的节点放入OPEN表
            for (int i = 1; i <= 4; i++) {
                if (cur.couldMove(i)) {
                    Node temp = cur.move(i);
                    nodeNum++;
                    if (!used.contains(temp.getCode())) {//如果不在CLOSE中
                        if (temp.isEnd(end)) {
                            information="nodeNum : " + nodeNum + "  depth : " + temp.getDepth();
                            System.out.println("Success, the route is below:");
                            return temp.printRoute();
                        } else {
                            temp.countFValue(end);
                            sonsOfN.add(temp);
                        }
                    }
                }
            }
            //若有节点，则放入OPEN表中
            if (sonsOfN.size() >= 1) {
                open.addAll(sonsOfN);
                sonsOfN.clear();
            }
            //对OPEN表排序
            Collections.sort(open, (x,y)-> x.getFvalue() - y.getFvalue());
        }
        System.out.println("Open is empty,failed");
        return null;
    }
}
