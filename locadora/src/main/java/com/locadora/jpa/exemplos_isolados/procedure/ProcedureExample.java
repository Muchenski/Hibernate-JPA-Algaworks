package com.locadora.jpa.exemplos_isolados.procedure;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

public class ProcedureExample {

	/* 
	 
	 create or replace
	 function retorna_nome_funcionario(func_id int, out min_len int)
	 returns text as
	 $$
	 -- Variáveis utilizadas no método para guardar os valores do select.
	 declare
	 nome text;
	 situacao text;
	 begin
	 select funcionario_nome, funcionario_situacao
	 into nome, situacao
	 from funcionario
	 where id = func_id;
	
	 if situacao = 'A' then
	 return nome || ' Usuário Ativo ';
	 elsif situacao = 'I' then
	 return nome || ' Usuário Inativo ';
	 else
	 return nome || ' Usuário com status nulo ou inválido ';
	 end if;
	 end
	 $$
	 language plpgsql;
	 
	 */

	public void procedureExample() {

		EntityManager entityManager = Persistence.createEntityManagerFactory("locadoraPU").createEntityManager();

		StoredProcedureQuery procedure = entityManager.createNamedStoredProcedureQuery("retorna_nome_funcionario");
		
		procedure.registerStoredProcedureParameter("func_id", Long.class, ParameterMode.IN);
		procedure.setParameter("func_id", 1l);
		
		procedure.registerStoredProcedureParameter("min_len", Long.class, ParameterMode.OUT);
		
		procedure.execute();
		
		Long minLen = (Long) procedure.getOutputParameterValue("min_len");
		
		System.out.println(minLen);
		
		// Caso fosse uma procedure que envolvesse alterações nos dados, deveria ser realizado o início e o commit da transação
		// pelo entityManager ou na própria procedure.
	}

}
