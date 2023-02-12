DELETE FROM user_roles;
DELETE FROM claim_parts;
DELETE FROM claims;
DELETE FROM users;
DELETE FROM parts;
DELETE FROM dealers;


COPY parts FROM 'D:\parts.csv' DELIMITER ';' CSV HEADER encoding 'windows-1251';

COPY dealers FROM 'D:\d.csv' DELIMITER ';' CSV HEADER encoding 'windows-1251';

COPY users FROM 'D:\users.csv' DELIMITER ';' CSV HEADER encoding 'windows-1251';

COPY user_roles FROM 'D:\user_roles.csv' DELIMITER ';' CSV HEADER encoding 'windows-1251';

COPY claims FROM 'D:\Claims.csv' DELIMITER ';' CSV HEADER encoding 'windows-1251';

COPY claim_parts FROM 'D:\claim_parts.csv' DELIMITER ';' CSV HEADER encoding 'windows-1251';