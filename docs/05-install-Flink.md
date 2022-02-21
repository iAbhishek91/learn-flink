# Flink installation

Flink is started in many way like standalone, cluster mode etc.

## Prerequisite

Flink works in linux box, else its recommended to use virtual box in windows

- Linux
- Java latest (min 8 for now)

## Download

- Download the latest binary *named as flink-<version>-bin-scala_2.11.tgz*.

```sh
# extract somewhere
cd ~
tar xvf flink-1.14.3-bin-scala_2.11.tgz
```

## Start

```sh
cd ~/flink-1.14.3

# Start local cluster
./bin/start-cluster.sh
# Starting cluster.
# Starting standalone session daemon on host CA-LAP-466.
# Starting task executor daemon on host CA-LAP-466.
```

Flink has started and running as a background process on "localhost:8081"

## Stop

```sh
# Stop local cluster
./bin/stop-cluster.sh
```
