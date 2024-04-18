package com.cisco.hackerthon.mapper;

import com.cisco.hackerthon.dto.users.KinderDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KinderMapper {

    public KinderDTO findStats(int id);
    public void updateSensor(KinderDTO stayLog);
}