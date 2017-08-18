package com.keessi.web.mapper;

import com.keessi.web.entity.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

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
