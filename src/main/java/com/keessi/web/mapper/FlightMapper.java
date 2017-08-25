package com.keessi.web.mapper;

import com.keessi.web.entity.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

public interface FlightMapper extends CrudRepository<Flight, Long> {
    @Override
    @PreAuthorize("#oauth2.hasScope('read')")
    Iterable<Flight> findAll();

    @PreAuthorize("#oauth2.hasScope('read')")
    Flight findOne(Long id);

    @Override
    @PreAuthorize("#oauth2.hasScope('write')")
    <S extends Flight> S save(S entity);


}
