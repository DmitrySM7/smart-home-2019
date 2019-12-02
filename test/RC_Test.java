import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.alarm.*;
import ru.sbt.mipt.oop.remotecontrol.RemoteControl;
import static org.junit.Assert.*;

public class RC_Test {
    @Test
    public void TestDangerState() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);
        Alarm alarm = context.getBean(Alarm.class);
        RemoteControl remoteControl = (RemoteControl) context.getBean("remoteControlRealisation");

        remoteControl.onButtonPressed("1");
        assertTrue(alarm.getState() instanceof DangerState);
    }

    @Test
    public void TestActivationState() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);
        Alarm alarm = context.getBean(Alarm.class);
        RemoteControl remoteControl = (RemoteControl) context.getBean("remoteControlRealisation");

        remoteControl.onButtonPressed("C");
        assertTrue(alarm.getState() instanceof ActivationState);
    }

    @Test
    public void TestHallDoorClosed() {
        boolean flag = false;
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);
        SmartHome smartHome = context.getBean(SmartHome.class);
        RemoteControl remoteControl = (RemoteControl) context.getBean("remoteControlRealisation");
        remoteControl.onButtonPressed("2");
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("Hall")) {
                for (Door door : room.getDoors()) {
                    if (door.getStatus()) {
                        flag = true;
                    }
                }
            }
        }
        assertFalse(flag);
    }

    @Test
    public void TestHallLightOn() {
        boolean flag = false;
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);
        SmartHome smartHome = context.getBean(SmartHome.class);
        RemoteControl remoteControl = (RemoteControl) context.getBean("remoteControlRealisation");
        remoteControl.onButtonPressed("D");
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("Hall")) {
                for (Light light : room.getLights()) {
                    if (!light.getStatus()) {
                        flag = true;
                    }
                }
            }
        }
        assertFalse(flag);
    }
}
