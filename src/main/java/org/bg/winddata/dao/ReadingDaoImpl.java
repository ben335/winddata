package org.bg.winddata.dao;

import org.bg.winddata.domain.Reading;
import org.bg.winddata.util.ObjectUtility;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ReadingDaoImpl extends HibernateDaoSupport implements ReadingDao {

    public void addReading(Reading reading) throws RuntimeException {
        try {
            getSessionFactory().getCurrentSession().saveOrUpdate(reading);
            getSessionFactory().getCurrentSession().flush();
            getSessionFactory().getCurrentSession().clear();
        } catch (RuntimeException e) {
            logger.error("Exception when adding record to the database from the DAO: ", e);
            throw e;
        }
    }

    public boolean existsByDateTime (Reading reading) {
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Reading.class);
        criteria.add(Restrictions.eq("dateTime", reading.getDateTime()));
        criteria.add(Restrictions.eq("stationId", reading.getStationId()));
        //TODO check station id to ensure that the reading exists for the correct station
        List<Reading> readings = criteria.list();
        if (readings.isEmpty()){
            logger.info("No previous same readings found");
            return false;
        }else {
            logger.info("Previous same reading found for station: " + reading.getStationId() + " in reading: " + ObjectUtility.printReading(reading));
            return true;
        }

    }
}
