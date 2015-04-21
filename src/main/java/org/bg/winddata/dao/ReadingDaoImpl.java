package org.bg.winddata.dao;

import org.bg.winddata.domain.Reading;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class ReadingDaoImpl extends HibernateDaoSupport implements ReadingDao {

    public void addReading(Reading reading) throws RuntimeException {
        try {
            getSessionFactory().getCurrentSession().saveOrUpdate(reading);
            getSessionFactory().getCurrentSession().flush();
            getSessionFactory().getCurrentSession().clear();
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
