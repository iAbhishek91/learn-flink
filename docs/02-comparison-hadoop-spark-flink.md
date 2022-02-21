# Comparing between Hadoop and Spark and Flink

## Hadoop

- Batch processing framework.
- From disk (Basic workflow below: input is consumed from disk, passed on to mapper func, the output is written to disk, then reducer function reads the data from disk and spits the output to disk) *NOTE there can be n number of mapper function in realtime, hence there are multiple read write to a disk*.

Input file  >>  Mapper function >> Intermediate mapper output  >> Reducer function  >> Output file
  HDFS(disk)                                 HDFS(disk)                                 HDFS(disk)

- difficult to program as each operations need to be defined.
- iterative processing is not so efficient(via chain mapper and chain reducer).
- abstraction is not supported.
- separate tool for machine learning is required.

## Spark and Flink

- Both Batch and stream processing engine
- In memory (much faster than hadoop) *Note: initial read and final write is unavoidable, however all the intermediate read/write to disk are done in-memory*

Input file  >>    In Memory operations >> Output file
HDFS(disk)           memory               HDFS(disk)

- Easy to program as there are lots of high-level operators(union, map, flat map, join) in it.
- supports iterative processing via in memory computation and with help of high level operators.
- supports abstraction: Spark - RDD | Flink dataflows
- have inbuilt machine learning engine: spark- Mlib, Flink- FlinkML

## Diff b/w Spark and Flink

- Spark was developed in 2012 for mainly batch processing using RDD.
- Spark is not a true real time processing. Its near to real time processing framework. RDD was used for steam processing as well which was not so efficient.
- Spark streaming computation mode is based on **microbatching** *microbatching is nothing but breaking the stream of data into small batches and the processing those*.
- Spark in implemented in Scala - provides APIs for Java, Python and R.
- Spark do not have memory manager of its own. User need to do memory management separately. Hence Spark gets out of memory very often.
- Spark uses DAG(directed acyclic graph) as its execution style and submits it to RDD.

- Flink is a true real time processing.
- Flink introduces the concept of **window** (based on time windows is created, if timeline is 5 mitns then window is created from current time to current time + 5 mitns) and **checkpoint**(notate where the stream have left processing and resume/reprocess the stream later) in the data stream.
- Flink is implemented in Java - provide supports for Java, Scala, Python and R.
- Flink have its automatic memory manager of its own. however user can still configure it if required. Due to this Flink rarely goes into out of memory scenario.
- Flink uses controlled cyclic dependency graph as its execution engine.
- Flink has a optimizer, i.e, the program that we write will be optimized and then executed by Flink
