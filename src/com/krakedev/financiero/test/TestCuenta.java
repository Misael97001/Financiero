package com.krakedev.financiero.test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestCuenta {

    public static void main(String[] args) {

        Cliente cliente1 = new Cliente("1723456789", "Jefferson", "Chiluisa");

        Banco banco = new Banco();

        Cuenta cuenta1 = banco.crearCuenta(cliente1);

        cuenta1.imprimir();

        banco.depositar(100, cuenta1);
        banco.retirar(30, cuenta1);

        cuenta1.imprimir();

        Cliente cliente2 = new Cliente("1100000000", "Maria", "Perez");
        Cuenta cuenta2 = banco.crearCuenta(cliente2);

        banco.transferir(cuenta1, cuenta2, 50);

        System.out.println("=== Cuenta 1 ===");
        cuenta1.imprimir();

        System.out.println("=== Cuenta 2 ===");
        cuenta2.imprimir();
    }
}