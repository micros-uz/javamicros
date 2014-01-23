package uz.micros1.modules;

import uz.micros1.Plugin;

public class IIVModule implements Plugin {
    @Override
    public String getName() {
        return "IIV sec module V 1.0";
    }

    @Override
    public boolean validate(String data) {
        return true;
    }
}
