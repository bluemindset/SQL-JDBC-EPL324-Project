IF OBJECT_ID ( 'selectRacesByMeetingDate', 'P' ) IS NOT NULL
    DROP PROCEDURE selectRacesByMeetingDate;
GO
CREATE PROCEDURE selectRacesByMeetingDate
@meetingDate DATETIME
AS 
BEGIN 
	SET NOCOUNT ON;
	SELECT *
	FROM MEETING M, RACE R
	WHERE M.id = R.meeting_id AND CONVERT(DATE, M.date) = CONVERT(DATE, @meetingDate)
END;
--EXEC selectRacesByMeetingDate()
--How to compare dates? What is the format for dates ?