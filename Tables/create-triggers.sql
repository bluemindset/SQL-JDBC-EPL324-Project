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

