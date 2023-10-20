--SELECT constraint_name, USERS
--FROM user_constraints
--WHERE r_constraint_name IN (
--    SELECT constraint_name
--    FROM user_constraints
--    WHERE table_name = 'USERS'
--);

--ALTER TABLE TRAINERS DROP COLUMN ROLE_ID;

--FAILED
DROP TABLE TRAINER_ROLES;

--ALTER TABLE ROLES DROP COLUMN RESPONSIBILITIES;