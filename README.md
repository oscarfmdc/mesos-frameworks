gradle build

scp run-framework-mesos.sh vagrant@192.168.121.84:/home/vagrant

scp run-executor-mesos.cmd vagrant@192.168.121.84:/home/vagrant

for i in 84 131 167 150 208; do scp build/libs/mesos-framework-example-1.0-SNAPSHOT.jar vagrant@192.168.121.$i:/tmp; done

Desde build:

sh run-framework-mesos.sh mesos-m1 run-executor-mesos.cmd