package com.aslam.mycontact.prompt_engine_opt.dao_layer.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AppRepository extends JpaRepository<App,Integer> {

@Query("select app from App app where app.appName=?1")
Optional<App> findByAppName(String appName);

boolean existsByAppName(String appName);

}
