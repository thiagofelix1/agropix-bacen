CREATE TABLE IF NOT EXISTS tipo_chave(id uuid PRIMARY KEY, tipo character varying(255) unique);

-- DO $$
-- DECLARE
--     count INTEGER;
--
-- BEGIN
--     CREATE TABLE IF NOT EXISTS tipo_chave(id uuid PRIMARY KEY, tipo character varying(255) unique);
--
--     count := (SELECT COUNT(*) FROM tipo_chave);
--
--     IF count = 0 THEN
--         INSERT INTO tipo_chave (id, tipo) VALUES ('74cd90d5-11c5-483f-a033-4062e2c4e683', 'ALEATORIA');
--         INSERT INTO tipo_chave (id, tipo) VALUES ('cf58ff8e-66ae-4541-9f5c-cc5613264a07', 'EMAIL');
--         INSERT INTO tipo_chave (id, tipo) VALUES ('1626ad1a-5977-4516-bb04-9fbccf07f1cb', 'CELULAR');
--         INSERT INTO tipo_chave (id, tipo) VALUES ('56f4a424-3c5a-4d10-bbfb-9a5362639792', 'CPF');
--     END IF;
-- END $$;
