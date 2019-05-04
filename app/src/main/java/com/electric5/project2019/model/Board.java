package com.electric5.project2019.model;

public class Board {
    public static int id;
    private String BoardName; // 글 제목
    private String Subject; // 분류
    private String WriterName; // 작성자 명

    public Board(int id, String BoardName, String Subject, String WriterName) {
        this.id = id;
        this.BoardName = BoardName;
        this.Subject=Subject;
        this.WriterName = WriterName;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getBoardName() {
        return BoardName;
    }
    public void setBoardName(String BoardName) {
        this.BoardName = BoardName;
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
    public void setWriterName(String WriterName) {
        this.WriterName = WriterName;
    }
}
