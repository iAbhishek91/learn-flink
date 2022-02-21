# Transformation program

Transformation program are function which are applied on dataset or datastream.

Few transformation program are unique to dataset or datastream, where as few are implemented in the same way.

*All the transformation will result in dataset/datastream*.

## execute

This triggers execution of the program

```java
env.execute("Word count example")
```

## readFile(fileInputFormat, path, watchtype, interval, pathFilter)

This used in case DataStream only.

It reads the file based on the provided fileInputFormat, watchtype and scans the file periodically for any new data in every (x)ms *mentioned in the interval*, where value of x is equal to interval value in milliseconds.

Two type of **WatchType** mode, they are enum constants:

**FileProcessingMode,PROCESS_CONTINUOUSLY**:

- source is monitored periodically which comes in a specified path
- period is decided by the interval(in milliseconds)

**FileProcessingMode.PROCESS_ONCE**:

- reads the data
- create a checkpoints, it will never visit the checkpoint again
- internally it will read the data until all the files are read.
- flink will create a new transaction for reading the next checkpoint
- this is not used mostly as it leads to slow recovery - refer DataStream API - Data Source & sinks of datastream API

PathFilter will discard file from the folder/directory.

### how internally this works

Flink split the reading process into two sub-tasks:

- Monitoring
  - Scan the path based on the watchType
  - divide into splits
  - Assign splits to readers.
- Actual reading
  - done by multiple readers
  - reader run parallelly
  - each slit read by only 1 reader.

## socketTextStream

Reads data from socket(open at a port), elements can be separated by a delimiter.

## addSource

This is to add a custom data source outside of Flink example: Kafka, flume, Twitter API etc.

## readTextfile(path)

Text file is read line by line and each data is saved as String in a dataset.

Mention the DataSet Type: String

```java
DataSet<String> text= env.readTextFile(params.get("input"));
```

Both for DataSet and DataStream

## readCsvFile(path)

CSV file is read row by row and and each row is saved as tuple in a dataset.

Mention the DataSet Type: Tuples (datatype in Flink, contain fixed length and consist of set of fields, vary from Tuple2 to Tuple25)

```java
DataSet<Tuple2<Integer,String>> text= env.readCsvFile(params.get("input"));
```

## readFileOfPrimitives(path, Class)

```txt
3242425678
3242423
5345
34
43465
324
```

If we read this data using "readTextFile", then each line is saved as string. Then we need to add another transformation to type cast the data from String to Integer.

## readFileOfPrimitives(path, delimiter, Class)

```txt
3242425678,23423,232
3242423,23423,4545
5345,23432,66
```

Reads each line from the file in the from of class mentioned in arguments using a delimiter.

## readHadoopFile(FileInputFormat, Key, Value, path)

Reading a file from HDFS location.

## readSequenceFile(key, value, path)

Reads sequence file.

## writeAsText()

Writes output linewise, each line as a string

## writeAsCsv(path)

writes CSV file, Row and fields delimiter are configurable.

```java
// counts is the last data set that we wish to output
counts.writeAsCsv(params.get("output"), "\n", " ")
```

Can be used for both DataSet and DataStream

## print()

Prints the output to console. Output is written as String by internally calling toString() method.

Both for DataSet and DataStream

## writeUsingOutputFormat()/FileOutputFormat

Writes output as per the provided FileOutputFormat.

Both for DataSet and DataStream

## writeToSocket

writes elements to a sockets according to a SerializationSchema.

Specific for DataStream

## addSink

to Add a custom data sink outside of Flink, Ex: Kafka, Flume etc using connectors.

## filter(takes an interface that implements FilterFunction)

Filter function takes interface as an argument, where we define the function for filter. This is similar to passing a callable function in javascript.

```java
DataSet<String> filtered = text.filter(
    new FilterFunction<String>(){
        public boolean filter(String value) {
            return value.startsWith("N")
        }
    }
);

DataSet<String> filtered = text.filter(new MyFilter());

public static final class MyFilter implements FilterFunction<String> {
    public boolean filter(String value) {
        return value.startsWith("N");
    }
}
```

## map(takes an interface that implements MapFunction)

Map will transform the data to another form. It takes single input and outputs a single element.

```java
DataSet<Tuple2<String, Integer>> tokenized = filtered.map(new Tokenizer());

public static final class Tokenizer implements MapFunction<String, Tuple2<String, Integer>> {
    public Tuple2<String, Integer> map(String value) {
        return new Tuple2<String, Integer>(value, 1)
    }
}

```

## flatMap(takes an interface that implements MapFunction)

Only difference b/w map and flatmap is that it can take single input and provide **multiple outputs**.

```txt
Noman Joyce Nipun Joe
Noman Noman Nipun Adam
Nikhil Fliyos Joe Adam
```

if we want to do word count of the above data *instead of single name per row* logic is bit different.

```java
DataSet<String> text = env.readTextFile(params.get("input"));

DataSet<Tuple2<String, Integer>> tokenized = text.flatMap(new Tokenizer());

public static final class Tokenizer implements flatMapFunction<String, Tuple2<String, Integer>>{
    public void flatMap(String value, collector<Tuple2<String, Integer>> out) {
        // split the line
        String[] token = value.split(" "); // tokens = [{Noman} {Joyce} {Nipun} {Joe}]
        // emit the pairs
        for (String token : tokens) {
            if (token.length() > 0) {
                out.collect(new Tuple2<String, Integer>(token, 1))
            }
        }
    }
}
```

## groupby

```java
// Note here chaining is used were sum function is applied after groupby.
// although there is no explicit dataset declared Flink will internally create a dataset for groupby,
// which will be passed on to sum transformation function.

// 0 in groupby is the column number where groupby is applied.
// 1 in sum is the column number where sum is applied.
DataSet<Tuple2<String, Integer>> counts = tokenized.groupBy(0).sum(1);
```

## sum

can only be applied on tuple dataset.

## joins

## reduce

## fold

## aggregation

## split

## iterate
