package com.pluralsight.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Ride> getRides() {

		return jdbcTemplate.query("select * from ride",
				(rs, i) ->
					new Ride(rs.getInt("id"), rs.getString("name"),
							rs.getInt("duration")));
	}

	@Override
	public Ride createRide(Ride ride) {
		jdbcTemplate.update("insert into ride (name, duration) values(?,?)",
				ride.getName(), ride.getDuration());

		
		return null;
	}

}
