DROP TABLE IF EXISTS dealers;

CREATE TABLE dealers
(
    id          INTEGER NOT NULL,
    dealer_code INTEGER NOT NULL,
    dealer_name VARCHAR(255),
    CONSTRAINT pk_dealers PRIMARY KEY (id),
    CONSTRAINT uc_dealers_dealer_name UNIQUE (dealer_name)
);


COPY dealers FROM 'D:\d.csv' DELIMITER ';' CSV HEADER encoding 'windows-1251';


CREATE TABLE parts
(
    id          INTEGER          NOT NULL,
    part_number VARCHAR(255)     NOT NULL,
    description VARCHAR(255)     NOT NULL,
    price       DOUBLE PRECISION NOT NULL,
    CONSTRAINT pk_parts PRIMARY KEY (id),
    CONSTRAINT uc_parts_part_number UNIQUE (part_number)
);
