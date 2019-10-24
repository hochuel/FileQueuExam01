public class FileWriteThread extends Thread {


    private FileQueu fileQueu;

    public FileWriteThread(FileQueu fileQueu){

        this.fileQueu = fileQueu;
    }


    public void run(){

        int index = 0;
        while(true){
            try {

                String str = "";
                for(int i = 0; i < 10; i++){
                    str += fileQueu.getDate()+ " test file data+["+index+"] \r\n";

                    index ++;
                }


                fileQueu.fileWrite(str);

                this.sleep(100);


            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
