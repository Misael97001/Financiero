package com.krakedev.financiero.servicios;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;

public class Banco {

    private int ultimoCodigo = 1000;

    public Banco() {
    }

    public int getUltimoCodigo() {
        return ultimoCodigo;
    }

    public void setUltimoCodigo(int ultimoCodigo) {
        this.ultimoCodigo = ultimoCodigo;
    }

    // Crear cuenta
    public Cuenta crearCuenta(Cliente cliente) {
        String codigoStr = ultimoCodigo + "";
        ultimoCodigo++;

        Cuenta cuenta = new Cuenta(codigoStr);
        cuenta.setPropietario(cliente);

        return cuenta;
    }

    // Depositar
    public boolean depositar(double monto, Cuenta cuenta) {
        if (monto <= 0) {
            return false;
        }

        cuenta.setSaldoActual(cuenta.getSaldoActual() + monto);
        return true;
    }

    // Retirar
    public boolean retirar(double monto, Cuenta cuenta) {
        if (monto <= 0 || monto > cuenta.getSaldoActual()) {
            return false;
        }

        cuenta.setSaldoActual(cuenta.getSaldoActual() - monto);
        return true;
    }

    // Transferir
    public boolean transferir(Cuenta origen, Cuenta destino, double monto) {
        boolean retiro = retirar(monto, origen);

        if (retiro) {
            depositar(monto, destino);
            return true;
        }

        return false;
    }
}