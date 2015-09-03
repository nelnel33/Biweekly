package experimental;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Nelnel33
 */
public class BasicPathfinding {

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    
    public static LinkedList<MatrixPoint> points= new LinkedList();
    
    public static final int[][] map = {
        {0,1,0,0},
        {0,1,0,1},
        {0,1,1,1},
        {0,1,1,1}
    };
    
    /**
     * Finds shortest path from current point to destination point
     * @param pathId - represents what integer constitute a tile
     * @param map - integer array that represents a map of any n by n dimension
     * @param curr - current point
     * @param dest - destination point
     * @return - returns shortest path if dest exists on map, else returns null.
     */
    public static LinkedList<MatrixPoint> pathfinder(int pathId, int[][] map, MatrixPoint curr, MatrixPoint dest){
        ArrayList<MatrixPoint> open = new ArrayList();
        LinkedList<MatrixPoint> closed = new LinkedList();
        open.add(curr);
        
        while(!curr.equals(dest)){
            System.out.println(curr);
            MatrixPoint[] tiles = new MatrixPoint[4];                
            try{
                tiles[UP] = MatrixPoint.create(curr.row-1, curr.col, map.length, map[curr.row-1].length);
                tiles[UP].g = curr.g+1;
                tiles[UP].parent = curr;
            }catch(ArrayIndexOutOfBoundsException aoe){
                tiles[UP] = null;
            }
            
            try{
                tiles[DOWN] = MatrixPoint.create(curr.row+1,curr.col, map.length, map[curr.row+1].length);
                tiles[DOWN].g = curr.g+1;
                tiles[DOWN].parent = curr;
            }catch(ArrayIndexOutOfBoundsException aoe){
                tiles[DOWN] = null;
            }
            
            tiles[LEFT] = MatrixPoint.create(curr.row, curr.col-1, map.length, map[curr.row].length);
            if(tiles[LEFT] != null){
                tiles[LEFT].g = curr.g+1;
                tiles[LEFT].parent = curr;
            }
            
            tiles[RIGHT] = MatrixPoint.create(curr.row,curr.col+1, map.length, map[curr.row].length);
            if(tiles[RIGHT] != null){
                tiles[RIGHT].g = curr.g+1;
                tiles[RIGHT].parent = curr;
            }

            
            //adds up,down,left,right to list if it is not null, is on the map, and isn't in closedlist or openlist
            for(int k=0;k<tiles.length;k++){
                MatrixPoint t = tiles[k];
                if(t != null && t.getOnMap(map) == pathId && !t.isInList(closed) && !t.isInList(open)){                    
                    open.add(t);                    
                }
            }
            
            open.remove(curr);
            closed.add(curr);
            
            //finds the smallest F value and sets indexOfSmallest to corresponding index;
            int smallest = -1;
            int indexOfSmallest = 0;
            for(int i=0;i<open.size();i++){
                if(smallest == -1){
                    smallest = open.get(i).getF(dest);
                }
                if(open.get(i).getF(dest) < smallest){
                    smallest = open.get(i).getF(dest);
                    indexOfSmallest = i;
                }
            } 
            
            try{
                curr = open.get(indexOfSmallest);
            }catch(IndexOutOfBoundsException ioe){
                break;
            }
            
        }
        
        if(curr.equals(dest)){
            closed.add(curr);
            return traverseAndMakePath(closed);
        }
        else{
            return null;
        }
    }
    
    /**
     * Traverses through last element in closed list to determine the shortest path.
     * @param closed - list containing all possible tiles.
     * @return 
     */
    public static LinkedList<MatrixPoint> traverseAndMakePath(LinkedList<MatrixPoint> closed){
        LinkedList<MatrixPoint> path = new LinkedList();
        MatrixPoint curr = closed.get(closed.size()-1);
        System.out.println(curr);
        Stack<MatrixPoint> stack = new Stack();
        while(curr != null){
            stack.push(curr);
            curr = curr.parent;
        }        
        
        while(!stack.empty()){
            path.add(stack.pop());
        }
        
        return path;
    }
    
    public static class MatrixPoint{        
        public int row;
        public int col;
        public int g;
        public MatrixPoint parent;
        public MatrixPoint(int row, int col){
            this.row = row;
            this.col = col;
        }
        public static MatrixPoint create(int row, int col, int mapWidth, int mapHeight){
            try{
                if(row<0 || col<0 || row>=mapWidth || col>=mapHeight){
                    return null;
                }
                else{
                    return new MatrixPoint(row,col);
                }
            } catch(ArrayIndexOutOfBoundsException aoe){
                return null;
            }
        }
        public boolean isInList(List<MatrixPoint> list){
            for(int i=0;i<list.size();i++){
                if(this.equals(list.get(i))){
                    return true;
                }
            }
            return false;
        }
        public int getOnMap(int[][] map){
            try{
                return map[row][col];//map[col][row];
            }catch(ArrayIndexOutOfBoundsException aoe){
                return -1;//random number that isn't a pathID
            }
        }
        public int getH(MatrixPoint dest){
            int h = Math.abs(dest.row-this.row)+Math.abs(dest.col-this.col);
            return h;
        }
        public int getF(MatrixPoint dest){
            return g+getH(dest);
        }
        public boolean equals(MatrixPoint other){
            return this.row == other.row && this.col == other.col;
        }
        @Override
        public String toString(){
            return "("+row+", "+col+")";
        }
    }    
    
    public static void main(String[] args){
        System.out.println(pathfinder(1,map, new MatrixPoint(0,1),new MatrixPoint(3,3)));
    }   
}

