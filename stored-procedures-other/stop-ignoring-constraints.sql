CREATE PROCEDURE dbo.stopIgnoringConstraints
AS
BEGIN
	exec sp_MSforeachtable @command1="print '?'", @command2="ALTER TABLE ? WITH CHECK CHECK CONSTRAINT all"
END