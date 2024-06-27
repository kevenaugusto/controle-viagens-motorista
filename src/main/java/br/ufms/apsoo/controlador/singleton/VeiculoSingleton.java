package br.ufms.apsoo.controlador.singleton;

import br.ufms.apsoo.controlador.model.Veiculo;

public final class VeiculoSingleton {

    private static volatile VeiculoSingleton instance;

    public Veiculo veiculo;

    private VeiculoSingleton(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    private static VeiculoSingleton getInstance() {
        VeiculoSingleton veiculoSingleton = instance;
        if (veiculoSingleton != null) {
            return veiculoSingleton;
        }
        synchronized(VeiculoSingleton.class) {
            if (instance == null) {
                instance = new VeiculoSingleton(null);
            }
            return instance;
        }
    }

    public static boolean isVeiculo() {
        return getInstance().veiculo != null;
    }

    public static void clearVeiculo() {
        getInstance().veiculo = null;
    }

    public static Veiculo getVeiculo() {
        return getInstance().veiculo;
    }

    public static void setVeiculo(Veiculo veiculo) {
        getInstance().veiculo = veiculo;
    }

}
