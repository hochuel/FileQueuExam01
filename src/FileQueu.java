import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileQueu {

    private static FileQueu instance = new FileQueu();

    private FileList fileList = null;
    private ReadHandler readHandler = null;


    public static FileQueu getInstance(){
        return instance;
    }

    public FileQueu(){
        if(instance == null) {
            try {
                this.fileList = new FileList();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


    public void setReadHandler(ReadHandler readHandler) {
        this.readHandler = readHandler;
    }

    private final String filePath = "/home/dextop/data/";


    public void fileWrite(String str) throws IOException, InterruptedException {
        RandomAccessFile rf = null;

        FileChannel channel = null;

        String fileName = filePath + "srv."+System.currentTimeMillis();

        File file = new File(fileName);
        file.createNewFile();

        rf = new RandomAccessFile(fileName, "rw");
        channel = rf.getChannel();

        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, str.length());

        map.position(0);
        map.put(str.getBytes());

        channel.close();
        rf.close();

        //System.out.println(fileName +" Create File...");

        fileList.AddFileQueu(file);

    }


    public synchronized void fileRead(String threadName, File file) throws IOException{

        RandomAccessFile rf = null;
        FileChannel channel = null;

        if(file != null && file.isFile()) {
            rf = new RandomAccessFile(file, "r");
            channel = rf.getChannel();


            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, (int) rf.length());

            byte[] data = new byte[(int) rf.length()];
            map.get(data);

            //System.out.println(threadName +"::" + file.getAbsolutePath() + "#########################");
            //System.out.println(new String(data));
            readHandler.setHandler(data, file);

            channel.close();
            rf.close();

        }
    }


    public void fileRead(File file) throws IOException{
        fileRead("", file);
    }

    public String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
        return dateFormat.format(new Date(System.currentTimeMillis()));
    }


    public Object getData(){

        Object result = null;
        try{
            File file = (File)fileList.getFileQueu();
            if(file != null && file.isFile()) {
                //System.out.println("file.getAbsolutePath() :" + file.getAbsolutePath());
                fileRead(file);


                result = readHandler.get();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }


    public int getFileCnt(){
        return fileList.getQueuSize();
    }


    public void fileDelete(){
        readHandler.fileDelete();
    }

}
