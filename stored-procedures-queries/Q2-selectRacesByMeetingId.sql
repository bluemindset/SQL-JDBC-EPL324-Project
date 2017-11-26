CREATE PROCEDURE selectRacesByMeetingId
@meeting_id nvarchar(25)
AS 
BEGIN 
	SET NOCOUNT ON;
	SELECT *
	FROM HORSE H
	WHERE SOUNDEX(H.name) = SOUNDEX(@meeting_id)
END;