package com.example.gpaie.Service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gpaie.Repository.HeureSupplRepository;
import com.example.gpaie.Service.heureSupplService;
@Service
public class HeureSupplServiceImpl implements heureSupplService{
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private HeureSupplRepository avantageRepository;
}
