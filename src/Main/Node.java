package Main;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;

public class Node {
    private int[][] map = new int[3][3];    // 当前九宫格
    private final Pair space;     //空格位置
    private Node parent;    //父节点
    private int depth;     //当前深度
    private int evaluation;     //从起始状态到目标的最小估计值
    private int misposition;    //到目标的最小估计

    public int getDepth() {
        return this.depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == 0) {
                    space.x = i;
                    space.y = j;
                    return;
                }
            }
        }
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public int getMisposition() {
        return misposition;
    }

    public void setMisposition(int misposition) {
        this.misposition = misposition;
    }

    public class Pair {  //坐标类
        public int x;
        public int y;

        public Pair() {
        }

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    //无参构造
    public Node() {
        space = new Pair();
    }

    public Node(int[][] map, Node parent, int depth, Pair space) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.map[i][j] = map[i][j];
            }
        }
        this.space = new Pair(space.x, space.y);
        this.depth = depth;
        this.parent = parent;
    }

    //获取唯一编码
    public int getCode() {
        int code = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                code = code * 10 + map[i][j];
            }
        }
        return code;
    }

    //判断是否达到目标状态
    public boolean isEnd(Node end) {
        return this.getCode() == end.getCode();
    }

    //统计逆序数之和
    private int getReverseNum() {
        int[] num = new int[9];
        int index = 0;
        for (int curs[] : map) {
            for (int cur : curs) {
                num[index++] = cur;
            }
        }
        int res = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < i; j++) {
                if (num[i] != 0 && num[j] > num[i]) {
                    res++;
                }
            }
        }
        return res;
    }

    //根据逆序数的关系判断是否可解
    public boolean isSolvable(Node target) {
        int[] num = new int[9];
        int index = 0;
        for (int curs[] : map) {
            for (int cur : curs) {
                num[index++] = cur;
            }
        }
        //判断逆序数奇偶性是否相同
        return ((this.getReverseNum() + target.getReverseNum()) & 1) == 0;
    }

    //交换坐标space和a
    public void swap(Pair a) {
        this.map[space.x][space.y] = this.map[a.x][a.y];
        this.map[a.x][a.y] = 0;
        this.space.x = a.x;
        this.space.y = a.y;
    }

    //移动规则：1上2右3下4左
    //判断能否移动
    public boolean couldMove(int direction) {
        switch (direction) {
            case 1:
                return space.x - 1 >= 0;
            case 2:
                return space.y + 1 < 3;
            case 3:
                return space.x + 1 < 3;
            case 4:
                return space.y - 1 >= 0;
        }
        return false;
    }

    //移动函数
    public Node move(int direction) {
        Node temp = new Node(map, this, this.getDepth() + 1, this.space);
        switch (direction) {
            case 1:
                temp.swap(new Pair(this.space.x - 1, this.space.y));
                break;
            case 2:
                temp.swap(new Pair(this.space.x, this.space.y + 1));
                break;
            case 3:
                temp.swap(new Pair(this.space.x + 1, this.space.y));
                break;
            case 4:
                temp.swap(new Pair(this.space.x, this.space.y - 1));
                break;
        }
        return temp;
    }

    //按九宫格格式输出
    public void print() {
        for (int curs[] : map) {
            for (int cur : curs) {
                System.out.print(cur + " ");
            }
            System.out.println();
        }
    }

    //获取路线中的状态 ArrayList
    public ArrayList<int[][]> printRoute() {
        ArrayList<int[][]> route = new ArrayList<>();
        int step = 0;
        Node temp = this;
        while (temp != null) {
            route.add(0, temp.getMap());
            System.out.println("=========== " + step++ + " step ===========");
            temp.print();
            temp = temp.getParent();
        }
        return route;
    }

    //求f(n) = g(n)+h(n);初始化状态信息
    public void init(Node target) {
        int temp = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] != target.getMap()[i][j])
                    temp++;
            }
        }
        this.setMisposition(temp);
        if (this.getParent() == null) {
            this.setDepth(0);
        } else {
            this.depth = this.parent.getDepth() + 1;
        }
        this.setEvaluation(this.getDepth() + this.getMisposition());
    }

    public int isContains(ArrayList<Node> open){
        for(int i=0;i<open.size();i++){
            if(Algorithm.equal(open.get(i).getMap(), getMap())){
                return i;
            }
        }
        return -1;
    }

    public void operation(ArrayList<Node> open, ArrayList<Node> close, Node parent, Node target) {
        if (this.isContains(close) == -1) {
            int position = this.isContains(open);
            if (position == -1) {
                this.parent = parent;
                this.init(target);
                int i;
                for(i=0;i< open.size();i++){
                    if(this.getEvaluation()<open.get(i).getEvaluation()){
                        break;
                    }
                }
                open.add(i,this);
            } else {
                if (this.getDepth() < open.get(position).getDepth()) {
                    open.remove(position);
                    this.parent = parent;
                    this.init(target);
                    int i;
                    for(i=0;i< open.size();i++){
                        if(this.getEvaluation()<open.get(i).getEvaluation()){
                            break;
                        }
                    }
                    open.add(i,this);
                }
            }
        }
    }
}