import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileQueu {

    private RandomAccessFile rf = null;

    private FileChannel channel = null;

    private final String filePath = "/home/dextop/data/";


    public void fileWrite(String str, FileList fileList) throws IOException, InterruptedException {

        String fileName = filePath + "srv."+System.currentTimeMillis();

        File file = new File(fileName);
        file.createNewFile();

        rf = new RandomAccessFile(fileName, "rw");
        channel = rf.getChannel();

        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, str.length());

        map.position(0);
        map.put(str.getBytes());

        close();

        fileList.AddQueu(file);

    }


    public void fileRead(String threadName, File file, ReadHandler readHandler) throws IOException{
        if(file != null) {
            rf = new RandomAccessFile(file, "rw");
            channel = rf.getChannel();


            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, (int) rf.length());

            byte[] data = new byte[(int) rf.length()];
            map.get(data);

            System.out.println(threadName +"::" + file.getAbsolutePath() + "#########################");
            //System.out.println(new String(data));
            readHandler.getHandler(data);

            close();

            file.delete();
        }
    }

    public void close() throws IOException{
        if(rf != null ) rf.close();
        if(channel != null) channel.close();

        //if(is != null) is.close();
        //if(br != null) br.close();
    }

    public String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
        return dateFormat.format(new Date(System.currentTimeMillis()));
    }

}
