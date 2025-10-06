package com.frankmoley.lil;

import java.util.List;
import java.util.Optional;

import com.frankmoley.lil.data.dao.ServiceDao;
import com.frankmoley.lil.data.entity.Service;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ServiceDao serviceDao = new ServiceDao();
        
        System.out.println("*** SERVICES ***");
        System.out.println("*** GET ALL");
        List<Service> services = serviceDao.getAll();
        services.forEach(System.out::println);

        System.out.println("*** GET ONE");
        Optional<Service> service = serviceDao.getOne(services.get(0).getServiceId());
        System.out.println(service.get());
    }
}
