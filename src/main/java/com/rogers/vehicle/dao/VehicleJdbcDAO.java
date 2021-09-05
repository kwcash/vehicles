package com.rogers.vehicle.dao;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.rogers.vehicle.model.Vehicle;

import java.util.List;
import java.util.Optional;

@Component
public abstract class VehicleJdbcDAO implements DAO<Vehicle> {

    private static final Logger log = LoggerFactory.getLogger(VehicleJdbcDAO.class);
    private JdbcTemplate jdbcTemplate;

    public VehicleJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Maps a row in the database to a Vehicle
     */
    RowMapper<Vehicle> rowMapper = (rs, rowNum) -> {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(rs.getInt("vehicle_id"));
        vehicle.setVin(rs.getString("vin"));
        vehicle.setModel(rs.getString("model"));
        vehicle.setDays(rs.getInt("days"));
        return vehicle;
    };

    @Override
    public List<Vehicle> list() {
        String sql = "SELECT vehicle_id, vin, model, days from vehicle";
        return jdbcTemplate.query(sql,rowMapper);
    }

    // CRUD (Create, Read, Update, Delete)

    @Override
    public void create(Vehicle vehicle) {
        String sql = "insert into vehicle(vin,model,days) values(?,?,?)";
        int insert = jdbcTemplate.update(sql,vehicle.getVin(),vehicle.getModel(),vehicle.getDays());
        if(insert == 1) {
            log.info("New Vehicle Created: " + vehicle.getVin());
        }
    }

    @Override
    public Optional<Vehicle> get(int id) {
        String sql = "SELECT vehicle_id,vin,model,days from vehicle where vehicle_id = ?";
        Vehicle vehicle = null;
        try {
            vehicle = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        }catch (DataAccessException ex) {
            log.info("Vehicle not found: " + id);
        }
        return Optional.ofNullable(vehicle);
    }

    @Override
    public void update(Vehicle vehicle, int id) {
        String sql = "update vehicle set vin = ?, model = ?, days = ? where vehicle_id = ?";
        int update = jdbcTemplate.update(sql,vehicle.getVin(),vehicle.getModel(),vehicle.getDays(),id);
        if(update == 1) {
            log.info("Vehicle Updated: " + vehicle.getVin());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from vehicle where vehicle_id = ?";
        int delete = jdbcTemplate.update(sql,id);
        if(delete == 1) {
            log.info("Vehicle Deleted: " + id);
        }
    }


}
