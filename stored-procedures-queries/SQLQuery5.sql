IF OBJECT_ID('selectTrainerWithNo1winnings', 'P') IS NOT NULL
    DROP PROCEDURE  selectTrainerWithNo1winnings;
GO
CREATE PROCEDURE selectTrainerWithNo1winnings
AS 
BEGIN 
	
	
select TRAINER.id,TRAINER.compressed_name
FROM TRAINER
WHERE TRAINER.ID NOT IN (
		SELECT distinct TRAINER.id 
		FROM PARTICIPATION
		LEFT JOIN HORSE ON HORSE.id= PARTICIPATION.star_pos
		left join TRAINER on PARTICIPATION.trainer_id= TRAINER.id
		)
	
END;

exec selectTrainerWithNo1winnings

