import java.io.File;

public class FileReadThread extends Thread {

    private FileList fileList;
    private FileQueu fileQueu;


    public FileReadThread(FileQueu fileQueu){
        this.fileQueu = fileQueu;
    }

    public void run(){

        while(true){
            try {
                File file = (File)fileList.getQueu();
                if(file != null) {
                    fileQueu.fileRead(this.getName(), file);
                }
                this.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
