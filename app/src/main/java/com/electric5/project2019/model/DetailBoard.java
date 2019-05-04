package com.electric5.project2019.model;

public class DetailBoard {
    public static int id;

    //기본 메인에 떠야하는 것
    private String BoardName;
    private String Subject;
    private String WriterName;

    //상세 보기
    private String BoardText;
    private String Date;
    private String uri;


    public DetailBoard(int id, String boardName, String Subject, String WriterName, String BoardText, String Date , String uri) {
        this.id=id;
        this.BoardName = boardName;
        this.Subject=Subject;
        this.WriterName=WriterName;
        this.BoardText = BoardText;
        this.Date =Date;
        this.uri= uri;
    }

    public static int getId() {
        return id;
    }
    public static void setId(int id) {
        DetailBoard.id = id;
    }

    public String getBoardName() {
        return BoardName;
    }
    public void setBoardName(String boardName) {
        BoardName = boardName;
    }

    public String getSubject() {
        return Subject;
    }
    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getWriterName() {
        return WriterName;
    }
    public void setWriterName(String writerName) {
        WriterName = writerName;
    }

    public String getBoardText() { return BoardText; }
    public void setBoardText(String boardText) { BoardText = boardText; }

    public String getDate() { return Date; }
    public void setDate(String date) { Date = date; }

    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }

}

