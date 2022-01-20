-- Tabela não mapeada como classe, que irá guardar os tempos em que houveram determinadas ações de uma pessoa.
-- Verificar classe 'Person'.

create sequence	locadora.alert_id_seq start with 1 increment by 1;

create table locadora.alert(
	id int not null,
	person_id int,
	register_date timestamp
);

alter table locadora.alert add constraint alert_pkey primary key(id);

alter table locadora.alert add foreign key(person_id) references locadora.person(id);

alter table locadora.alert alter column id set default nextval('locadora.alert_id_seq'::regclass);

insert into locadora.alert(person_id, register_date) values((select id from locadora.person offset 0 limit 1), (select now()::timestamp));
insert into locadora.alert(person_id, register_date) values((select id from locadora.person offset 1 limit 1), (select now()::timestamp));

select locadora.person.id, locadora.person.name, locadora.alert.* from locadora.alert, locadora.person where locadora.person.id = locadora.alert.person_id; 