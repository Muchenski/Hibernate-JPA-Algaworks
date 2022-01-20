-- Tabela não mapeada como classe, que irá guardar os tempos em que houveram determinadas ações de uma pessoa.
-- Verificar classe 'Person'.

create sequence if not exists locadora.alert_id_seq start with 1 increment by 1;

create table if not exists locadora.alert(id int not null, person_id int, register_date timestamp);

alter table if exists locadora.alert add constraint alert_pkey primary key(id);

alter table if exists locadora.alert add foreign key(person_id) references locadora.person(id);

alter table if exists locadora.alert alter column id set default nextval('locadora.alert_id_seq'::regclass);
