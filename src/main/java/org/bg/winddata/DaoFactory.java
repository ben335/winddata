package org.bg.winddata;

import org.bg.winddata.dao.ReadingDao;

public class DaoFactory {
    private ReadingDao readingDao;

    public ReadingDao getReadingDao() {
        return readingDao;
    }

    public void setReadingDao(ReadingDao readingDao) {
        this.readingDao = readingDao;
    }
}
