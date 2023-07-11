CREATE KEYSPACE StudProKat WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE StudProKat.Coupons
(
    coupon TEXT,
    money INT,
    PRIMARY KEY ( coupon )
);

CREATE TABLE StudProKat.history_by_user
(
    id                 UUID,
    user_id            INT,
    client_id          INT,
    product_id         INT,
    client_name        TEXT,
    client_surname     TEXT,
    client_middle_name TEXT,
    product_name       TEXT,
    end_date           DATE,
    start_date         DATE,
    active             BOOLEAN,
    PRIMARY KEY ( (id), active )
) WITH CLUSTERING ORDER BY (active DESC);

CREATE TABLE StudProKat.products_by_ad_type
(
    id                  UUID,
    user_id             UUID,
    ad_type             TEXT,
    address             TEXT,
    photo               TEXT,
    product_name        TEXT,
    product_type        TEXT,
    status              TEXT,
    price               INT,
    product_description TEXT,
    creation_date       DATE ,
    PRIMARY KEY ( (id), ad_type )
) WITH CLUSTERING ORDER BY (ad_type ASC);

CREATE INDEX idx_Products_0 ON StudProKat.products_by_ad_type (user_id);

CREATE TABLE StudProKat.products_by_price
(
    id                  UUID,
    user_id             UUID,
    ad_type             TEXT,
    address             TEXT,
    photo               TEXT,
    product_name        TEXT,
    product_type        TEXT,
    status              TEXT,
    price               INT,
    product_description TEXT,
    creation_date       DATE,
    PRIMARY KEY ( (id), price )
) WITH CLUSTERING ORDER BY (price ASC);

CREATE INDEX idx_Products_1 ON StudProKat.products_by_price (user_id);

CREATE TABLE StudProKat.products_by_product_type
(
    id                  UUID,
    user_id             UUID,
    ad_type             TEXT,
    address             TEXT,
    photo               TEXT,
    product_name        TEXT,
    product_type        TEXT,
    status              TEXT,
    price               INT,
    product_description TEXT,
    creation_date       DATE,
    PRIMARY KEY ( (id), product_type )
) WITH CLUSTERING ORDER BY (product_type ASC);

CREATE INDEX idx_Products ON StudProKat.products_by_product_type (user_id);

CREATE TABLE StudProKat.reviews_by_user
(
    id          UUID,
    user_id     UUID,
    client_id   UUID,
    name        TEXT,
    surname     TEXT,
    middle_name TEXT,
    rating      TEXT,
    text        TEXT,
    date        DATE,
    PRIMARY KEY ( (id), date )
) WITH CLUSTERING ORDER BY (date DESC);

CREATE TABLE StudProKat.users_by_id
(
    id                UUID,
    email             TEXT,
    social_networks   SET<TEXT>,
    name              TEXT,
    surname           TEXT,
    middle_name       TEXT,
    registration_date DATE,
    PRIMARY KEY ( id )
);

CREATE TABLE StudProKat.users_by_mail
(
    email             TEXT,
    password          TEXT,
    id                UUID,
    registration_date DATE,
    permission_level   TEXT,
    PRIMARY KEY ( email )
);

CREATE TABLE StudProKat.wallet
(
    user_id UUID,
    money   INT,
    PRIMARY KEY ( user_id )
);

