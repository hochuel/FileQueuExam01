import java.util.List;

public class Process extends Thread {


    private DataList list;

    public Process(DataList list){
        this.list = list;
    }
    @Override
    public void run() {


        while(true){

            System.out.println("list.size() ::" + list.size());

            if(list != null && list.size() > 0) {


               System.out.println("list.size() ::" + list.size());
               for(int i = 0; i < list.size(); i++){
                   System.out.println("Process ::" + list.get(i));
               }

            }
        }
    }
}
