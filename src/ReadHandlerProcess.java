import java.io.*;
import java.util.concurrent.LinkedBlockingDeque;

public class ReadHandlerProcess extends LinkedBlockingDeque implements ReadHandler{
    @Override
    public void put(Object obj) {
        this.add(obj);
    }

    @Override
    public synchronized Object get() {
        if(this.size() > 0) {
            return this.removeFirst();
        }else{
            return null;
        }

    }

    @Override
    public void setHandler(byte[] datas, File file) {

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


            file.delete();

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
