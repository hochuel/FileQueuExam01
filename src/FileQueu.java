import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileQueu {

    private FileList fileList = null;
    private ReadHandler readHandler = null;

    public FileQueu() throws InterruptedException{
        this.fileList = new FileList();
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

        fileList.AddQueu(file);

    }


    public void fileRead(String threadName, File file) throws IOException{

        RandomAccessFile rf = null;
        FileChannel channel = null;

        if(file != null) {
            rf = new RandomAccessFile(file, "r");
            channel = rf.getChannel();


            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, (int) rf.length());

            byte[] data = new byte[(int) rf.length()];
            map.get(data);

            //System.out.println(threadName +"::" + file.getAbsolutePath() + "#########################");
            //System.out.println(new String(data));
            readHandler.setHandler(data);

            channel.close();
            rf.close();

            file.delete();
        }
    }


    public void fileRead(File file) throws IOException{
        fileRead("", file);
    }

    public String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
        return dateFormat.format(new Date(System.currentTimeMillis()));
    }

}
