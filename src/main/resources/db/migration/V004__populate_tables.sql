INSERT INTO public.departamento
(id, nome)
VALUES(1, 'Financeiro');
INSERT INTO public.departamento
(id, nome)
VALUES(2, 'Comercial');
INSERT INTO public.departamento
(id, nome)
VALUES(3, 'Desenvolvimento');


INSERT INTO public.pessoa(nome, departamento_id)
VALUES('Camila', 1);
INSERT INTO public.pessoa(nome, departamento_id)
VALUES('Pedro', 2);
INSERT INTO public.pessoa(nome, departamento_id)
VALUES('Fabiano', 3);
INSERT INTO public.pessoa(nome, departamento_id)
VALUES('Raquel', 3);
INSERT INTO public.pessoa(nome, departamento_id)
VALUES('Patricia', 3);
INSERT INTO public.pessoa(nome, departamento_id)
VALUES('Joaquim', 1);
