import java.util.ArrayList;

public class DataList extends ArrayList {

    private static DataList instance;

    public static DataList getInstance(){

        if(instance == null){
            instance = new DataList();
        }

        return instance;
    }


    public void add(String str){
        super.add(str);
    }


}
