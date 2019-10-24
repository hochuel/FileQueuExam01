import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadHandlerProcess implements ReadHandler{
    @Override
    public void put(Object obj) {

    }

    @Override
    public Object get() {
        return null;
    }

    @Override
    public void setHandler(byte[] datas) {

        ByteArrayInputStream is = null;
        BufferedReader br = null;

        try {
            is = new ByteArrayInputStream(datas);
            br = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line = br.readLine()) != null) {

                System.out.println("getHandler data :" + line);

            }
        }catch(IOException ex){
            ex.printStackTrace();
        }finally {
            try {
                is.close();
                br.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }




    }

}
