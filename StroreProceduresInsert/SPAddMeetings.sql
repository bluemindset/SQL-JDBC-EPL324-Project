IF OBJECT_ID ( 'meetingsInsert', 'P' ) IS NOT NULL
	Drop PROCEDURE meetingsInsert
GO
CREATE PROCEDURE dbo.meetingsInsert @datemeeting date

AS


SET NOCOUNT ON


INSERT INTO [dbo].[MEETING]
			(		
			   [datem]
			)

     VALUES
           (
		   @datemeeting
			)


GO		