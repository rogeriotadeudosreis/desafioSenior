alter table cliente drop column nome;
alter table cliente drop column email;
alter table cliente rename column inicio_vigencia to data_inicio;
alter table cliente rename column fim_vigencia to data_fim;
alter table cliente rename column atualizacao to data_atualizacao;