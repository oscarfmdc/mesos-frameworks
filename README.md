# Apache Mesos Framework Example

This is an example for a simple Apache Mesos Framework

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

This code is ready to run in the vagrant environment setup that can be found here:
https://github.com/apache/incubator-myriad/tree/master/vagrant/libvirt/mesos

### Installing

```
gradle build

scp run-framework-mesos.sh vagrant@192.168.121.84:/home/vagrant

scp run-executor-mesos.cmd vagrant@192.168.121.84:/home/vagrant

for i in 84 131 167 150 208; do scp build/libs/mesos-framework-example-1.0-SNAPSHOT.jar vagrant@192.168.121.$i:/tmp; done

vagrant ssh mesos-m1

sh run-framework-mesos.sh mesos-m1 run-executor-mesos.cmd
```

## Built With

* [Gradle](https://gradle.org/) - Dependency Management

## Acknowledgments

* https://github.com/opencredo/mesos_framework_demo