-- ******************************************************************************************************************************************************************** 
------ CREATE TABLES ------ CREATE TABLES ------ CREATE TABLES ------ CREATE TABLES ------ CREATE TABLES ------ CREATE TABLES ------ CREATE TABLES ------ CREATE TABLES 

CREATE TABLE [dbo].[HORSE](
	[id][int] NOT NULL,
	[name][nvarchar](25)  NULL,
	[compressed_name] AS cast([name] AS [nvarchar](15)),
	[cur_weight][float]  NULL,
	[date_of_birth][date]  NULL ,
	[age] AS CAST( DATEDIFF( YY,CONVERT(DATE, [date_of_birth]) , CONVERT(DATE, GetDate()))AS [tinyint]),  
	[sex][nvarchar](10)  NULL ,
	[is_purebred][bit] NULL,
	[record] [nvarchar](250) NULL ,
	[origin_country][nvarchar](25) NULL,
	-----Foreign Keys--------
	[mama_id][int] NULL,
	[dad_id][int] NULL,
	[jockey_id][char](6) NULL,
	[breeder_id][char](6)  NULL,
	[color_name][nvarchar](25)  NULL,
	[trainer_id][char](6)  NULL,
	[owner_id][char](6)  NULL,
	--USER-CHANGES--
	[created_by][char](8)  NULL,
	[date_created][date]  NULL,
	[updated_by][char](8) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_HORSE] PRIMARY KEY (id ASC)
)
GO

--insert into [HORSE] ([name]) VALUES ('tfdydrdrdrydryddrufryryet')
--UPDATE [HORSE]
--SET [name] = 'tfdydrdrdrydryddrufryryet'
--WHERE [id]=1;

--HORSE COLOR
CREATE TABLE [dbo].[HORSE_COLOR](
	[color_name][nvarchar](25)  NOT NULL,
	--USER-CHANGES--
	[created_by][char](8)  NULL,
	[date_created][date]  NULL,
	[updated_by][char](8) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_HORSE_COLOR] PRIMARY KEY (color_name ASC)
)
GO
--JOCKEY
CREATE TABLE [dbo].[JOCKEY](
	[id][char](6) NOT NULL ,
	[first_name][nvarchar](25)  NULL,
	[last_name][nvarchar](25)  NULL,
	[compressed_name]AS cast(SUBSTRING([first_name],1,1)+'.'+[last_name] AS [nvarchar](15)) ,
	[total_number_of_wins][smallint] NULL,
	--USER-CHANGES--
	[created_by][char](8)  NULL,
	[date_created][date]  NULL,
	[updated_by][char](8)  NULL,
	[date_updated][date]  NULL,
	CONSTRAINT [PK_JOCKEY] PRIMARY KEY (id ASC)
)

GO
--MEETING
CREATE TABLE [dbo].[MEETING](
	[datem][date] NOT NULL,
	--USER-CHANGES--
	[created_by][char](8)  NULL,
	[date_created][date]  NULL,
	[updated_by][char](8) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_MEETING] PRIMARY KEY (datem ASC)
)
--insert into [MEETING] ([date]) VALUES ('12-7-1998')
GO
--RACE
CREATE TABLE [dbo].[RACE](
	[race_time][time] NOT NULL,
	[distance][int]  NULL,
	[prize_1][money]  NULL,
	[prize_2][money]  NULL,
	[prize_3][money]  NULL,
	[total_winnings] AS ([prize_1]+[prize_2]+[prize_3]),
	-- FOREIGN KEYS--
	[race_type][nvarchar](25)  NULL,
	[field_type][nvarchar](25)  NULL,
	[meeting_date][date] NOT NULL,
	--USER-CHANGES--
	[created_by][char](8)  NULL,
	[date_created][date]  NULL,
	[updated_by][char](8) NULL,
	[date_updated][date] NULL ,
	CONSTRAINT [PK_RACE] PRIMARY KEY ([race_time] ASC, [meeting_date] ASC)
)
GO

--insert into [RACE] ([prize_1],[prize_2],[prize_3],[meeting_id]) VALUES ('120','100','10','1')

--RACE_DISTANCE
CREATE TABLE [dbo].[RACE_DISTANCE](
	[distance][int] NOT NULL,
	--USER-CHANGES--
	[created_by][char](8) NULL,
	[date_created][date]  NULL,
	[updated_by][char](8) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_RACE_DISTANCE] PRIMARY KEY ([distance] ASC)
)
GO
--RACE_TYPE
CREATE TABLE [dbo].[RACE_TYPE](
	[type][nvarchar](25) NOT NULL,
	--USER-CHANGES--
	[created_by][char](8)  NULL,
	[date_created][date]  NULL,
	[updated_by][char](8) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_RACE_TYPE] PRIMARY KEY ([type] ASC)
)
GO

--FIELD TYPE
CREATE TABLE [dbo].[FIELD_TYPE](
	[type][nvarchar](25) NOT NULL,
	--USER-CHANGES--
	[created_by][char](8) NULL ,
	[date_created][date] NULL,
	[updated_by][char](8) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_FIELD_TYPE] PRIMARY KEY ([type] ASC)
)
GO
--PARTICIPATION
CREATE TABLE [dbo].[PARTICIPATION](
	-- FOREIGN KEYS + PK--
	[race_time][time] NOT NULL,
	[meeting_date][date] NOT NULL,
	[horse_id][int] NOT NULL,
	[jockey_id][char](6) NOT NULL,
	[trainer_id][char](6) NOT NULL,
	[cur_horse_weight][tinyint] NOT NULL ,
	[cur_horse_age][tinyint] NULL,
	[star_pos][int] NOT NULL,
	[end_pos][int] NOT NULL,
	[winnings][MONEY] NOT NULL,
	--USER-CHANGES--
	[created_by][char](8) NULL,
	[date_created][date] NULL,
	[updated_by][char](8) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_PARTICIPATION] PRIMARY KEY ([race_time] ASC, [meeting_date] ASC, [horse_id] ASC, [jockey_id] ASC, [trainer_id] ASC)
)
GO
--TRAINER
CREATE TABLE [dbo].[TRAINER](
	[id][char](6) NOT NULL,
	[first_name][nvarchar](25)  NULL ,
	[last_name][nvarchar](25)  NULL,
	[compressed_name] AS cast(SUBSTRING([first_name],1,1)+'.'+[last_name] AS [nvarchar](15)),
	--USER-CHANGES--
	[created_by][char](8)  NULL,
	[date_created][date] NULL,
	[updated_by][char](8) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_TRAINER] PRIMARY KEY ([id] ASC)
)
GO
--BREEDER
CREATE TABLE [dbo].[BREEDER](
	[id][char](6) NOT NULL,
	[first_name][nvarchar](25)  NULL ,
	[last_name][nvarchar](25)  NULL,
	[compressed_name] AS cast(SUBSTRING([first_name],1,1)+'.'+[last_name] AS [nvarchar](15)),
	--USER-CHANGES--
	[created_by][char](8) NULL,
	[date_created][date] NULL,
	[updated_by][char](8) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_BREEDER] PRIMARY KEY ([id] ASC),
)
GO
--OWNER
CREATE TABLE [dbo].[OWNER](
	[id][char](6) NOT NULL,
	[first_name][nvarchar](25)  NULL,
	[last_name][nvarchar](25)  NULL,
	[compressed_name] AS cast(SUBSTRING([first_name],1,1)+'.'+[last_name] AS [nvarchar](15)),
	[uniform][nvarchar](50)  NULL,
	[title][nvarchar](5)  NULL,
	--FOREIGN KEY--
	[onwer_family][nvarchar](35)  NULL,
	--USER-CHANGES--
	[created_by][char](8)  NULL,
	[date_created][date] NULL ,
	[updated_by][char](8) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_OWNER] PRIMARY KEY ([id] ASC)
)
GO

--FAMILY
CREATE TABLE [dbo].[FAMILY](
	[name][nvarchar](35) ,
	[compressed_name] AS cast([name] AS [nvarchar](15)),
	[no_of_owners][int] NULL,
	--USER-CHANGES--
	[created_by][char](8)  NULL ,
	[date_created][date]  NULL,
	[updated_by][char](8) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_FAMILY] PRIMARY KEY ([name] ASC)
)
GO
--SYSTEM_USER
CREATE TABLE [dbo].[SYSTEM_USER](
	[id][char](8) NOT NULL,
	[username][nvarchar](25) UNIQUE NOT NULL,
	[passwordUser] [nvarchar](50) NULL,
	[first_name][nvarchar](25) NULL,
	[last_name][nvarchar](25)  NULL,
	[role][char](2)  NULL,
		--USER-CHANGES--
	[created_by][char](8) NULL,
	[date_created][date] NULL,
	[updated_by][char](8) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_USER] PRIMARY KEY ([id] ASC)
)
GO
----------------------------NEW TABLES-------------------------------------------------
--HORSE SEX--
CREATE TABLE [dbo].[HORSE_SEX](
	[horse_sex][nvarchar](15) NOT NULL
)
GO
CREATE TABLE [dbo].[LOG_HISTORY] (
	[id]INT NOT NULL IDENTITY(1,1),
	[action] CHAR(1),
	[tmstmp] DATETIME,
	[table_name] NVARCHAR(25),
	--foreign keys--
	[user_id] CHAR(8) NOT NULL,
	CONSTRAINT [PK_LOG_HISTORY] PRIMARY KEY([id] ASC )
)
GO
