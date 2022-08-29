CREATE TABLE task (
                      id integer auto_increment,
                      uid character varying(255) NOT NULL ,
                      execution_time numeric(19,2) NOT NULL ,
                      creation_date timestamp without time zone NOT NULL,
                      modification_date timestamp without time zone
);
