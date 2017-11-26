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

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[HORSE]
	SET		[created_by]=USER,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[HORSE]
	SET		[updated_by]=USER,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
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

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[HORSE_COLOR]
	SET		[created_by]=USER,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[HORSE_COLOR]
	SET		[updated_by]=USER,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
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

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[JOCKEY]
	SET		[created_by]=USER,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[JOCKEY]
	SET		[updated_by]=USER,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
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

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[MEETING]
	SET		[created_by]=USER,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[MEETING]
	SET		[updated_by]=USER,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
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

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[RACE]
	SET		[created_by]=USER,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[RACE]
	SET		[updated_by]=USER,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
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

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[RACE_DISTANCE]
	SET		[created_by]=USER,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[RACE_DISTANCE]
	SET		[updated_by]=USER,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
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

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[RACE_TYPE]
	SET		[created_by]=USER,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[RACE_TYPE]
	SET		[updated_by]=USER,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
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

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[FIELD_TYPE]
	SET		[created_by]=USER,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[FIELD_TYPE]
	SET		[updated_by]=USER,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
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

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[PARTICIPATION]
	SET		[created_by]=USER,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[PARTICIPATION]
	SET		[updated_by]=USER,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
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

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[BREEDER]
	SET		[created_by]=USER,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[BREEDER]
	SET		[updated_by]=USER,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
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

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[OWNER]
	SET		[created_by]=USER,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[OWNER]
	SET		[updated_by]=USER,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
END
GO



--Trigger for USER in FAMILY
IF OBJECT_ID ('[tr_USER_FAMILY]', 'TR') IS NOT NULL
	DROP TRIGGER [tr_USER_FAMILY]
GO
CREATE TRIGGER [tr_USER_FAMILY] ON [dbo].[FAMILY]
AFTER INSERT, UPDATE
AS
DECLARE @countDeleted int
SET @countDeleted = (SELECT COUNT(*) FROM deleted)

IF @countDeleted=0
--A new employee has been inserted
BEGIN
	UPDATE	[dbo].[FAMILY]
	SET		[created_by]=USER,
			[date_created]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
END
ELSE
--A current employee has been updated
BEGIN
	UPDATE	[dbo].[FAMILY]
	SET		[updated_by]=USER,
			[date_updated]=GETDATE()
	WHERE	[id] IN (SELECT [id] FROM inserted)
END
GO
