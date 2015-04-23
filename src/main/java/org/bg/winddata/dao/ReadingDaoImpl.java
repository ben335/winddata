package org.bg.winddata.dao;

import org.bg.winddata.domain.Reading;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;


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

    public boolean existsByDateTime (Reading reading) {
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Reading.class);
        criteria.add(Restrictions.eq("dateTime", reading.getDateTime()));
        List<Reading> readings = criteria.list();
        if (readings.isEmpty()){
            return false;
        }else return true;

    }
}
