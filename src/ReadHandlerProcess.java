import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadHandlerProcess implements ReadHandler{

    private ByteArrayInputStream is = null;
    private BufferedReader br = null;

    @Override
    public void getHandler(byte[] datas) {


        try {
            is = new ByteArrayInputStream(datas);
            br = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line = br.readLine()) != null) {

                System.out.println("getHandler data :" + line);

            }

            is.close();
            br.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }


    }


    public void close() throws IOException{
        is.close();
        br.close();
    }
}
