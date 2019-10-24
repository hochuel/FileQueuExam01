public class FileWriteThread extends Thread {


    private FileQueuMain fileQueuMain;

    public FileWriteThread(FileQueuMain fileQueuMain){

        this.fileQueuMain = fileQueuMain;
    }


    public void run(){

        int index = 0;
        while(true){
            try {

                String str = "";
                for(int i = 0; i < 10; i++){


                    str += fileQueuMain.fileQueu.getDate()+ " test file data+["+index+"] \r\n";

                    index ++;
                }


                fileQueuMain.fileQueu.fileWrite(str);

                this.sleep(100);


            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
