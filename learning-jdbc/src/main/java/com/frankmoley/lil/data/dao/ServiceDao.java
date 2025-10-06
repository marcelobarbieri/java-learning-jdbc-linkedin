package com.frankmoley.lil.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import javax.naming.spi.DirStateFactory.Result;

import com.frankmoley.lil.data.entity.Service;
import com.frankmoley.lil.data.util.DatabaseUtils;

public class ServiceDao implements Dao<Service,UUID> {

    private static final Logger LOGGER = Logger.getLogger(ServiceDao.class.getName());
    private static final String GET_ALL = "select service_id, name, price from wisdom.services";
    private static final String GET_BY_ID = "select service_id, name, price from wisdom.services where service_id = ?";

    @Override
    public Service create(Service entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(UUID id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Service> getAll() {
        List<Service> services = new ArrayList<>();
        Connection connection = DatabaseUtils.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL);
            services = this.processResultSet(rs);
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("ServiceDao.getAll", e, LOGGER);
        }
        return services;
    }

    @Override
    public Optional<Service> getOne(UUID id) {
        try (PreparedStatement statement = DatabaseUtils.getConnection().prepareStatement(GET_BY_ID)) {
            statement.setObject(1,id); // non zero-based, replace ? from GET_BY_ID
            ResultSet rs = statement.executeQuery();
            List<Service> services = this.processResultSet(rs);
            if (services.isEmpty()) 
                return Optional.empty();
            return Optional.of(services.get(0));
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("ServiceDao.getOne", e, LOGGER);
        }
        return Optional.empty();
    }

    @Override
    public Service update(Service entity) {
        // TODO Auto-generated method stub
        return null;
    }


    private List<Service> processResultSet(ResultSet rs) throws SQLException {                

        List<Service> services = new ArrayList<>();

        while(rs.next()) {
            Service service = new Service();
            service.setServiceId((UUID)rs.getObject("service_id"));
            service.setName(rs.getString("name"));
            service.setPrice(rs.getBigDecimal("price"));

            services.add(service);
        }
        return services;
    }    
}
