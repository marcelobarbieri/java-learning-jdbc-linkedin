package com.frankmoley.lil.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.frankmoley.lil.data.entity.Service;

public class ServiceDao implements Dao<Service,UUID> {

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Service> getOne(UUID id) {
        // TODO Auto-generated method stub
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
