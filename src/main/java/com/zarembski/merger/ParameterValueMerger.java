package com.zarembski.merger;

import com.google.common.collect.Lists;
import com.zarembski.data.ParameterValue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParameterValueMerger {

    public List<ParameterValue> merge (List<ParameterValue> parameterValueOndevice, List<ParameterValue> newParameterValue) {

        Map<String, ParameterValue> parameterValueMap = parameterValueOndevice.stream().collect(Collectors.toMap(p -> p.getTag(), p -> p));
        newParameterValue.stream().forEach(p -> parameterValueMap.put(p.getTag(), p));


        return Lists.newArrayList(parameterValueMap.values());

    }
}
