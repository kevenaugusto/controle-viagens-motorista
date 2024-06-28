package br.ufms.apsoo.controlador.singleton;

import br.ufms.apsoo.controlador.model.Gerente;

public class ManagerSingleton {

    private static volatile ManagerSingleton instance;

    public Gerente gerente;

    private ManagerSingleton(Gerente gerente) {
        this.gerente = gerente;
    }

    private static ManagerSingleton getInstance() {
        ManagerSingleton managerSingleton = instance;
        if (managerSingleton != null) {
            return managerSingleton;
        }
        synchronized (ManagerSingleton.class) {
            if (instance == null) {
                instance = new ManagerSingleton(null);
            }
            return instance;
        }
    }

    public static boolean isGerente() {
        return getInstance().gerente != null;
    }

    public static void clearGerente() {
        getInstance().gerente = null;
    }

    public static Gerente getGerente() {
        return getInstance().gerente;
    }

    public static void setGerente(Gerente gerente) {
        getInstance().gerente = gerente;
    }

}
