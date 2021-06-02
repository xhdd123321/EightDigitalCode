package Main;

import java.util.ArrayList;

public class Node{
    private int[][] map = new int[3][3];    // 当前九宫格
    private int space;     //空格位置
    private Node parent;    //父节点
    private int depth;     //当前深度

    public int getDepth(){
        return this.depth;
    }

    public Node getParent() {
        return parent;
    }

    public int[][] getMap(){
        return map;
    }
    public void setMap(int[][] map){
        this.map = map;
        this.space = getZeroPostion();
    }

    private int getZeroPostion(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(map[i][j] == 0){
                    return i*10+j;
                }
            }
        }
        return 0;
    }

    //无参构造
    public Node(){}

    public Node(int[][] map, Node parent, int depth, int space){
        for(int i = 0; i < 3; i++){
            System.arraycopy(map[i], 0, this.map[i], 0, 3);
        }
        this.space = space;
        this.depth = depth;
        this.parent = parent;
    }

    //获取唯一编码
    public int getCode(){
        int code = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                code = code * 10 + map[i][j];
            }
        }
        return code;
    }

    //判断是否达到目标状态
    public boolean isEnd(Node end){
        return this.getCode() == end.getCode();
    }

    //统计逆序数之和
    private int getReverseNum(){
        int[] num = new int[9];
        int index = 0;
        for(int[] curs : map){
            for(int cur : curs){
                num[index++] = cur;
            }
        }
        int res = 0;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < i; j++){
                if(num[i] != 0 && num[j] > num[i]){
                    res++;
                }
            }
        }
        return res;
    }

    //根据逆序数的关系判断是否可解
    public boolean isSolvable(Node target) {
        //判断逆序数奇偶性是否相同
        return ((this.getReverseNum() + target.getReverseNum()) & 1) == 0;
    }

    //交换坐标space和a
    public void swap(int a){
        this.map[space/10][space%10] = this.map[a/10][a%10];
        this.map[a/10][a%10] = 0;
        this.space = a;
    }

    //移动规则：1上2右3下4左
    //判断能否移动
    public boolean couldMove(int direction){
        switch (direction){
            case 1: return space/10 - 1 >= 0;
            case 2: return space%10 + 1 < 3;
            case 3: return space/10 + 1 < 3;
            case 4: return space%10 - 1 >= 0;
        }
        return false;
    }

    //移动函数
    public Node move(int direction) {
        Node temp = new Node(map, this, this.getDepth() + 1, this.space);
        switch (direction){
            case 1: temp.swap(space - 10);break;
            case 2: temp.swap(space + 1);break;
            case 3: temp.swap(space + 10);break;
            case 4: temp.swap(space - 1);break;
        }
        return temp;
    }

    //按九宫格格式输出
    public void print() {
        for(int[] curs : map){
            for (int cur : curs){
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
        while(temp != null) {
            route.add(0,temp.getMap());
            System.out.println("=========== " + step++ + " step ===========");
            temp.print();
            temp = temp.getParent();
        }
        return route;
    }
}
