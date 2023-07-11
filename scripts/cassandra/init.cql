CREATE KEYSPACE StudProKat WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE StudProKat.Coupons
(
    coupon TEXT,
    money  INT,
    PRIMARY KEY ( coupon )
);

CREATE TABLE StudProKat.history_by_user
(
    id                 UUID,
    user_id            UUID,
    client_id          UUID,
    product_id         INT,
    client_name        TEXT,
    client_surname     TEXT,
    client_middle_name TEXT,
    product_name       TEXT,
    end_date           DATE,
    start_date         DATE,
    active             BOOLEAN,
    PRIMARY KEY ( (active), user_id )
) WITH CLUSTERING ORDER BY (user_id DESC);

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
    creation_date       DATE,
    PRIMARY KEY ( (ad_type), user_id)
) WITH CLUSTERING ORDER BY (user_id ASC);

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
    PRIMARY KEY ( (user_id), price )
) WITH CLUSTERING ORDER BY (price ASC);

CREATE TABLE StudProKat.products_by_id
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
    PRIMARY KEY ( id )
);

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
    PRIMARY KEY ( (product_type), user_id )
) WITH CLUSTERING ORDER BY (user_id ASC);


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
    PRIMARY KEY ((user_id), date )
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
    permission_level  TEXT,
    PRIMARY KEY ( email )
);

CREATE TABLE StudProKat.wallet
(
    user_id UUID,
    money   INT,
    PRIMARY KEY ( user_id )
);

