package com.cisco.hackerthon.mqtt;

import com.cisco.hackerthon.dto.users.KinderDTO;
import com.cisco.hackerthon.mapper.KinderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MqttSubscriber {

    @Autowired
    private KinderMapper kinderMapper;
    private Boolean flag = true;
    private int duration = 0;

    //MqttConfig에 있는 MqttPahoMessageDrivenChannelAdapter의존성 주입
    private final MqttPahoMessageDrivenChannelAdapter UWBRangeMessageDrivenChannelAdapter;
    private final Map<Integer, StayData> stayDataMap = new HashMap<>();


    @ServiceActivator(inputChannel = "UWBIdInputChannel")
    public void receiveUWBId(String payload){
//        System.out.println("디바이스 ID : " + payload);
    }
    @ServiceActivator(inputChannel = "UWBRangeInputChannel")
    public void receiveUWBRange(String payload){
        System.out.println("UWB 사이 거리 : " + payload + "m");
        int id = 1;
        updateStayData(payload, id);
    }

    private void updateStayData(String distance, int id) {
        StayData stayData = stayDataMap.get(id);

        if (stayData == null) {
            stayData = new StayData();
            stayData.setStartTime(LocalDateTime.now());
            stayData.setFrequency(0);
            stayDataMap.put(id, stayData);
        }


        // 15cm 이내에 있는 경우
        if (Double.parseDouble(distance) < 0.15) {
            duration = (int) stayData.getStartTime().until(LocalDateTime.now(), ChronoUnit.SECONDS);
            stayData.setDuration(duration);
            System.out.println("체류 시간 : " + duration);

            if (duration > 10 && flag == true)
            {
                stayData.setFrequency(stayData.getFrequency() + 1);
                flag = false;
            }
        }

        if (Double.parseDouble(distance) == 0.22) {
            KinderDTO stayLog = new KinderDTO();
            stayLog.setId(id); // id 값
            stayLog.setFrequency(stayData.getFrequency()); // 체류 시작 시간
            stayLog.setDuration(stayData.getDuration()); // 체류 시간

            flag = true;
            stayData.setStartTime(LocalDateTime.now());

            kinderMapper.updateSensor(stayLog);
//
//            // StayData 초기화
//            stayDataMap.remove(id);
        }
    }
}