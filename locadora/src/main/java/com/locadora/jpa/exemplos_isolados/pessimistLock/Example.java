package com.locadora.jpa.exemplos_isolados.pessimistLock;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

public class Example {
	
	@PersistenceContext
	private EntityManager manager;
	
	//@Test
	public void deveAdicionarUmClienteAConta() {
		Cliente cliente = new Cliente();
		cliente.setNome("Maria");

		cliente = manager.merge(cliente);
		manager.flush();

		Conta conta = manager.find(Conta.class, 2L, LockModeType.PESSIMISTIC_READ);
		conta.setClientes(new ArrayList<Cliente>());
		conta.getClientes().add(cliente);

		try {
			System.out.println("Esperando...");
			// [Enquanto aguarda ocorre uma tentativa de exclusão da conta - Mas o LockModeType.PESSIMISTIC_READ impede esta tentativa]
			Thread.sleep(12000); 
		} catch (InterruptedException e) {
		}

		manager.merge(conta);
	}

	//@Test
	public void deveTransferirValor() {
		Conta conta1 = manager.find(Conta.class, 1L, LockModeType.PESSIMISTIC_WRITE);
		Conta conta2 = manager.find(Conta.class, 2L, LockModeType.PESSIMISTIC_WRITE);

		BigDecimal valor = new BigDecimal("30");
		conta1.setSaldo(conta1.getSaldo().subtract(valor));

		manager.flush();

		try {
			System.out.println("Esperando...");
			// [Enquanto aguarda ocorre uma tentativa de alteração de uma das contas - Mas o LockModeType.PESSIMISTIC_WRITE impede esta tentativa]
			Thread.sleep(12000);
		} catch (InterruptedException e) {
		}

		conta2.setSaldo(conta2.getSaldo().add(valor));
	}
}
