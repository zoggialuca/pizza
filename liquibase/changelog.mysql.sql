-- liquibase formatted sql

-- changeset l.zoggia:1628840738630-1
CREATE TABLE ingredient (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(255) NOT NULL, notes VARCHAR(255) NULL, CONSTRAINT PK_INGREDIENT PRIMARY KEY (id), UNIQUE (name));

-- changeset l.zoggia:1628840738630-2
CREATE TABLE pizza (id BIGINT AUTO_INCREMENT NOT NULL, is_vegetarian BIT(1) NOT NULL, name VARCHAR(255) NOT NULL, CONSTRAINT PK_PIZZA PRIMARY KEY (id), UNIQUE (name));

-- changeset l.zoggia:1628840738630-3
CREATE TABLE pizza_ingredient (id BIGINT AUTO_INCREMENT NOT NULL, quantity DOUBLE NULL, ingredient BIGINT NOT NULL, pizza BIGINT NOT NULL, unit_of_measure BIGINT NULL, CONSTRAINT PK_PIZZA_INGREDIENT PRIMARY KEY (id));

-- changeset l.zoggia:1628840738630-4
CREATE TABLE unit_of_measure (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(255) NOT NULL, CONSTRAINT PK_UNIT_OF_MEASURE PRIMARY KEY (id), UNIQUE (name));

-- changeset l.zoggia:1628840738630-5
ALTER TABLE pizza_ingredient ADD CONSTRAINT UKdw4krjkw2bqknsr8wdtnasilk UNIQUE (pizza, ingredient);

-- changeset l.zoggia:1628840738630-6
CREATE INDEX FK9541pp04rjlkapaygb9gjlit2 ON pizza_ingredient(ingredient);

-- changeset l.zoggia:1628840738630-7
CREATE INDEX FKmvwaccgkkshksnct8cm55e3j ON pizza_ingredient(unit_of_measure);

-- changeset l.zoggia:1628840738630-8
ALTER TABLE pizza_ingredient ADD CONSTRAINT FK459edhbrtw54gpw4bv37d8xx FOREIGN KEY (pizza) REFERENCES pizza (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

-- changeset l.zoggia:1628840738630-9
ALTER TABLE pizza_ingredient ADD CONSTRAINT FK9541pp04rjlkapaygb9gjlit2 FOREIGN KEY (ingredient) REFERENCES ingredient (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

-- changeset l.zoggia:1628840738630-10
ALTER TABLE pizza_ingredient ADD CONSTRAINT FKmvwaccgkkshksnct8cm55e3j FOREIGN KEY (unit_of_measure) REFERENCES unit_of_measure (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

