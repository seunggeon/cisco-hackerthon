package com.cisco.hackerthon.mqtt;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StayData {
    private String deviceId;
    private LocalDateTime startTime;
    private int frequency;
    private int duration;
    public StayData() {
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
