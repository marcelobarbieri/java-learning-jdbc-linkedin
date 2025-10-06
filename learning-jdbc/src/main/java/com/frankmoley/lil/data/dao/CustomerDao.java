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

import com.frankmoley.lil.data.entity.Customer;
import com.frankmoley.lil.data.util.DatabaseUtils;

public class CustomerDao  implements Dao<Customer,UUID> {

    private static final Logger LOGGER = Logger.getLogger(CustomerDao.class.getName());
    
    private static final String GET_ALL = "select customer_id, first_name, last_name, email, phone, address from wisdom.customers";
    private static final String GET_BY_ID = "select customer_id, first_name, last_name, email, phone, address from wisdom.customers where customer_id = ?";
    private static final String CREATE = "insert into wisdom.customers (customer_id, first_name, last_name, email, phone, address) values(?,?,?,?,?,?)";
    private static final String UPDATE = "update wisdom.customers set first_name = ?, last_name = ?, email = ?, phone = ?, address = ? where customer_id = ?";
    private static final String DELETE = "delete from wisdom.customers where customer_id = ?";
    private static final String GET_ALL_PAGE = "select customer_id, first_name, last_name, email, phone, address from wisdom.customers order by last_name, first_name limit ? offset ?";

    @Override
    public Customer create(Customer entity) {
        UUID customerId = UUID.randomUUID();
        Connection connection = DatabaseUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(CREATE);            
            statement.setObject(1,customerId);
            statement.setObject(2, entity.getFirstName());
            statement.setObject(3, entity.getLastName());
            statement.setObject(4, entity.getEmail());
            statement.setObject(5, entity.getPhone());
            statement.setObject(6, entity.getAddress());            
            
            statement.execute();
            connection.commit();
            statement.close();
        } catch (SQLException e1) {
            try {
                connection.rollback();
            } catch (SQLException e2) {
                DatabaseUtils.handleSQLException("CustomerDao.create.rollback", e2, LOGGER);
            }
            DatabaseUtils.handleSQLException("CustomerDao.create", e1, LOGGER);
        }
        Optional<Customer> customer = this.getOne(customerId);
        if (!customer.isPresent())
            return null;
        return customer.get();
    }

    @Override
    public void delete(UUID id) {
        Connection connection = DatabaseUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setObject(1, id);
            statement.executeUpdate();
            connection.commit();
            statement.close();
        } catch (SQLException e1) {
            try {
                connection.rollback();
            } catch (SQLException e2) {
                DatabaseUtils.handleSQLException("CustomerDao.delete.rollback", e2, LOGGER);
            }
            DatabaseUtils.handleSQLException("CustomerDao.delete", e1, LOGGER);
        }          
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        Connection connection = DatabaseUtils.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL);
            customers = this.processResultSet(rs);
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("CustomerDao.getAll", e, LOGGER);
        }
        return customers;
    }

    public List<Customer> getAllPaged(int pageNumber, int limit) {
        List<Customer> customers = new ArrayList<>();
        Connection connection = DatabaseUtils.getConnection();
        int offset = ((pageNumber -1) * limit);
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_PAGE)) {
            statement.setInt(1,limit);
            statement.setInt(2,offset);
            ResultSet rs = statement.executeQuery();
            customers = this.processResultSet(rs);
            rs.close();
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("CustomerDao.getAll", e, LOGGER);
        }
        return customers;
    }

    @Override
    public Optional<Customer> getOne(UUID id) {
        try (PreparedStatement statement = DatabaseUtils.getConnection().prepareStatement(GET_BY_ID)) {
            statement.setObject(1,id); // non zero-based, replace ? from GET_BY_ID
            ResultSet rs = statement.executeQuery();
            List<Customer> customers = this.processResultSet(rs);
            if (customers.isEmpty()) 
                return Optional.empty();
            return Optional.of(customers.get(0));
        } catch (SQLException e) {
            DatabaseUtils.handleSQLException("CostumerDao.getOne", e, LOGGER);
        }
        return Optional.empty();
    }

    @Override
    public Customer update(Customer entity) {
        Connection connection = DatabaseUtils.getConnection();
        
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPhone());
            statement.setString(5, entity.getAddress());
            statement.setObject(6, entity.getCustomerId());
            
            statement.execute();
            connection.commit();
            statement.close();
        } catch (SQLException e1) {
            try {
                connection.rollback();
            } catch (SQLException e2) {
                DatabaseUtils.handleSQLException("CustomerDao.update.rollback", e2, LOGGER);
            }
            DatabaseUtils.handleSQLException("CustomerDao.update", e1, LOGGER);
        }
        return this.getOne(entity.getCustomerId()).get();
    }

    private List<Customer> processResultSet(ResultSet rs) throws SQLException {                

        List<Customer> customers = new ArrayList<>();

        while(rs.next()) {
            Customer costumer = new Customer();
            costumer.setCustomerId((UUID)rs.getObject("customer_id"));
            costumer.setFirstName(rs.getString("first_name"));
            costumer.setLastName(rs.getString("last_name"));
            costumer.setEmail(rs.getString("email"));
            costumer.setPhone(rs.getString("phone"));
            costumer.setAddress(rs.getString("address"));
            customers.add(costumer);
        }
        return customers;
    }       

}
