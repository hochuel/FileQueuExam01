public class FileQueuMain {


    public FileQueu fileQueu = null;

    private static FileQueuMain instance = new FileQueuMain();

    public FileQueuMain(){
        if(instance == null) {

            fileQueu = FileQueu.getInstance();
            fileQueu.setReadHandler(new ReadHandlerProcess());

        }
    }

    public static FileQueuMain getInstance(){
        return instance;
    }

}
