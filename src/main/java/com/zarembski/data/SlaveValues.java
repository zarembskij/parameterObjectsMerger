package com.zarembski.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SlaveValues {
    private Long profile;
    private String slaveValue;
}