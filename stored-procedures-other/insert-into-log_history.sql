IF OBJECT_ID('insert_into_log_history', 'P') IS NOT NULL
    DROP PROCEDURE insert_into_log_history
GO
CREATE PROCEDURE insert_into_log_history
@actionType char(1), @tableName NVARCHAR(25)
AS
  BEGIN
    INSERT INTO LOG_HISTORY (action, tmstmp, table_name, user_id)
    VALUES (@actionType, GETDATE(), @tableName, USER)
  END

EXEC insert_into_log_history 'U', 'HORSE';
SELECT * FROM LOG_HISTORY;