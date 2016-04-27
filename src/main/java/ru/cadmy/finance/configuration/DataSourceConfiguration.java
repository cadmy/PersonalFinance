package ru.cadmy.finance.configuration;

import java.util.Properties;

import javax.sql.DataSource;

/**
 * Created by Cadmy on 27.04.2016.
 */
public interface DataSourceConfiguration {
    DataSource dataSource();

    Properties additionalProperties();
}
