IF OBJECT_ID('dbo.ignoreConstraints') IS NOT NULL
    DROP PROCEDURE dbo.ignoreConstraints
GO
CREATE PROCEDURE dbo.ignoreConstraints
AS
BEGIN
	EXEC sp_MSforeachtable "ALTER TABLE ? NOCHECK CONSTRAINT all"
END
GO