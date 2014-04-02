package uz.micros.network;

public class ClientEvent {
    private ClientEventType type;
    private String data;
    private String name;

    public ClientEventType getType() {
        return type;
    }

    public void setType(ClientEventType type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
