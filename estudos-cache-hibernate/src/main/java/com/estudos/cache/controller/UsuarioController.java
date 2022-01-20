package com.estudos.cache.controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.cache.model.domain.Grupo;
import com.estudos.cache.model.domain.Status;
import com.estudos.cache.model.domain.Usuario;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	@Autowired
	private EntityManager entityManager;

	@GetMapping
	public ResponseEntity<List<Usuario>> obterUsuarios() {
		return ResponseEntity.ok(entityManager.createQuery("select distinct u from Usuario u left join fetch u.grupos", Usuario.class).getResultList());
	}

	@GetMapping(value = "/{codigoUsuario}")
	public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable Long codigoUsuario) {
		return ResponseEntity.ok(entityManager.find(Usuario.class, codigoUsuario));
	}

	@GetMapping(value = "/grupos")
	public ResponseEntity<List<Grupo>> obterGrupos() {
		return ResponseEntity.ok(entityManager.createQuery("from Grupo g", Grupo.class).getResultList());
	}
	
	@GetMapping(value = "/quantidade-ativos")
	public ResponseEntity<Long> obterNumeroDeUsuariosAtivos() {
		// Sem cache de query:
		// Long resultado = entityManager.createQuery("select count(u) from Usuario u where u.status = 'ATIVO'", Long.class).getSingleResult();
		// Com cache de query:
		Session session = entityManager.unwrap(Session.class);
		Long resultado = session.createQuery("select count(u) from Usuario u where u.status =:status", Long.class).setParameter("status", Status.ATIVO).setCacheable(true).uniqueResult();
		return ResponseEntity.ok(resultado);
	}
	
	@GetMapping(value = "/quantidade")
	public ResponseEntity<Long> obterNumeroDeUsuarios() {
		return ResponseEntity.ok(entityManager.createQuery("select count(u) from Usuario u", Long.class).getSingleResult());
	}

}
