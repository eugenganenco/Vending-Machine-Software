import java.util.HashMap;

public class NumberToCoordinateConverter {
    private final HashMap<Integer,String> rowMap;
    private final HashMap<Integer,String> colMap;

    public NumberToCoordinateConverter(){
        rowMap = new HashMap<>();
        rowMap.put(0,"A");
        rowMap.put(1,"B");
        rowMap.put(2,"C");
        rowMap.put(3,"D");

        colMap = new HashMap<>();
        colMap.put(0,"1");
        colMap.put(1,"2");
        colMap.put(2,"3");
        colMap.put(3,"4");
        colMap.put(4,"5");
        colMap.put(5,"6");
        colMap.put(6,"7");
        colMap.put(7,"8");
    }

    public String getCoordinate(int num){
        return rowMap.get(num/8) + colMap.get(num%8);
    }
}
