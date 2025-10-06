package com.frankmoley.lil;

import java.math.BigDecimal;
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

    }
}
