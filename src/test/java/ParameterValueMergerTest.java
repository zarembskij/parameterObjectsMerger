import com.google.common.collect.Lists;
        import com.zarembski.data.ParameterValue;
import com.zarembski.data.SlaveValues;
import com.zarembski.merger.ParameterValueMerger;
import org.junit.Assert;
import org.junit.Test;

        import java.util.List;

public class ParameterValueMergerTest {

    //TDD was here

    ParameterValueMerger merger = new ParameterValueMerger();

    @Test
    public void whenMergeObjectThenReturnList() {

        ParameterValue p1 = ParameterValue.builder().tag("001").masterValue("val_001").build();
        ParameterValue p2 = ParameterValue.builder().tag("002").masterValue("val_002").build();
        ParameterValue p3 = ParameterValue.builder().tag("003").masterValue("val_003").build();
        ParameterValue p3_new = ParameterValue.builder().tag("003").masterValue("val_003_edited").build();
        ParameterValue p4 = ParameterValue.builder().tag("004").masterValue("val_004").build();
        ParameterValue p5 = ParameterValue.builder().tag("005").masterValue("val_005").build();

        List<ParameterValue> parameterOnDevice = Lists.newArrayList(p1,p2,p3,p4);
        List<ParameterValue> newParameterList = Lists.newArrayList(p1,p2,p3_new,p5);
        List<ParameterValue> mergedList = merger.merge(parameterOnDevice, newParameterList);

        Assert.assertTrue(mergedList.size() == 5);
        Assert.assertTrue(mergedList.containsAll(Lists.newArrayList(p1,p2,p3_new,p4,p5)));
    }

    @Test
    public void mergeObjectWithSlaveValues() {

        SlaveValues slave1 = SlaveValues.builder().profile(1L).slaveValue("1_profile").build();
        SlaveValues slave2 = SlaveValues.builder().profile(2L).slaveValue("2_profile").build();
        SlaveValues slave2_new = SlaveValues.builder().profile(2L).slaveValue("2_profile_new").build();
        SlaveValues slave3 = SlaveValues.builder().profile(3L).slaveValue("3_profile").build();


        ParameterValue p1 = ParameterValue.builder().tag("001").masterValue("val_001").build();
        ParameterValue p2 = ParameterValue.builder().tag("002").masterValue("val_002").build();
        ParameterValue p3 = ParameterValue.builder().tag("003").masterValue("val_003").slaveValuesList(Lists.newArrayList(slave1, slave2)).build();
        ParameterValue p3_new = ParameterValue.builder().tag("003").masterValue("val_003_edited").slaveValuesList(Lists.newArrayList(slave2_new, slave3)).build();
        ParameterValue p4 = ParameterValue.builder().tag("004").masterValue("val_004").build();
        ParameterValue p5 = ParameterValue.builder().tag("005").masterValue("val_005").build();

        ParameterValue p3_merged = ParameterValue.builder().tag("003").masterValue("val_003_edited").slaveValuesList(Lists.newArrayList(slave1, slave2_new, slave3)).build();


        List<ParameterValue> parameterOnDevice = Lists.newArrayList(p1,p2,p3,p4);
        List<ParameterValue> newParameterList = Lists.newArrayList(p1,p2,p3_new,p5);
        List<ParameterValue> mergedList = merger.merge(parameterOnDevice, newParameterList);

        Assert.assertTrue(mergedList.size() == 5);
        Assert.assertTrue(mergedList.containsAll(Lists.newArrayList(p1,p2,p3_merged,p4,p5)));
    }
}
