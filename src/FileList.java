import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.LinkedBlockingDeque;

public class FileList {

    private  int COMPARETYPE_NAME = 0;
    private  int COMPARETYPE_DATE = 1;

    private LinkedBlockingDeque fileQueu = null;


    private File file;
    private File[] files;

    public FileList() throws InterruptedException{
        fileQueu = new LinkedBlockingDeque();


        String path = "/home/dextop/data";
        file = new File(path);
        files = file.listFiles();

        setFileLoad();
    }


    public void setFileLoad() throws InterruptedException{
        File[] fileList = sortFileList(files, COMPARETYPE_DATE);


        for (File tempFile : fileList) {
            if (tempFile.isFile()) {
                AddQueu(tempFile);
            }
        }
    }

    public void AddQueu(Object obj) throws InterruptedException{

        fileQueu.add(obj);

        //notifyAll();

    }

    public Object getQueu() throws InterruptedException{
        if(fileQueu.size() == 0){
            return null;
        }

        File file = (File)fileQueu.removeFirst();


        return file;
    }

    public int getQueuSize(){
        return fileQueu.size();
    }


    public File[] sortFileList(File[] files, final int compareType) {

        Arrays.sort(files,
                new Comparator<Object>() {
                    @Override
                    public int compare(Object object1, Object object2) {

                        String s1 = "";
                        String s2 = "";

                        if (compareType == COMPARETYPE_NAME) {
                            s1 = ((File) object1).getName();
                            s2 = ((File) object2).getName();
                        } else if (compareType == COMPARETYPE_DATE) {
                            s1 = ((File) object1).lastModified() + "";
                            s2 = ((File) object2).lastModified() + "";
                        }


                        return s1.compareTo(s2);

                    }
                });

        return files;
    }



/*

    public static void main(String[] args) {

        FileList fileList = new FileList();


        System.out.println(fileList.getQueuSize());

        while(fileList.getQueuSize() > 0){
            File file = (File)fileList.getQueu();
            System.out.println(file.getAbsolutePath());

        }

        System.out.println(fileList.getQueuSize());

    }
*/
}