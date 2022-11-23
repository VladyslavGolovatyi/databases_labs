USE golovatyi;

DELIMITER //
CREATE FUNCTION get_max_passengers_func()
    RETURNS INTEGER
DETERMINISTIC
BEGIN
DECLARE max_pass INTEGER;
SELECT MAX(max_passengers) INTO max_pass
FROM planes;
IF (max_pass IS NULL) THEN
    SET max_pass = 0;
END IF;
RETURN max_pass;
END //
DELIMITER ;