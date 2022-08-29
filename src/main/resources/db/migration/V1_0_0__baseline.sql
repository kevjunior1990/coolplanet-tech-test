--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1 (Debian 13.1-1.pgdg90+1)
-- Dumped by pg_dump version 13.1 (Debian 13.1-1.pgdg90+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;






--
-- Name: task_seq; Type: SEQUENCE;
--

CREATE SEQUENCE task_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: execution_time_seq; Type: SEQUENCE;
--

CREATE SEQUENCE execution_time_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

SET default_tablespace = '';

SET default_table_access_method = heap;




--
-- Name: task; Type: TABLE;
--

CREATE TABLE task (
    id bigint NOT NULL,
    uid character varying(255) unique NOT NULL ,
    creation_date timestamp without time zone NOT NULL,
    modification_date timestamp without time zone
);

--
-- Name: execution_time; Type: TABLE;
--

CREATE TABLE execution_time (
    id bigint NOT NULL,
    value numeric(19,2) NOT NULL ,
    task_id bigint NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    modification_date timestamp without time zone
);



--
-- Name: task task_pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY task
    ADD CONSTRAINT task_pkey PRIMARY KEY (id);


--
-- Name: execution_time execution__pkey; Type: CONSTRAINT;
--

ALTER TABLE ONLY execution_time
    ADD CONSTRAINT execution_time_pkey PRIMARY KEY (id);


--
-- Name: execution_time fk_execution_time_task; Type: CONSTRAINT;
--
ALTER TABLE execution_time
    ADD CONSTRAINT fk_execution_time_task FOREIGN KEY (task_id) REFERENCES task;

--
-- PostgreSQL database dump complete
--

