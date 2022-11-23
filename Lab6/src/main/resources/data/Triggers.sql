USE golovatyi;

DELIMITER //
CREATE TRIGGER BeforeInsertCreditCard
    BEFORE INSERT
    ON credit_cards
    FOR EACH ROW
BEGIN
    IF (NOT EXISTS(SELECT * FROM users WHERE id=new.owner_id))
        THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'There is no user with such owner_id';
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER BeforeUpdateCreditCard
    BEFORE UPDATE
    ON credit_cards
    FOR EACH ROW
BEGIN
    IF (NOT EXISTS(SELECT * FROM users WHERE id=new.owner_id))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'There is no user with such owner_id';
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER BeforeDeleteUser
    BEFORE DELETE
    ON users
    FOR EACH ROW
BEGIN
    DELETE FROM credit_cards WHERE owner_id=old.id;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER BeforeDeleteAirline
    BEFORE DELETE
    ON airlines
    FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Cannot delete airline';
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER AfterDeletePlane
    AFTER DELETE
    ON planes
    FOR EACH ROW
BEGIN
    IF(SELECT COUNT(*) FROM planes)<10
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Delete error MIN cardinality';
    END IF;
END //
DELIMITER ;


DELIMITER //
CREATE TRIGGER BeforeInsertUser
    BEFORE INSERT
    ON users
    FOR EACH ROW
BEGIN
    IF (new.password NOT RLIKE '.{2,}')
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Password should have al least 2 characters';
    END IF;
END //
DELIMITER ;


