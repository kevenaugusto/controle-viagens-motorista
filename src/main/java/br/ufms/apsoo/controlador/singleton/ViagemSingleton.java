package br.ufms.apsoo.controlador.singleton;

import br.ufms.apsoo.controlador.model.Viagem;

public class ViagemSingleton {

    private static volatile ViagemSingleton instance;

    public Viagem viagem;

    private ViagemSingleton(Viagem viagem) {
        this.viagem = viagem;
    }

    private static ViagemSingleton getInstance() {
        ViagemSingleton viagemSingleton = instance;
        if (viagemSingleton != null) {
            return viagemSingleton;
        }
        synchronized(ViagemSingleton.class) {
            if (instance == null) {
                instance = new ViagemSingleton(null);
            }
            return instance;
        }
    }

    public static boolean isViagem() {
        return getInstance().viagem != null;
    }

    public static void clearViagem() {
        getInstance().viagem = null;
    }

    public static Viagem getViagem() {
        return getInstance().viagem;
    }

    public static void setViagem(Viagem viagem) {
        getInstance().viagem = viagem;
    }

}
