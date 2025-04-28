package com.school.library.Java.LibraryU.model;

import java.util.List;

public class LoanRequest {
    private Long studentId;
    private List<Long> bookIds;

    public LoanRequest() {
    }

    public LoanRequest(Long studentId, List<Long> bookIds) {
        this.studentId = studentId;
        this.bookIds = bookIds;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }
}
