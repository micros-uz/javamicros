package uz.micros1;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GuardSystem {

    ArrayList<Plugin> plugins = new ArrayList<Plugin>();

    public void run() {
        Input.print("Guard System 1.0 Micros Gmbh " + '\u00A9');

        Input.print("Enter user code");
        String s = Input.getLine();
        boolean res = true;

        SearchPlugins();

        for(Plugin p : plugins)
            res &= p.validate(s);

        Input.print("User is " + (res ? "allowed" : "prohibited") + " to enter");
    }

    private void SearchPlugins() {

        try {
            Class[] classes = Loader.getClasses(this.getClass().getPackage().getName());

            for(Class cl : classes)
                if (Plugin.class.isAssignableFrom(cl) &&
                        !cl.isInterface()){
                    plugins.add((Plugin)cl.getConstructor().newInstance());
                }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
