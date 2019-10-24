import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args){

        int writeThreadCnt = 5;
        int readThreadCnt = 1;

        ExecutorService writeExecutorService = Executors.newFixedThreadPool(writeThreadCnt);
        ExecutorService readExecutorService = Executors.newFixedThreadPool(readThreadCnt);

        FileQueuMain fileQueuMain = FileQueuMain.getInstance();



        FileWriteThread[] fwt = new FileWriteThread[writeThreadCnt];
        for(int i = 0; i < writeThreadCnt; i++) {
            fwt[i] = new FileWriteThread(fileQueuMain);
            writeExecutorService.execute(fwt[i]);
        }



        FileReadThread [] frt = new FileReadThread[readThreadCnt];
        for(int i = 0; i < readThreadCnt; i++) {
            frt[i] = new FileReadThread(fileQueuMain);
            readExecutorService.execute(frt[i]);
        }

    }



    /*

    public static void main(String[] args) throws Exception{
        FileQueuMain fileQueuMain = FileQueuMain.getInstance();

        //fileQueuMain.fileQueu.fileWrite("This is test");


        int cnt = fileQueuMain.fileQueu.getSize();
        for(int i = 0; i < cnt; i++) {
            System.out.println(fileQueuMain.fileQueu.get());
        }
    }
*/
}
