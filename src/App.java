public class App {
    public static void main(String[] args) throws Exception{


        FileQueuMain fileQueuMain = FileQueuMain.getInstance();

        FileWriteThread fwt = new FileWriteThread(fileQueuMain);
        fwt.start();


        int threadCnt = 1;
        FileReadThread [] frt = new FileReadThread[threadCnt];

        for(int i = 0; i < threadCnt; i++) {
            frt[i] = new FileReadThread(fileQueuMain);
            frt[i].start();
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
