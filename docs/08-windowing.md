# Windowing

Window is treated as heart of any stream processing.

Windows split the data stream into bucket of finite size over which computations can be applied.

Windows is created as soon as first element arrives, and window closed based on a condition.

Condition can be: time in seconds passed etc

There are **two type of windows**

- **pre-defined** windows
  - tumbling window
    - time based window
    - once the time is passed, Flink will emits the output of that particular window.
    - as data enters the window it will be processed, this means that each window will have different amount of window.
    - next window starts when previous one is complete(based on time).
  - sliding window
    - time based window
    - only difference with tumbling window is that, you can have overlapping window.
    - amount of overlap is constant and known as window sliding time, which is configurable.
  - session window
  - global window
- **custom** window

## Key and Non Key stream

**Key stream**:

- data stream is keyed by using keyBy operation: for example: data for month of july, data for month of august
- window() - window assigner defines how entities are assigned to windows.
- *Window assigners* can be pre-defined built in or define a custom window assigners by extending the *WindowAssigner* class.

**Non-Keystream**:

- where data are not grouped or keyed.
- we use windowAll() predefined method of WindowAssigner class is used.

## What is time in Flink

There are various aspect of time, what Flink considers.

There are three main stages in terms of stream processing. **Source** >> **Flink ingestion** >> **Processing**

**Processing time**:

- We are considering processing stage here.
- System clock of the machine is used.
- this is simplest notion of time
- requires no co-ordination b/w stream and machine
- best performance and low latency
- less suitable for distributed environment as each system will have different time.

**Event time**:

- This is the time at which event occurred on source stage.
- event time is embedded within each record.
- consistent and deterministic results regardless of order.
- timestamp is embedded in each record.
- however, shows latency while waiting out-of-order events.
- Example: event window set to 1 hour >> each window will contain all records of that hour timestamp regardless of order they arrive.

**Ingestion time**:

- ingestion is the time when an event enters flink(seconds step in the flow)
- each record gets source's current timestamp.
- All time based operations refer to that timestamp.
- cannot handle out-of-order events or late data.

## Triggers

Trigger determines when a window is ready to be processed by window function.

All window assigners comes with a default triggers assigned to them.

We can define our own trigger by implementing **Trigger** interface.

## Evictors

Its an optional concept which may por may not used based on the use case.

Evictor is used to remove elements from a window after the trigger fires and before and/or after the window function is applied.

**lifecycle of a window**:

window created               >>    Trigger         >> Window function              >>  result
.window(), .windowAll()            .trigger()         reduce, fold, aggregate etc

                                                >>  evictor >>  window func     >> evictor 

Evictors are again of two type:

- pre defined
- custom:
  - void evictBefore(Iterable<TimestampedValue<T>> elements, int size, W window, EvictorContext evictorContext)
  - void evictAfter(Iterable<TimestampedValue<T>> elements, int size, W window, EvictorContext)
