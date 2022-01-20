insert into usuario(codigo, nome, status) values(1, 'Henrique', 'ATIVO');
insert into usuario(codigo, nome, status) values(2, 'Maria', 'INATIVO');
insert into grupo(codigo, nome) values(1, 'ADMINISTRADOR');
insert into grupo(codigo, nome) values(2, 'VENDEDOR');
insert into usuario_grupo(usuario_codigo, grupo_codigo) values(1, 1);
insert into usuario_grupo(usuario_codigo, grupo_codigo) values(1, 2);
insert into usuario_grupo(usuario_codigo, grupo_codigo) values(2, 2);