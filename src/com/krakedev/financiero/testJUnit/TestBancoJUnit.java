package com.krakedev.financiero.testJUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestBancoJUnit {

	@Test
    public void testCrearCuenta() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("1723456789", "Jefferson", "Chiluisa");

        Cuenta c1 = banco.crearCuenta(cliente);
        Cuenta c2 = banco.crearCuenta(cliente);

        assertNotNull(c1);
        assertNotNull(c2);

        assertEquals("1000", c1.getId());
        assertEquals("1001", c2.getId());
    }

    @Test
    public void testDepositarCorrecto() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("1723456789", "Jefferson", "Chiluisa");

        Cuenta cuenta = banco.crearCuenta(cliente);

        boolean resultado = banco.depositar(100, cuenta);

        assertTrue(resultado);
        assertEquals(100, cuenta.getSaldoActual());
    }

    @Test
    public void testDepositarInvalido() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("1723456789", "Jefferson", "Chiluisa");

        Cuenta cuenta = banco.crearCuenta(cliente);

        boolean resultado = banco.depositar(-50, cuenta);

        assertFalse(resultado);
        assertEquals(0, cuenta.getSaldoActual());
    }

    @Test
    public void testRetirarCorrecto() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("1723456789", "Jefferson", "Chiluisa");

        Cuenta cuenta = banco.crearCuenta(cliente);
        banco.depositar(100, cuenta);

        boolean resultado = banco.retirar(40, cuenta);

        assertTrue(resultado);
        assertEquals(60, cuenta.getSaldoActual());
    }

    @Test
    public void testRetirarSaldoInsuficiente() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("1723456789", "Jefferson", "Chiluisa");

        Cuenta cuenta = banco.crearCuenta(cliente);

        boolean resultado = banco.retirar(50, cuenta);

        assertFalse(resultado);
        assertEquals(0, cuenta.getSaldoActual());
    }

    @Test
    public void testTransferirCorrecto() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("1723456789", "Jefferson", "Chiluisa");

        Cuenta origen = banco.crearCuenta(cliente);
        Cuenta destino = banco.crearCuenta(cliente);

        banco.depositar(100, origen);

        boolean resultado = banco.transferir(origen, destino, 50);

        assertTrue(resultado);
        assertEquals(50, origen.getSaldoActual());
        assertEquals(50, destino.getSaldoActual());
    }

    @Test
    public void testTransferirFallido() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("1723456789", "Jefferson", "Chiluisa");

        Cuenta origen = banco.crearCuenta(cliente);
        Cuenta destino = banco.crearCuenta(cliente);

        boolean resultado = banco.transferir(origen, destino, 50);

        assertFalse(resultado);
        assertEquals(0, origen.getSaldoActual());
        assertEquals(0, destino.getSaldoActual());
    }
    }
