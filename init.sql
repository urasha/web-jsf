CREATE SEQUENCE point_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE TABLE point (
                       id NUMBER PRIMARY KEY,
                       x NUMBER(10, 5) NOT NULL,
                       y NUMBER(10, 5) NOT NULL,
                       radius NUMBER NOT NULL,
                       inside CHAR(1) NOT NULL
);

CREATE OR REPLACE TRIGGER point_seq_trigger
    BEFORE INSERT ON point
    FOR EACH ROW
BEGIN
    IF :new.id IS NULL THEN
        SELECT point_seq.NEXTVAL INTO :new.id FROM dual;
    END IF;
END;
/
