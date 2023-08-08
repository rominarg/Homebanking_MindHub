package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountsRepository extends JpaRepository <Account, Long>{
    List <Account> findById(long Id);
}



