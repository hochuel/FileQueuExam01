public class FileQueuMain {


    private FileQueu fileQueu = null;

    private static FileQueuMain instance = new FileQueuMain();

    public FileQueuMain(){
        if(instance != null) {
            try {
                fileQueu = new FileQueu();
                fileQueu.setReadHandler(new ReadHandlerProcess());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public FileQueuMain getInstance(){
        return instance;
    }


    public static void main(String[] args) throws Exception{


        FileQueu fileQueu = new FileQueu();
        fileQueu.setReadHandler(new ReadHandlerProcess());


        FileWriteThread fwt = new FileWriteThread(fileQueu);
        fwt.start();



        int threadCnt = 3;
        FileReadThread [] frt = new FileReadThread[threadCnt];

        for(int i = 0; i < threadCnt; i++) {
            frt[i] = new FileReadThread(fileQueu);
            frt[i].start();
        }
    }
}
