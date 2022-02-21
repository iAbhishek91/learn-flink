# Flink programming model

- **Source**(third party): flink read from a data source (Either a batch file or a stream like - Kafka, flume, or Socket)
- **Operation or Transformation**: these are operation on dataset.
- **Sink**(third party): place where we dump the process data. For example: database, Memory, HDFS, S3 etc

## How flink connects with Source and Sink

Flink has bunch of connectors to connects with source and sink.

## Example

**REFER the image**, this is an example where we want to count the number of worked which starts with 'n'.

- nodes can be on same machine or on different systems.
- The data are split into "Block".
- Each "block" is processed by a "node", and it outputs a intermediate result(dataset or datastream) in memory.
- The next "node" reads from the memory of the previous "node".
  - In disaster scenario, if "node-a1" is deleted, a new node is created by Flink. Flink knows which dataset to work on and what is the operation.
- Each block is a dataset. "Node-X1" produces a dataset, which is consumed by "Node-X2", and saves dataset in memory which is consumed by the next node.
