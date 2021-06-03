package Main;

import javax.swing.*;
import java.util.ArrayList;

public class Main
{
    private int[][] startMap;
    private int[][] endMap;
    private int flag;
    private long runtime;
    private String information;

    public Main(){
        flag=0;
        startMap=new int[3][3];
        endMap=new int[3][3];
    }

    public int getFlag() {
        return flag;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }
    public long getRuntime() {
        return runtime;
    }
    public void setInformation(String information) {
        this.information = information;
    }
    public String getInformation(){
        return information;
    }
    public void setFlag(String flag) {
        if(flag=="1 宽度优先搜索算法"){
            this.flag = 1;
        }else if(flag=="2 深度优先搜索算法"){
            this.flag=2;
        }else if(flag=="3 A*搜索算法"){
            this.flag=3;
        }
    }
    public void setStartMap(int[][] startMap) {
        this.startMap = startMap;
    }
    public void setEndMap(int[][] endMap) {
        this.endMap = endMap;
    }
    public ArrayList<int[][]> test(){
        Algorithm Algorithm = new Algorithm();
        Node start = new Node();
        Node end = new Node();
        start.setMap(startMap);
        end.setMap(endMap);
        if(start.isSolvable(end)){
            ArrayList<int[][]> res=new ArrayList<>();
            if(flag==1){
                res = Algorithm.bfs(start, end);
                setRuntime(Algorithm.getRuntime());
                setInformation(Algorithm.getInformation());
                return res;
            }else if(flag==2){
                res = Algorithm.dfs(start, end);
                setRuntime(Algorithm.getRuntime());
                setInformation(Algorithm.getInformation());
                return res;
            }else if(flag==3){
                res = Algorithm.Astar(start, end);
                setRuntime(Algorithm.getRuntime());
                setInformation(Algorithm.getInformation());
                return res;
            }
        }else{
            JOptionPane.showMessageDialog(null,"Unsolvable, END!");
            System.out.println("Unsolvable, END!");
        }
        return null;
    }
}
