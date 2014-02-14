package uz.micros;

import uz.micros.devices.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SettingsManager {

    private final Notifier ntf;

    public SettingsManager(Notifier notifier) {
        ntf = notifier;
    }

    Iterable<Device> getDevices() {
        Iterable<Device> devs = getFormFile();

        return devs != null ? devs : getStandardDevs();
    }

    private Iterable<Device> getFormFile() {

        Path path = Paths.get("station.settings");

        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            return parseDevs(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Iterable<Device> parseDevs(List<String> lines) {
        ArrayList<Device> devs = new ArrayList<Device>();

        for (String s : lines) {
            if (!s.startsWith("#")) {
                Device d = createDev(s);
                if (d != null) devs.add(d);
            }
        }

        return devs;
    }

    private Device createDev(String s) {
        try {
            String[] parts = s.split(" ");

            if (parts.length == 0) return null;

            Class c = Class.forName("uz.micros.devices." + parts[0]);

            Constructor[] ctors = c.getConstructors();

            if (ctors.length > 0) {

                if (ctors[0].getParameterTypes().length == 1) {
                    Object o = ctors[0].newInstance(ntf);
                    return (Device) o;
                } else if (ctors[0].getParameterTypes().length == 2) {
                    Object o = ctors[0].newInstance(parts[1], ntf);
                    return (Device) o;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Iterable<Device> getStandardDevs() {
        ArrayList<Device> devs = new ArrayList<Device>();

        devs.add(new PressureSensor(ntf));
        devs.add(new VoltageSensor(ntf));
        devs.add(new TemperatureSensor(ntf));

        devs.add(new Turbine("Left", ntf));
        devs.add(new Turbine("Right", ntf));

        return devs;
    }

}
