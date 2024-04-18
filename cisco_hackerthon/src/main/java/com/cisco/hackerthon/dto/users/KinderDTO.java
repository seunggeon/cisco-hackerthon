package com.cisco.hackerthon.dto.users;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KinderDTO {
    private int id;
    @Nullable
    private int duration;
    @Nullable
    private int frequency;
}
