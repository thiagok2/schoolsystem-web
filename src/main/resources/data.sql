delete from usuario_permissoes;
delete from permissao;
delete from usuario;

INSERT INTO permissao (id, nome) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

INSERT INTO usuario (id, email, password, nome) VALUES
(1, 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS',
'Admin2'),
(2, 'user@gmail.com', '$2a$10$UFEPYW7Rx1qZqdHajzOnB.VBR3rvm7OI7uSix4RadfQiNhkZOi2fi',
'Thiago2'),

(3, 'teste2@gmail.com', '$2a$10$UFEPYW7Rx1qZqdHajzOnB.VBR3rvm7OI7uSix4RadfQiNhkZOi2fi',
'Teste2');

insert into usuario_permissoes(usuario_id, permissao_id) values
(1,1),
(1,2),
(2,2);