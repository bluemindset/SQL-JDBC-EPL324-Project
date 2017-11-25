Drop PROCEDURE dbo.meetingsInsert

CREATE PROCEDURE dbo.meetingsInsert @datemeeting date

AS


SET NOCOUNT ON


INSERT INTO [dbo].[MEETING]
			(		
			   [date]	
			)

     VALUES
           (
		   @datemeeting
			)


GO		