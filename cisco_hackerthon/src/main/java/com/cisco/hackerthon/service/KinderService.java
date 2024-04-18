package com.cisco.hackerthon.service;

import com.cisco.hackerthon.dto.users.KinderDTO;
import com.cisco.hackerthon.mapper.KinderMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class KinderService {
    @Autowired
    private KinderMapper kinderMapper;

    public KinderDTO getStats() {
        KinderDTO stats = kinderMapper.findStats(1);
        return stats;
    }
}
