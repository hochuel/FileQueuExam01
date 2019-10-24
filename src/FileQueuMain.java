public class FileQueuMain {

    public static void main(String[] args) throws Exception{



        FileList fileList = new FileList();

        FileQueu fileQueu = new FileQueu();


        FileWriteThread fwt = new FileWriteThread(fileList, fileQueu);
        fwt.start();



        int threadCnt = 3;
        FileReadThread [] frt = new FileReadThread[threadCnt];

        for(int i = 0; i < threadCnt; i++) {
            frt[i] = new FileReadThread(fileList, fileQueu);
            frt[i].setReadHandler(new ReadHandlerProcess());
            frt[i].start();
        }
    }
}
