USE golovatyi;

#1
DROP PROCEDURE IF EXISTS add_airport;
DELIMITER //
CREATE PROCEDURE add_airport(
    IN airport_name varchar(45)
)
BEGIN
    INSERT INTO airports(name) VALUES (airport_name);
END //

#2
DROP PROCEDURE IF EXISTS add_base_airport;
DELIMITER //
CREATE PROCEDURE add_base_airport(
    IN airport_id integer,
    IN airline_id integer
)
BEGIN
    INSERT INTO base_airports VALUES (airline_id,airport_id);
END //


#3
DROP PROCEDURE IF EXISTS add_10_airports;
DELIMITER //
CREATE PROCEDURE add_10_airports(
    IN airport_name varchar(45)
)
BEGIN
    DECLARE x int;
    SET x = 1;
    label1: LOOP
        IF x > 10 THEN LEAVE label1;
        END IF;
        INSERT INTO airports(name) VALUES(CONCAT(airport_name,x));
        SET x = x+1;
    END LOOP;
END //
DELIMITER ;

#4
DROP PROCEDURE IF EXISTS get_max_passengers;
DELIMITER //
CREATE PROCEDURE get_max_passengers(
OUT value1 int
)
BEGIN
    SELECT get_max_passengers_func() INTO value1;
END //
DELIMITER ;


#5
DROP PROCEDURE IF EXISTS ProcCursor;
DELIMITER //
CREATE PROCEDURE ProcCursor()
BEGIN


    DECLARE done int DEFAULT false;
    DECLARE full_nameT char(25);

    DECLARE St_Cursor10 CURSOR
        FOR SELECT full_name FROM users;

    DECLARE CONTINUE HANDLER
        FOR NOT FOUND SET done = true;

    DROP DATABASE IF EXISTS test;
    CREATE DATABASE test;

    OPEN St_Cursor10;
    myLoop: LOOP

        FETCH St_Cursor10 INTO full_nameT;
        IF done=true THEN LEAVE myLoop;
        END IF;
        SET @temp_query=CONCAT('CREATE TABLE test.', full_nameT,DATE_FORMAT (CURRENT_TIMESTAMP(), '_%d_%m_%Y__%H_%i_%s'),'(id int, country varchar(45), year int)');

        PREPARE myquery FROM @temp_query;
        EXECUTE myquery;
        DEALLOCATE PREPARE myquery;
    END LOOP;
    CLOSE St_Cursor10;

END //
DELIMITER ;
