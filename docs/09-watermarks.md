# Watermarks

Watermarks are mechanism by which we measure progress of event time in Flink.

Watermarks are kind of transformation with in the flink where we insert the watermark for a record.

```java
DataStream<Tuple2<Long, String>> watermark = data.assignTimestampsAndWatermarks(new AscendingTimestampExtractor<Tuple2<Long, String>>(){
    public long extractorAscendingTimestamp(Tuple2<Long, String> t) {
        return t.f0;
    }
});
```

Watermark declares the amount of event time passed in the stream.

Basically watermark are created after a pre defined time declared as

```java
ExecutionConfig.setAutoWatermarkInterval(10)
```

Watermark play a crucial role in out-of-order events.

## Late Elements

Late elements are the elements that arrive after the watermark has crossed the element's timestamp value.

In other words, elements which have a timestamp within a watermark say (10:00AM and 10:10 AM), with a event time as 10:03AM. If this element enters Flink after the watermark 10:10AM its known as later elements.

### how flink deals with late elements

By default late elements are dropped - Allowed lateness is 0.

There is a value called **allowed lateness**. This is a time defined by which a element can be late before it is dropped.

Window without late element is already processed, however if there are any late element and allowed lateness is > 0, then the window will fire again with updated results.

For re-processing window, due to late elements, flink keeps track of window state until allowed lateness time expires. If any elements enters the stream which are within the allowed lateness time, the window is re-processed along with updated state.

Also you might have guessed, since the window is processed two time, there will be multiple results for the same computation, user need to eliminate the duplicate result.

## Side output

In case a element enters very late, then window is done with processing. However Flink provides another way of processing those data as well.

The order is as below:

```java
result = input
    .keyBy() // creating key input
    .window() // creating window from the keyed input
    .allowedLateness(<time>)
    .sideOutputLateData(lateOutputTag)
    .<window transformations>
```

They are processed separately and no window is re-processed.

This is done by processing a late element by tag inserted above and processing that stream separately.

```java
DataStream<T> lateStream = result.getSideOutput(lateOutputTag);
```
