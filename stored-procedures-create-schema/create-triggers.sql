--TRIGGERS
--Trigger for USER in HORSE TABLE
IF OBJECT_ID ('[tr_USER_HORSE]', 'TR') IS NOT NULL
	DROP TRIGGER [tr_USER_HORSE]
GO
CREATE TRIGGER [tr_USER_HORSE] ON [dbo].[HORSE]
AFTER INSERT, UPDATE
AS
DECLARE @countDeleted int
SET @countDeleted = (SELECT COUNT(*) FROM deleted)
DECLARE @curUserId CHAR(8);
EXEC getCurUserId @userId = @curUserId OUTPUT;

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[HORSE]
	SET		[created_by]=@curUserId,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
	EXEC insert_into_log_history 'I', 'HORSE';
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[HORSE]
	SET		[updated_by]=@curUserId,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
	EXEC insert_into_log_history 'U', 'HORSE';
END
GO

--Trigger for USER in HORSE_COLOR
IF OBJECT_ID ('[tr_USER_HORSE_COLOR]', 'TR') IS NOT NULL
	DROP TRIGGER [tr_USER_HORSE_COLOR]
GO
CREATE TRIGGER [tr_USER_HORSE_COLOR] ON [dbo].[HORSE_COLOR]
AFTER INSERT, UPDATE
AS
DECLARE @countDeleted int
SET @countDeleted = (SELECT COUNT(*) FROM deleted)
--to get current user id
DECLARE @curUserId CHAR(8);
EXEC getCurUserId @userId = @curUserId OUTPUT;

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[HORSE_COLOR]
	SET		[created_by]=@curUserId,
			[date_created]=GETDATE()
	WHERE	[color_name] IN (SELECT [color_name] FROM inserted)
	EXEC insert_into_log_history 'I', 'HORSE_COLOR';
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[HORSE_COLOR]
	SET		[updated_by]=@curUserId,
			[date_updated]=GETDATE()
	WHERE	[color_name] IN (SELECT [color_name] FROM inserted)
	EXEC insert_into_log_history 'U', 'HORSE_COLOR';
END
GO

--Trigger for USER in JOCKEY
IF OBJECT_ID ('[tr_USER_JOCKEY]', 'TR') IS NOT NULL
	DROP TRIGGER [tr_USER_JOCKEY]
GO
CREATE TRIGGER [tr_USER_JOCKEY] ON [dbo].[JOCKEY]
AFTER INSERT, UPDATE
AS
DECLARE @countDeleted int
SET @countDeleted = (SELECT COUNT(*) FROM deleted)
--to get current user id
DECLARE @curUserId CHAR(8);
EXEC getCurUserId @userId = @curUserId OUTPUT;

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[JOCKEY]
	SET		[created_by]=@curUserId,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
	EXEC insert_into_log_history 'I', 'JOCKEY';
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[JOCKEY]
	SET		[updated_by]=@curUserId,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
	EXEC insert_into_log_history 'U', 'JOCKEY';
END
GO

--Trigger for USER in MEETING
IF OBJECT_ID ('[tr_USER_MEETING]', 'TR') IS NOT NULL
	DROP TRIGGER [tr_USER_MEETING]
GO
CREATE TRIGGER [tr_USER_MEETING] ON [dbo].[MEETING]
AFTER INSERT, UPDATE
AS
DECLARE @countDeleted int
SET @countDeleted = (SELECT COUNT(*) FROM deleted)

--to get current user id
DECLARE @curUserId CHAR(8);
EXEC getCurUserId @userId = @curUserId OUTPUT;

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[MEETING]
	SET		[created_by]=@curUserId,
			[date_created]=GETDATE()
	WHERE	[datem] IN (SELECT [datem] FROM inserted)
	EXEC insert_into_log_history 'I', 'MEETING';
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[MEETING]
	SET		[updated_by]=@curUserId,
			[date_updated]=GETDATE()
	WHERE	[datem] IN (SELECT [datem] FROM inserted)
	EXEC insert_into_log_history 'U', 'MEETING';
END
GO

--Trigger for USER in RACE
IF OBJECT_ID ('[tr_USER_RACE]', 'TR') IS NOT NULL
	DROP TRIGGER [tr_USER_RACE]
GO
CREATE TRIGGER [tr_USER_RACE] ON [dbo].[RACE]
AFTER INSERT, UPDATE
AS
DECLARE @countDeleted int
SET @countDeleted = (SELECT COUNT(*) FROM deleted)
--to get current user id
DECLARE @curUserId CHAR(8);
EXEC getCurUserId @userId = @curUserId OUTPUT;

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[RACE]
	SET		[created_by]=@curUserId,
			[date_created]=GETDATE()
	WHERE	[race_time] IN (SELECT [race_time] FROM inserted)
	 AND [meeting_date] IN (SELECT [meeting_date] FROM inserted)

	 EXEC insert_into_log_history 'I', 'RACE';
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[RACE]
	SET		[updated_by]=@curUserId,
			[date_updated]=GETDATE()
	WHERE	[race_time] IN (SELECT [race_time] FROM inserted)
	 AND [meeting_date] IN (SELECT [meeting_date] FROM inserted)

	 EXEC insert_into_log_history 'U', 'RACE';
END
GO

--Trigger for USER in RACE_DISTANCE
IF OBJECT_ID ('[tr_USER_RACE_DISTANCE]', 'TR') IS NOT NULL
	DROP TRIGGER [tr_USER_RACE_DISTANCE]
GO
CREATE TRIGGER [tr_USER_RACE_DISTANCE] ON [dbo].[RACE_DISTANCE]
AFTER INSERT, UPDATE
AS
DECLARE @countDeleted int
SET @countDeleted = (SELECT COUNT(*) FROM deleted)
--to get current user id
DECLARE @curUserId CHAR(8);
EXEC getCurUserId @userId = @curUserId OUTPUT;

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[RACE_DISTANCE]
	SET		[created_by]=@curUserId,
			[date_created]=GETDATE()
	WHERE	[distance] IN (SELECT [distance] FROM inserted)
	EXEC insert_into_log_history 'I', 'RACE_DISTANCE';
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[RACE_DISTANCE]
	SET		[updated_by]=@curUserId,
			[date_updated]=GETDATE()
	WHERE	[distance] IN (SELECT [distance] FROM inserted)
	EXEC insert_into_log_history 'U', 'RACE_DISTANCE';
END
GO

--Trigger for USER in RACE_TYPE
IF OBJECT_ID ('[tr_USER_RACE_TYPE]', 'TR') IS NOT NULL
	DROP TRIGGER [tr_USER_RACE_TYPE]
GO
CREATE TRIGGER [tr_USER_RACE_TYPE] ON [dbo].[RACE_TYPE]
AFTER INSERT, UPDATE
AS
DECLARE @countDeleted int
SET @countDeleted = (SELECT COUNT(*) FROM deleted)
--to get current user id
DECLARE @curUserId CHAR(8);
EXEC getCurUserId @userId = @curUserId OUTPUT;

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[RACE_TYPE]
	SET		[created_by]=@curUserId,
			[date_created]=GETDATE()
	WHERE	[type] IN (SELECT [type] FROM inserted)
	EXEC insert_into_log_history 'I', 'RACE_TYPE';
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[RACE_TYPE]
	SET		[updated_by]=@curUserId,
			[date_updated]=GETDATE()
	WHERE	[type] IN (SELECT [type] FROM inserted)
	EXEC insert_into_log_history 'U', 'RACE_TYPE';
END
GO

--Trigger for USER in FIELD_TYPE
IF OBJECT_ID ('[tr_USER_FIELD_TYPE]', 'TR') IS NOT NULL
	DROP TRIGGER [tr_USER_FIELD_TYPE]
GO
CREATE TRIGGER [tr_USER_FIELD_TYPE] ON [dbo].[FIELD_TYPE]
AFTER INSERT, UPDATE
AS
DECLARE @countDeleted int
SET @countDeleted = (SELECT COUNT(*) FROM deleted)
--to get current user id
DECLARE @curUserId CHAR(8);
EXEC getCurUserId @userId = @curUserId OUTPUT;

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[FIELD_TYPE]
	SET		[created_by]=@curUserId,
			[date_created]=GETDATE()
	WHERE	[type] IN (SELECT [type] FROM inserted)
	EXEC insert_into_log_history 'I', 'FIELD_TYPE';
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[FIELD_TYPE]
	SET		[updated_by]=@curUserId,
			[date_updated]=GETDATE()
	WHERE	[type] IN (SELECT [type] FROM inserted)
	EXEC insert_into_log_history 'U', 'FIELD_TYPE';
END
GO

--Trigger for USER in PARTICIPATION
IF OBJECT_ID ('[tr_USER_PARTICIPATION]', 'TR') IS NOT NULL
	DROP TRIGGER [tr_USER_PARTICIPATION]
GO
CREATE TRIGGER [tr_USER_PARTICIPATION] ON [dbo].[PARTICIPATION]
AFTER INSERT, UPDATE
AS
DECLARE @countDeleted int
SET @countDeleted = (SELECT COUNT(*) FROM deleted)
--to get current user id
DECLARE @curUserId CHAR(8);
EXEC getCurUserId @userId = @curUserId OUTPUT;

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[PARTICIPATION]
	SET		[created_by]=@curUserId,
			[date_created]=GETDATE()
	WHERE	[race_time]IN (SELECT [race_time]FROM inserted) AND 
			[meeting_date] IN (SELECT [meeting_date] FROM inserted) AND 
			 [horse_id] IN (SELECT  [horse_id] FROM inserted) AND
			 [jockey_id] IN (SELECT  [jockey_id] FROM inserted) AND
			 [trainer_id]  IN(SELECT  [jockey_id] FROM inserted)

			 EXEC insert_into_log_history 'I', 'PARTICIPATION';
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[PARTICIPATION]
	SET		[updated_by]=@curUserId,
			[date_updated]=GETDATE()
	WHERE	[race_time]IN (SELECT [race_time]FROM inserted) AND 
			[meeting_date] IN (SELECT [meeting_date] FROM inserted) AND 
			 [horse_id] IN (SELECT  [horse_id] FROM inserted) AND
			 [jockey_id] IN (SELECT  [jockey_id] FROM inserted) AND
			 [trainer_id]  IN(SELECT  [jockey_id] FROM inserted)

			 EXEC insert_into_log_history 'U', 'PARTICIPATION';
END
GO

--Trigger for USER in TRAINER
IF OBJECT_ID ('[tr_USER_TRAINER]', 'TR') IS NOT NULL
	DROP TRIGGER [tr_USER_TRAINER]
GO
CREATE TRIGGER [tr_USER_TRAINER] ON [dbo].[TRAINER]
AFTER INSERT, UPDATE
AS
DECLARE @countDeleted int
SET @countDeleted = (SELECT COUNT(*) FROM deleted)
--to get current user id
DECLARE @curUserId CHAR(8);
EXEC getCurUserId @userId = @curUserId OUTPUT;

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[TRAINER]
	SET		[created_by]=@curUserId,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
	EXEC insert_into_log_history 'I', 'TRAINER';
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[TRAINER]
	SET		[updated_by]=@curUserId,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
	EXEC insert_into_log_history 'I', 'TRAINER';
END
GO

--Trigger for USER in BREEDER
IF OBJECT_ID ('[tr_USER_BREEDER]', 'TR') IS NOT NULL
	DROP TRIGGER [tr_USER_BREEDER]
GO
CREATE TRIGGER [tr_USER_BREEDER] ON [dbo].[BREEDER]
AFTER INSERT, UPDATE
AS
DECLARE @countDeleted int
SET @countDeleted = (SELECT COUNT(*) FROM deleted)
--to get current user id
DECLARE @curUserId CHAR(8);
EXEC getCurUserId @userId = @curUserId OUTPUT;

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[BREEDER]
	SET		[created_by]=@curUserId,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
	EXEC insert_into_log_history 'I', 'BREEDER';
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[BREEDER]
	SET		[updated_by]=@curUserId,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
	EXEC insert_into_log_history 'U', 'BREEDER';
END
GO

--Trigger for USER in OWNER
IF OBJECT_ID ('[tr_USER_OWNER]', 'TR') IS NOT NULL
	DROP TRIGGER [tr_USER_OWNER]
GO
CREATE TRIGGER [tr_USER_OWNER] ON [dbo].[OWNER]
AFTER INSERT, UPDATE
AS
DECLARE @countDeleted int
SET @countDeleted = (SELECT COUNT(*) FROM deleted)

--to get current user id
DECLARE @curUserId CHAR(8);
EXEC getCurUserId @userId = @curUserId OUTPUT;

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[OWNER]
	SET		[created_by]=@curUserId,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
	EXEC insert_into_log_history 'I', 'OWNER';
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[OWNER]
	SET		[updated_by]=@curUserId,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
	EXEC insert_into_log_history 'U', 'OWNER';
END
GO

--Trigger for USER in FAMILY
IF OBJECT_ID ('[tr_USER_FAMILY]', 'TR') IS NOT NULL
	DROP TRIGGER [tr_USER_FAMILY]
GO
CREATE TRIGGER [tr_USER_FAMILY] ON [dbo].[FAMILY]
AFTER INSERT, UPDATE
AS
  BEGIN
    DECLARE @countDeleted int
    SET @countDeleted = (SELECT COUNT(*) FROM deleted)
		-- To get the current user id.
		DECLARE @curUserId CHAR(8);
		EXEC getCurUserId @userId = @curUserId OUTPUT;

    IF (@countDeleted=0)
    --A new employee has been inserted
    BEGIN
      UPDATE	[dbo].[FAMILY]
      SET		[created_by]= @curUserId,
          [date_created]=GETDATE()
      WHERE	[name] IN (SELECT [name] FROM inserted);
      EXEC insert_into_log_history 'I', 'FAMILY';
    END
    ELSE
    --A current employee has been updated
    BEGIN
				PRINT 'whatever i entered';
      UPDATE	[dbo].[FAMILY]
      SET		[updated_by]= @curUserId,
          [date_updated]=GETDATE()
      WHERE	[name] IN (SELECT [name] FROM inserted);
      EXEC insert_into_log_history 'U', 'FAMILY';
    END
  END
GO

--SELECT  * FROM FAMILY;
--GO
--UPDATE FAMILY SET FAMILY.name = 'asojdhjaskhask' WHERE FAMILY.name = 'Cork family';
--GO
--SELECT * FROM LOG_HISTORY;
--GO
--SELECT  * FROM FAMILY;
--GO
--EXEC familyHorseInsert 'Jaffar';
--GO
----SELECT * FROM LOG_HISTORY;


--Trigger for USER in SYSTEM_USER
IF OBJECT_ID ('[tr_USER_SYSTEM_USER]', 'TR') IS NOT NULL
	DROP TRIGGER [tr_USER_SYSTEM_USER]
GO
-- CREATE TRIGGER [tr_USER_SYSTEM_USER] ON [dbo].[SYSTEM_USER]
-- AFTER INSERT, UPDATE
-- AS
-- DECLARE @countDeleted int
-- SET @countDeleted = (SELECT COUNT(*) FROM deleted)
--		-- To get the current user id.
-- DECLARE @curUserId CHAR(8);
-- EXEC getCurUserId @userId = @curUserId OUTPUT;
-- IF @countDeleted=0
-- --A new employee has been inserted
-- BEGIN
-- 	UPDATE	[dbo].[SYSTEM_USER]
-- 	SET		[created_by]=@curUserId,
-- 			[date_created]=GETDATE()
-- 	WHERE	[id] IN (SELECT [id] FROM inserted)
-- 	EXEC insert_into_log_history 'I', 'SYSTEM_USER';
-- END
-- ELSE
-- --A current employee has been updated
-- BEGIN
-- 	UPDATE	[dbo].[SYSTEM_USER]
-- 	SET		[updated_by]=@curUserId,
-- 			[date_updated]=GETDATE()
-- 	WHERE	[id] IN (SELECT [id] FROM inserted)
-- 	EXEC insert_into_log_history 'U', 'SYSTEM_USER';
-- END
-- GO