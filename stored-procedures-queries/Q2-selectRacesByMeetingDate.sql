CREATE PROCEDURE selectRacesByMeetingDate
@meetingDate nvarchar(25)
AS 
BEGIN 
	SET NOCOUNT ON;
	SELECT *
	FROM MEETING M, RACE R
	WHERE M.id = R.meeting_id AND M.date = @meetingDate
END;
--How to compare dates? What is the format for dates ?