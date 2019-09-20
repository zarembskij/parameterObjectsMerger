import com.google.common.collect.Lists;
        import com.zarembski.data.ParameterValue;
        import com.zarembski.merger.ParameterValueMerger;
        import org.junit.Test;

        import java.util.List;

public class ParameterValueMergerTest {

    //TDD was here

    ParameterValueMerger merger = new ParameterValueMerger();

    @Test
    public void whenMergeObjectThenReturnList() {

        List<ParameterValue> parameterValueList = Lists.newArrayList();

        merger.merge(parameterValueList, parameterValueList);
    }
}
