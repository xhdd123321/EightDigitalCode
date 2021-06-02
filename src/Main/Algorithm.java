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
        Node cur;
        used.add(start.getCode());
        open.push(start);
        nodeNum = 0;
        while(!open.isEmpty()){
            close.add(open.peekFirst());
            cur = open.peek();
            open.pop();
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

    //A*搜索算法
    public ArrayList<int[][]> Astar(Node start, Node end){
        //TODO
        if(start.isSolvable(end)){
            //初始化初始状态
            start.init(end);
            open.add(start);
            while(open.isEmpty() == false){
                //自定义排序1
                Collections.sort(open, new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        return o1.getEvaluation()-o2.getEvaluation();
                    }
                });
                Node best = open.getFirst();    //从open表中取出最小估值的状态并移除open表
                open.remove();
                close.add(best);
                if(best.isEnd(end)){
                    //输出
                    best.printRoute();
                }
                int move;
                //由best状态进行扩展并加入到open表中
                //0的位置上移之后状态不在close和open中设定best为其父状态，并初始化f(n)估值函数
                if(best.couldMove(1)){
                    move = 0;
                    Node up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                //0的位置下移之后状态不在close和open中设定best为其父状态，并初始化f(n)估值函数
                if(best.couldMove(3)){
                    move = 1;
                    Node up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                //0的位置左移之后状态不在close和open中设定best为其父状态，并初始化f(n)估值函数
                if(best.couldMove(4)){
                    move = 2;
                    Node up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                //0的位置右移之后状态不在close和open中设定best为其父状态，并初始化f(n)估值函数
                if(best.couldMove(2)){
                    move = 3;
                    Node up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
            }
        }else
            System.out.println("没有解，请重新输入。");
        return null;
    }
}
