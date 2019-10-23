import java.io.File;

public class FileReadThread extends Thread {

    private FileList fileList;
    private FileQueu fileQueu;


    private ReadHandler readHandler;

    public FileReadThread(FileList fileList, FileQueu fileQueu){
        this.fileList = fileList;
        this.fileQueu = fileQueu;
    }


    public void setReadHandler(ReadHandler readHandler) {
        this.readHandler = readHandler;
    }

    public void run(){

        while(true){
            try {
                File file = (File)fileList.getQueu();
                if(file != null) {
                    fileQueu.fileRead(this.getName(), file, readHandler);
                }
                this.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
