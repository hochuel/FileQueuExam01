import java.io.File;

public class FileReadThread extends Thread {

    private FileQueuMain fileQueuMain = null;

    public FileReadThread(FileQueuMain fileQueuMain){
        this.fileQueuMain = fileQueuMain;
    }

    public void run(){

        while(true){
            try {
                int cnt = fileQueuMain.fileQueu.getFileCnt();
                while(cnt > 0){
                    String data = (String)fileQueuMain.fileQueu.getData();
                    if(data != null) {
                        System.out.println(data);
                    }
                }
                this.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
