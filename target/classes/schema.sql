CREATE TABLE vehicle (
    vehicle_id integer identity NOT NULL,
    vin varchar(17) NOT NULL,         -- Vehicle Title
    model varchar(50) NOT NULL,  -- Vehicle Description
    days int(4) NOT NULL,         -- Vehicle landing page link
    CONSTRAINT pk_vehicle_vehicle_id PRIMARY KEY (vehicle_id)
);