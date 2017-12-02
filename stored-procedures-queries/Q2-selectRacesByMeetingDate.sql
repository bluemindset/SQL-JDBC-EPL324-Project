IF OBJECT_ID ( 'selectRacesByMeetingDate', 'P' ) IS NOT NULL
    DROP PROCEDURE selectRacesByMeetingDate;
GO
CREATE PROCEDURE selectRacesByMeetingDate
@meetingDate DATETIME
AS
BEGIN
	SET NOCOUNT ON;
	SELECT *
	FROM RACE R
	WHERE CONVERT(DATE, R.meeting_date) = CONVERT(DATE, @meetingDate)
END;

EXEC selectRacesByMeetingDate '2010-01-08';
--How to compare dates? What is the format for dates ?