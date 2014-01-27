package uz.micros.modules;

import uz.micros.CameraHost;
import uz.micros.PluginEx;

import java.util.Date;

public class MXXModule implements PluginEx {
    @Override
    public void send(String code) {

        System.out.println("Registererd: " +
                new Date() + " " + code);
    }

    public boolean allow(String code, CameraHost host){
        boolean res = !code.contains("x");

        if (!res && host != null){
            byte[] photo = host.takePhoto();
            ///
        }

        return res;
    }
}
