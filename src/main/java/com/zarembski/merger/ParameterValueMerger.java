package com.zarembski.merger;

import com.google.common.collect.Lists;
import com.zarembski.data.ParameterValue;
import com.zarembski.data.SlaveValues;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParameterValueMerger {

    public List<ParameterValue> merge (List<ParameterValue> parameterValueOndevice, List<ParameterValue> newParameterValue) {

        Map<String, ParameterValue> parameterValueMap = parameterValueOndevice.stream().collect(Collectors.toMap(p -> p.getTag(), p -> p));
        newParameterValue.stream().forEach(p -> parameterValueMap.put(p.getTag(), p));
        parameterValueOndevice.stream().filter(p -> p.getSlaveValuesList() != null).filter(p -> !p.getSlaveValuesList().isEmpty()).forEach(p -> mergeSlaveValues(parameterValueMap, p));
        return Lists.newArrayList(parameterValueMap.values());
    }

    private void mergeSlaveValues(Map<String, ParameterValue> parameterValueMap, ParameterValue parameterValueOnDevice) {
        List<SlaveValues> slaveValuesListOnDevice = parameterValueOnDevice.getSlaveValuesList();
        List<SlaveValues> mergedSlaveValueList = parameterValueMap.get(parameterValueOnDevice.getTag()).getSlaveValuesList();
        Map<Long, SlaveValues> slaveValuesMap = slaveValuesListOnDevice.stream().collect(Collectors.toMap(s -> s.getProfile(), s -> s));
        mergedSlaveValueList.stream().forEach(s -> slaveValuesMap.put(s.getProfile(), s));

        ParameterValue newParameterValue = parameterValueMap.get(parameterValueOnDevice.getTag());
        newParameterValue.setSlaveValuesList(Lists.newArrayList(slaveValuesMap.values()));
        parameterValueMap.put(newParameterValue.getTag(), newParameterValue);
    }
}
