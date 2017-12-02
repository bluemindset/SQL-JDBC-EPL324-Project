IF OBJECT_ID ( 'RaceResultsInsert', 'P' ) IS NOT NULL
	Drop PROCEDURE RaceResultsInsert
GO
CREATE PROCEDURE dbo.RaceResultsInsert @meetingdate date,@racetime time,@horse char(6), @jockey char(6),@trainer char(6),@startpos int,@finishpos int
		   
AS
	


SET NOCOUNT ON


INSERT INTO [dbo].[RACE_RESULTS]
	         (	[meeting_date]
				,[race_time]
				,[horse]
				,[jockey]
				,[trainer]
				,[start_pos]
				,[finish_pos]
			  )

     VALUES
           (@meetingdate
		   ,@racetime
           ,@horse
           ,@jockey
           ,@trainer
           ,@startpos
		   ,@finishpos
		   )

GO

