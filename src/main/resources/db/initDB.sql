DROP TABLE IF EXISTS claim_parts;
DROP TABLE IF EXISTS claims;
DROP TABLE IF EXISTS parts;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS dealer_users;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS dealers;
DROP SEQUENCE IF EXISTS hibernate_sequence;

CREATE SEQUENCE hibernate_sequence START 200000;

CREATE TABLE parts
(
    id          INTEGER          NOT NULL,
    part_number VARCHAR(255)     NOT NULL,
    description VARCHAR(255)     NOT NULL,
    price       DOUBLE PRECISION NOT NULL,
    CONSTRAINT pk_parts PRIMARY KEY (id),
    CONSTRAINT uc_parts_part_number UNIQUE (part_number)
);


CREATE TABLE dealers
(
    id          INTEGER              NOT NULL,
    dealer_code INTEGER              NOT NULL,
    dealer_name VARCHAR(255),
    enabled     BOOLEAN DEFAULT TRUE NOT NULL,
    CONSTRAINT pk_dealers PRIMARY KEY (id),
    CONSTRAINT uc_dealers_dealer_name UNIQUE (dealer_name)
);

CREATE TABLE users
(
    id         INTEGER                                   NOT NULL,
    name       VARCHAR(255)                              NOT NULL,
    email      VARCHAR(255)                              NOT NULL,
    password   VARCHAR(255)                              NOT NULL,
    enabled    BOOLEAN                     DEFAULT TRUE  NOT NULL,
    registered TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    dealer_id  INTEGER,
    CONSTRAINT pk_users PRIMARY KEY (id),
    FOREIGN KEY (dealer_id) REFERENCES dealers (id) ON DELETE CASCADE,
    CONSTRAINT uc_users_email UNIQUE (email)
);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT fk_user_roles_on_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE dealer_users
(
    dealer_id INTEGER NOT NULL,
    users_id  INTEGER NOT NULL,
    CONSTRAINT pk_dealer_users PRIMARY KEY (dealer_id, users_id),
    CONSTRAINT fk_dealer_users_on_dealer FOREIGN KEY (dealer_id) REFERENCES dealers (id) ON DELETE CASCADE,
    CONSTRAINT fk_dealer_users_on_user FOREIGN KEY (users_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE claims
(
    id               INTEGER,
    dealer_id        INTEGER      NOT NULL,
    oem              VARCHAR(255),
    dealer_ro        VARCHAR(255) NOT NULL,
    esn              VARCHAR(255) NOT NULL,
    mileage          INTEGER,
    mileage_type     INTEGER,
    application_type INTEGER,
    engine_model     VARCHAR(255),
    failure_date     date         NOT NULL,
    receive_date     TIMESTAMP WITHOUT TIME ZONE,
    question_date    TIMESTAMP WITHOUT TIME ZONE,
    submit_date      TIMESTAMP WITHOUT TIME ZONE,
    approve_date     TIMESTAMP WITHOUT TIME ZONE,
    claim_amount     DOUBLE PRECISION,
    approve_amount   DOUBLE PRECISION,
    author           INTEGER,
    adviser          INTEGER,
    narrative        text,
    history          text,
    status           INTEGER,
    CONSTRAINT pk_claims PRIMARY KEY (id),
    FOREIGN KEY (dealer_id) REFERENCES dealers (id) ON DELETE CASCADE,
    CONSTRAINT FK_CLAIMS_ON_ADVISER FOREIGN KEY (adviser) REFERENCES users (id),
    CONSTRAINT FK_CLAIMS_ON_AUTHOR FOREIGN KEY (author) REFERENCES users (id)
);
CREATE UNIQUE INDEX claims_unique_dealer_dealer_RO_idx ON claims (dealer_id, dealer_ro);

CREATE TABLE claim_parts
(
    claim_id INTEGER NOT NULL,
    part_id  INTEGER NOT NULL,
    qty      INTEGER,
    CONSTRAINT pk_claim_parts PRIMARY KEY (claim_id, part_id),
    CONSTRAINT fk_claim_parts_on_claim FOREIGN KEY (claim_id) REFERENCES claims (id) ON DELETE CASCADE,
    CONSTRAINT fk_claim_parts_on_part FOREIGN KEY (part_id) REFERENCES parts (id)
);
