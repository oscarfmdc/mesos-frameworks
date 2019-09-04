gradle build

scp build/libs/mesos-framework-example-1.0-SNAPSHOT.jar vagrant@192.168.121.84:/tmp
scp run-framework-mesos.sh vagrant@192.168.121.84:/home/vagrant
scp run-executor-mesos.cmd vagrant@192.168.121.84:/home/vagrant

scp build/libs/mesos-framework-example-1.0-SNAPSHOT.jar vagrant@192.168.121.131:/tmp
scp build/libs/mesos-framework-example-1.0-SNAPSHOT.jar vagrant@192.168.121.167:/tmp
scp build/libs/mesos-framework-example-1.0-SNAPSHOT.jar vagrant@192.168.121.150:/tmp
scp build/libs/mesos-framework-example-1.0-SNAPSHOT.jar vagrant@192.168.121.208:/tmp

Desde build:
sh run-framework-mesos.sh mesos-m1 run-executor-mesos.cmd