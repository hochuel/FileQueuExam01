# FileQueuExam01
파일큐를 만들어 보았다. 아직 베타 버전이다.

사용방법
FileQueuMain fileQueuMain = FileQueuMain.getInstance();

fileQueuMain.fileQueu.fileWrite("This is test");

System.out.println(fileQueuMain.fileQueu.get());
