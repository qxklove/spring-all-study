package com.qxk.springall.springboottransaction;

public interface FooService {
    void insertRecord();
    void insertThenRollback() throws RollbackException;
    void invokeInsertThenRollback() throws RollbackException;

    void insertThenRollbackNested() throws RollbackException;
    void invokeInsertThenRollbackNested() throws RollbackException;
}
