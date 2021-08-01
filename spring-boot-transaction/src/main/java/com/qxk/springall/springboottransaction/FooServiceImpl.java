package com.qxk.springall.springboottransaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class FooServiceImpl implements FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FooService self;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        throw new RollbackException();
    }

    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        insertThenRollback();
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class, propagation = Propagation.NESTED)
    public void insertThenRollbackNested() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
//        throw new RollbackException();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void invokeInsertThenRollbackNested() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
        try {
            self.insertThenRollbackNested();
        } catch (RollbackException e) {
            log.error("RollbackException", e);
        }
        throw new RuntimeException();
    }
}
