# Flink Architecture

- **Storage**:
  - can be integrated with these filesystems: Local, HDFS, S3.
  - can be integrated with databases: MongoDb and HBase.
  - can be integrated with streaming services: Kafka, Flume, RabbitMq.
- **Deploy**
  - local JVM: can run in local machine as single mode
  - cluster standalone/yarn: can run on multiple machine as a single cluster.
  - cloud GCE/EC2 etc
- **Flink Runtime**: its the core for stream processing.
- **Abstraction APIs**:
  - DataSet: batch processing (java, scala, python)
  - DataStream: stream processing (java, scala)
- **Other libraries**:
  - Table API: relational queries in structural queries. Supports both batch and streaming programs For: join, group by.
  - GELLY API: for graph processing
  - FLINK ML API: for machine learnings
- Interactive data analysis using zeeplin library.Zeeplin is a visualization tool.
