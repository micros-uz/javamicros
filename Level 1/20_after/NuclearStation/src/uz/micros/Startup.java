package uz.micros;

public class Startup implements Notifier {
    public void run() throws InterruptedException {

        SettingsManager sm = new SettingsManager(this);

        CommandProcessor cp = new CommandProcessor(sm.getDevices());

        cp.run();

        System.out.println("Exit!");

        System.exit(0);
    }

    private Notifier getNotifier() {
        return this;
    }

    @Override
    public void send(String s) {
        Input.print(s);
    }
}


/*
        Path path = Paths.get("file.txt");
        try {
            List<String> d = Files.readAllLines(path, StandardCharsets.UTF_8);
            Class c = Class.forName("uz.micros.devices.Sensor");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
*/
