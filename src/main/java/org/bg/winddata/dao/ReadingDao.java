package org.bg.winddata.dao;

import org.bg.winddata.domain.Reading;

public interface ReadingDao {
    void addReading(Reading reading) throws RuntimeException;
    boolean existsByDateTime(Reading reading) throws RuntimeException;
}
