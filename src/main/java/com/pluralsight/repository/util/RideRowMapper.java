package com.pluralsight.repository.util;

import com.pluralsight.model.Ride;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kamil
 */
public class RideRowMapper implements RowMapper<Ride>{

    @Override
    public Ride mapRow(ResultSet rs, int i) throws SQLException {
        return new Ride(rs.getInt("id"),rs.getString("name"),
                rs.getInt("duration"));
    }
}
