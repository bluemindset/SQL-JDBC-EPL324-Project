IF OBJECT_ID('selectTrainerWithNo1winnings', 'P') IS NOT NULL
    DROP PROCEDURE  selectTrainerWithNo1winnings;
GO
CREATE PROCEDURE selectTrainerWithNo1winnings
AS 
BEGIN
select T.id,T.compressed_name
FROM TRAINER T
WHERE T.ID NOT IN (
		SELECT distinct T.id
		FROM PARTICIPATION P
		WHERE P.trainer_id= T.id AND P.end_pos !='1'
		)
END;

exec selectTrainerWithNo1winnings;

