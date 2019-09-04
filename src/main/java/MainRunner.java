import org.apache.mesos.*;
import org.apache.mesos.Protos.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainRunner {

    private static final String frameworkName = "mesos-framework-example";
    private static final String executorName = "executor-example-name";
    private static final String remoteExecutorPath = "/tmp/mesos-framework-example-1.0-SNAPSHOT.jar";
    private static String command = "";

    private static FrameworkInfo getFrameworkInfo() {
        FrameworkInfo.Builder builder = FrameworkInfo.newBuilder();
        builder.setFailoverTimeout(120000);
        builder.setUser("");
        builder.setName(frameworkName);
        return builder.build();
    }

    private static CommandInfo.URI getUri() {
        CommandInfo.URI.Builder uriBuilder = CommandInfo.URI.newBuilder();
        uriBuilder.setValue(remoteExecutorPath);
        uriBuilder.setExtract(false);
        return uriBuilder.build();
    }

    private static CommandInfo getCommandInfo() {
        CommandInfo.Builder cmdInfoBuilder = Protos.CommandInfo.newBuilder();
        cmdInfoBuilder.addUris(getUri());
        cmdInfoBuilder.setValue(command);
        return cmdInfoBuilder.build();
    }

    private static ExecutorInfo getExecutorInfo() {
        ExecutorInfo.Builder builder = ExecutorInfo.newBuilder();
        builder.setExecutorId(Protos.ExecutorID.newBuilder().setValue(executorName));
        builder.setCommand(getCommandInfo());
        builder.setName(executorName);
        builder.setSource("java");
        return builder.build();
    }

    private static void runFramework(String mesosMaster, String commandFile) {

        try {
            command = new Scanner(new File(commandFile)).useDelimiter("\\Z").next();
            System.out.println(command);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scheduler scheduler = new ExampleScheduler(getExecutorInfo());
        MesosSchedulerDriver driver = new MesosSchedulerDriver(scheduler, getFrameworkInfo(), mesosMaster);

        int status = driver.run() == Protos.Status.DRIVER_STOPPED ? 0 : 1;

        // Ensure that the driver process terminates.
        driver.stop();

        // For this test to pass reliably on some platforms, this sleep is
        // required to ensure that the SchedulerDriver teardown is complete
        // before the JVM starts running native object destructors after
        // System.exit() is called. 500ms proved successful in test runs,
        // but on a heavily loaded machine it might not.
        // and its associated tasks via the Java API and wait until their
        // teardown is complete to exit.
        // TODO: Inspect the status of the driver
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("I was interrupted!");
            e.printStackTrace();
        }
        System.exit(status);
    }

    public static void main(String[] args) {
        runFramework(args[0], args[1]);
    }
}
