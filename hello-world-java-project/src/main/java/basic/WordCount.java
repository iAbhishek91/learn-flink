package basic;

import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
// to use dataset abstraction in the code
import org.apache.flink.api.java.DataSet;
// to set an execution environment for the program
import org.apache.flink.api.java.ExecutionEnvironment;
// to use TUPLE type
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;

public class WordCount
{
    public static void main(String[] args) throws Exception
    {
        // Below THREE line are almost common in all FLINK program
        // is the context where the program is executed
        // this can be local(in current JVM) or remote(on a cluster)
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        // in case of stream processing, we StreamExecutionEnvironment.getExecutionEnvironment()

        // help the reading and parsing program arguments.
        // for example, we will pass input and output PATH of file in the arguments
        // from CLI
        ParameterTool params = ParameterTool.fromArgs(args);

        // the parameters passed as argument are to be passed to every node of flink
        // hence we need to set these as global parameter in the execution context.
        env.getConfig().setGlobalJobParameters(params);

        DataSet<String> text = env.readTextFile(params.get("input"));

        DataSet<String> filtered = text.filter(new FilterFunction<String>()

        {
            public boolean filter(String value)
            {
                return value.startsWith("N");
            }
        });
        DataSet<Tuple2<String, Integer>> tokenized = filtered.map(new Tokenizer());

        DataSet<Tuple2<String, Integer>> counts = tokenized.groupBy(new int[] { 0 }).sum(1);
        if (params.has("output"))
        {
            counts.writeAsCsv(params.get("output"), "\n", " ");

            env.execute("WordCount Example");
        }
    }

    public static final class Tokenizer
            implements MapFunction<String, Tuple2<String, Integer>>
    {
        public Tuple2<String, Integer> map(String value)
        {
            return new Tuple2(value, Integer.valueOf(1));
        }
    }
}
