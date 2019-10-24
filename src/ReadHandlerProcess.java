import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingDeque;

public class ReadHandlerProcess extends LinkedBlockingDeque implements ReadHandler{
    @Override
    public void put(Object obj) {
        this.add(obj);
    }

    @Override
    public Object get() {

        Object obj = this.removeFirst();

        if(obj == null) return null;
        return obj;
    }

    @Override
    public void setHandler(byte[] datas) {

        ByteArrayInputStream is = null;
        BufferedReader br = null;

        try {
            is = new ByteArrayInputStream(datas);
            br = new BufferedReader(new InputStreamReader(is));

            String str = "";
            String line = null;
            while ((line = br.readLine()) != null) {
                str += line+"\r\n";
            }
            this.add(str);
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
