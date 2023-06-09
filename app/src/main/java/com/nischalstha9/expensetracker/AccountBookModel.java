package com.nischalstha9.expensetracker;

public class AccountBookModel {
    private String account_book_name;
    private int account_book_id;
    private int account_book_balance;


    public AccountBookModel(String accountBook_name, int accountBook_id, int accountBook_balance) {
        this.account_book_name = accountBook_name;
        this.account_book_id = accountBook_id;
        this.account_book_balance = accountBook_balance;
    }

    public String getAccountBookName() {
        return account_book_name;
    }
    public void setCourse_name(String course_name) {
        this.account_book_name = course_name;
    }

    public int getAccountBookId() {
        return account_book_id;
    }
    public void setAccountBookId(int accountBook_id) {
        this.account_book_id = accountBook_id;
    }
    public int getAccountBookBalance() {
        return account_book_balance;
    }
    public void setAccountBookBalance(int accountBook_balance) {
        this.account_book_balance = accountBook_balance;
    }
}
