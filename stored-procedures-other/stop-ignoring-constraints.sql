IF OBJECT_ID ('stopIgnoringConstraints') IS NOT NULL
	DROP PROCEDURE stopIgnoringConstraints
GO
CREATE PROCEDURE stopIgnoringConstraints
	@errorMessage NVARCHAR(4000) OUTPUT,
	@isSuccessful BIT OUTPUT
	AS
	BEGIN
		BEGIN TRY
		exec sp_MSforeachtable @command1="print '?'", @command2="ALTER TABLE ? WITH CHECK CHECK CONSTRAINT all";
		SET @isSuccessful = 'true';
		SET @errorMessage = 'No error';
		END TRY
		BEGIN CATCH
		SET @errorMessage = ERROR_MESSAGE( );
		SET @isSuccessful = 'false';
		END CATCH
	END