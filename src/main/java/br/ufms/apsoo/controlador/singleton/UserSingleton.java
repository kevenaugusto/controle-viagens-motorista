package br.ufms.apsoo.controlador.singleton;

import br.ufms.apsoo.controlador.model.Usuario;

public class UserSingleton {

    private static volatile UserSingleton instance;

    public Usuario usuario;

    private UserSingleton(Usuario usuario) {
        this.usuario = usuario;
    }

    private static UserSingleton getInstance() {
        UserSingleton userSingleton = instance;
        if (userSingleton != null) {
            return userSingleton;
        }
        synchronized(UserSingleton.class) {
            if (instance == null) {
                instance = new UserSingleton(null);
            }
            return instance;
        }
    }

    public static boolean isUser() {
        return getInstance().usuario != null;
    }

    public static void clearUser() {
        getInstance().usuario = null;
    }

    public static Usuario getUser() {
        return getInstance().usuario;
    }

    public static void setUser(Usuario usuario) {
        getInstance().usuario = usuario;
    }

}
