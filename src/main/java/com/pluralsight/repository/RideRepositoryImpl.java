package com.pluralsight.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pluralsight.repository.util.RideRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Ride> getRides() {

//		return jdbcTemplate.query("select * from ride",
//				(rs, i) ->
//					new Ride(rs.getInt("id"), rs.getString("name"),
//							rs.getInt("duration")));
		return jdbcTemplate.query("select * from ride", new RideRowMapper());
	}

	@Override
	public Ride createRide(Ride ride) {
//		jdbcTemplate.update("insert into ride (name, duration) values(?,?)",
//				ride.getName(), ride.getDuration());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO ride (name, duration) VALUES(?,?)", new String[]{"id"});
			ps.setString(1, ride.getName());
			ps.setInt(2, ride.getDuration());
			return ps;
		}, keyHolder);
		
		Number id = keyHolder.getKey();
		return getRide(id.intValue());
	}

	public Ride getRide(Integer id) {
		return jdbcTemplate.queryForObject("select * from ride where id = ?", new RideRowMapper(), id);
	}

}
