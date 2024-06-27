package br.ufms.apsoo.controlador.singleton;

import br.ufms.apsoo.controlador.model.Motorista;

public final class MotoristaSingleton {

    private static volatile MotoristaSingleton instance;

    public Motorista motorista;
    public boolean fromTripForm;

    private MotoristaSingleton(Motorista motorista) {
        this.motorista = motorista;
    }

    private static MotoristaSingleton getInstance() {
        MotoristaSingleton motoristaSingleton = instance;
        if (motoristaSingleton != null) {
            return motoristaSingleton;
        }
        synchronized(MotoristaSingleton.class) {
            if (instance == null) {
                instance = new MotoristaSingleton(null);
            }
            return instance;
        }
    }

    public static boolean isMotorista() {
        return getInstance().motorista != null;
    }

    public static void clearMotorista() {
        getInstance().motorista = null;
    }

    public static Motorista getMotorista() {
        return getInstance().motorista;
    }

    public static void setMotorista(Motorista motorista) {
        getInstance().motorista = motorista;
    }

    public static boolean isFromTripForm() {
        return getInstance().fromTripForm;
    }

    public static void setFromTripForm(boolean fromTripForm) {
        getInstance().fromTripForm = fromTripForm;
    }

}
