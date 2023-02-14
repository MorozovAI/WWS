DELETE FROM user_roles;
DELETE FROM claim_parts;
DELETE FROM claims;
DELETE FROM users;
DELETE FROM parts;
DELETE FROM dealers;


copy parts FROM '/data/parts.csv' DELIMITER ';' CSV HEADER encoding 'windows-1251';

COPY dealers FROM '/data/d.csv' DELIMITER ';' CSV HEADER encoding 'windows-1251';

COPY users FROM '/data/users.csv' DELIMITER ';' CSV HEADER encoding 'windows-1251';

COPY user_roles FROM '/data/user_roles.csv' DELIMITER ';' CSV HEADER encoding 'windows-1251';

COPY claims FROM '/data/Claims.csv' DELIMITER ';' CSV HEADER encoding 'windows-1251';

COPY claim_parts FROM '/data/claim_parts.csv' DELIMITER ';' CSV HEADER encoding 'windows-1251';