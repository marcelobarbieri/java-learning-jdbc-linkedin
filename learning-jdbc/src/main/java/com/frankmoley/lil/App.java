package com.frankmoley.lil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.frankmoley.lil.data.dao.CustomerDao;
import com.frankmoley.lil.data.dao.ServiceDao;
import com.frankmoley.lil.data.entity.Customer;
import com.frankmoley.lil.data.entity.Service;


public class App {
    public static void main(String[] args) {
                
        System.out.println("**************************************************");
        System.out.println("*** SERVICES ***");
        System.out.println("**************************************************");

        ServiceDao serviceDao = new ServiceDao();

        System.out.println("*** GET ALL");
        List<Service> services = serviceDao.getAll();
        services.forEach(System.out::println);

        System.out.println("*** GET ONE");
        Optional<Service> service = serviceDao.getOne(services.get(0).getServiceId());
        System.out.println(service.get());

        System.out.println("*** CREATE");
        Service newService = new Service();
        newService.setName("FooBarBaz" + System.currentTimeMillis());
        newService.setPrice(new BigDecimal(4.35));
        newService = serviceDao.create(newService);
        System.out.println(newService);

        System.out.println("*** UPDATE");
        newService.setPrice(new BigDecimal(13.45));
        newService = serviceDao.update(newService);
        System.out.println(newService);

        System.out.println("*** DELETE");
        System.out.println(newService);
        serviceDao.delete(newService.getServiceId());

        System.out.println("*** LIST");
        services.forEach(System.out::println);        

        System.out.println("**************************************************");
        System.out.println("*** CUSTOMERS ***");
        System.out.println("**************************************************");

        CustomerDao customerDao = new CustomerDao();

        System.out.println("*** GET ALL");
        List<Customer> customers = customerDao.getAll();
        customers.forEach(System.out::println);

        System.out.println("*** GET ONE");
        Optional<Customer> customer = customerDao.getOne(customers.get(0).getCustomerId());
        System.out.println(customer.get());

        System.out.println("*** CREATE");
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("FirstName" + System.currentTimeMillis());
        newCustomer.setLastName("LastName" + System.currentTimeMillis());
        newCustomer.setEmail("Email" + System.currentTimeMillis());
        newCustomer.setPhone("Phone" + System.currentTimeMillis());
        newCustomer.setAddress("Address" + System.currentTimeMillis());
        newCustomer = customerDao.create(newCustomer);
        System.out.println(newCustomer);

        System.out.println("*** UPDATE");
        newCustomer.setEmail("Email Updated "  + System.currentTimeMillis());
        newCustomer = customerDao.update(newCustomer);
        System.out.println(newCustomer);

        System.out.println("*** DELETE");
        System.out.println(newCustomer);
        serviceDao.delete(newCustomer.getCustomerId());        

        System.out.println("*** LIST");
        customers.forEach(System.out::println);          
    }
}
