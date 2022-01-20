drop sequence if exists locadora.alert_id_seq cascade;

alter table if exists locadora.alert drop constraint alert_pkey;

drop sequence if exists alert_pkey cascade;

drop table if exists locadora.alert;