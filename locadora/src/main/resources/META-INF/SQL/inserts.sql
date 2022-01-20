insert into locadora.defaultBaseDomain(id) values(1);
insert into locadora.person(id, name, cpf, gender) values(1, 'Henrique', '10311678971', 0);

insert into locadora.defaultBaseDomain(id) values(2);
insert into locadora.person(id, name, cpf, gender) values(2, 'Joca', '98989898989', 0);

insert into locadora.alert(id, person_id, register_date) values(1, 1, (select now()::timestamp));
insert into locadora.alert(id, person_id, register_date) values(2, 2, (select now()::timestamp));