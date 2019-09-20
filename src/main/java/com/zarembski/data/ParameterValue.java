package com.zarembski.data;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class ParameterValue {
    private String tag;
    private String masterValue;
    private List<SlaveValues> slaveValuesList = Lists.newArrayList();
}
