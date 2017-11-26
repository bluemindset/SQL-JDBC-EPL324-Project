-- ******************************************************************************************************************************************************************** 
------ CREATE TABLES ------ CREATE TABLES ------ CREATE TABLES ------ CREATE TABLES ------ CREATE TABLES ------ CREATE TABLES ------ CREATE TABLES ------ CREATE TABLES 

CREATE TABLE [dbo].[HORSE](
	[id][int] NOT NULL IDENTITY(1,1),
	[name][nvarchar](25)  NULL,
	[compressed_name] AS cast([name] AS [nvarchar](15)),
	[cur_height][float]  NULL,
	[cur_weight][float]  NULL,
	[date_of_birth][date]  NULL ,
	[age][tinyint]  NULL ,
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
	[created_by][char](6)  NULL,
	[date_created][date]  NULL,
	[updated_by][char](6) NULL,
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
	[created_by][char](6)  NULL,
	[date_created][date]  NULL,
	[updated_by][char](6) NULL,
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
	[created_by][char](6)  NULL,
	[date_created][date]  NULL,
	[updated_by][char](6)  NULL,
	[date_updated][date]  NULL,
	CONSTRAINT [PK_JOCKEY] PRIMARY KEY (id ASC)
)
--insert into [JOCKEY] ([id],[first_name],[last_name]) VALUES ('548915','Afdydrdrdrydryddrufryryet','ttttttttttttttttttttt')
--UPDATE [JOCKEY]
--SET [first_name] = 'Bfdydrdrdrydryd'
--WHERE [id]='548915';
GO

--MEETING
CREATE TABLE [dbo].[MEETING](
	[id][int] IDENTITY(1,1) NOT NULL,
	[date][date] NOT NULL,
	--USER-CHANGES--
	[created_by][char](6)  NULL,
	[date_created][date]  NULL,
	[updated_by][char](6) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_MEETING] PRIMARY KEY (id ASC)
)
--insert into [MEETING] ([date]) VALUES ('12-7-1998')
GO
--RACE
CREATE TABLE [dbo].[RACE](
	[race_id][int] NOT NULL IDENTITY(1,1),
	[date][date]  NULL,
	[time][time]  NULL,
	[distance][int]  NULL,
	[prize_1][money]  NULL,
	[prize_2][money]  NULL,
	[prize_3][money]  NULL,
	[total_winnings] AS ([prize_1]+[prize_2]+[prize_3]),
	-- FOREIGN KEYS--
	[race_type][nvarchar](25)  NULL,
	[field_type][nvarchar](25)  NULL,
	[meeting_id] [int]  NOT NULL,
	--USER-CHANGES--
	[created_by][char](6)  NULL,
	[date_created][date]  NULL,
	[updated_by][char](6) NULL,
	[date_updated][date] NULL ,
	CONSTRAINT [PK_RACE] PRIMARY KEY ([race_id] ASC, [meeting_id] ASC)
)
GO

--insert into [RACE] ([prize_1],[prize_2],[prize_3],[meeting_id]) VALUES ('120','100','10','1')

--RACE_DISTANCE
CREATE TABLE [dbo].[RACE_DISTANCE](
	[distance][int] NOT NULL,
	--USER-CHANGES--
	[created_by][char](6) NULL,
	[date_created][date]  NULL,
	[updated_by][char](6) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_RACE_DISTANCE] PRIMARY KEY ([distance] ASC)
)
GO
--RACE_TYPE
CREATE TABLE [dbo].[RACE_TYPE](
	[type][nvarchar](25) NOT NULL,
	--USER-CHANGES--
	[created_by][char](6)  NULL,
	[date_created][date]  NULL,
	[updated_by][char](6) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_RACE_TYPE] PRIMARY KEY ([type] ASC)
)
GO

--FIELD TYPE
CREATE TABLE [dbo].[FIELD_TYPE](
	[type][nvarchar](25) NOT NULL,
	--USER-CHANGES--
	[created_by][char](6) NOT NULL ,
	[date_created][date] NOT NULL,
	[updated_by][char](6) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_FIELD_TYPE] PRIMARY KEY ([type] ASC)
)
GO
--PARTICIPATION
CREATE TABLE [dbo].[PARTICIPATION](
	-- FOREIGN KEYS + PK--
	[race_id][int] NOT NULL,
	[meeting_id][int] NOT NULL,
	[horse_id][int] NOT NULL,
	[jockey_id][char](6) NOT NULL,
	[trainer_id][char](6) NOT NULL,
	[cur_horse_weight][tinyint] NOT NULL ,
	[cur_horse_age][tinyint] NOT NULL,
	[star_pos][int] NOT NULL,
	[end_pos][int] NOT NULL,
	[winnings][tinyint] NOT NULL,
	--USER-CHANGES--
	[created_by][char](6) NOT NULL,
	[date_created][date] NOT NULL,
	[updated_by][char](6) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_PARTICIPATION] PRIMARY KEY ([race_id] ASC, [meeting_id] ASC, [horse_id] ASC, [jockey_id] ASC, [trainer_id] ASC)
)
GO
--TRAINER
CREATE TABLE [dbo].[TRAINER](
	[id][char](6) NOT NULL,
	[first_name][nvarchar](25)  NULL ,
	[last_name][nvarchar](25)  NULL,
	[compressed_name] AS cast(SUBSTRING([first_name],1,1)+'.'+[last_name] AS [nvarchar](15)),
	--USER-CHANGES--
	[created_by][char](6)  NULL,
	[date_created][date] NULL,
	[updated_by][char](6) NULL,
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
	[created_by][char](6) NULL,
	[date_created][date] NULL,
	[updated_by][char](6) NULL,
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
	[family_id][int] NOT NULL,
	[onwer_family][nvarchar](20)  NULL,
	--USER-CHANGES--
	[created_by][char](6)  NULL,
	[date_created][date] NULL ,
	[updated_by][char](6) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_OWNER] PRIMARY KEY ([id] ASC)
)
GO

--FAMILY
CREATE TABLE [dbo].[FAMILY](
	[id][int] NOT NULL,
	[name][nvarchar](25) ,
	[compressed_name] AS cast([name] AS [nvarchar](15)),
	[no_of_owners][int] NULL,
	--USER-CHANGES--
	[created_by][char](6)  NULL ,
	[date_created][date]  NULL,
	[updated_by][char](6) NULL,
	[date_updated][date] NULL,
	CONSTRAINT [PK_FAMILY] PRIMARY KEY ([id] ASC)
)
GO
--SYSTEM_USER
CREATE TABLE [dbo].[SYSTEM_USER](
	[id][char](6) NOT NULL,
	[username][nvarchar](25) UNIQUE NOT NULL,
	[first_name][nvarchar](25) NULL,
	[last_name][nvarchar](25)  NULL,
	[role][char](2)  NULL,
		--USER-CHANGES--
	[created_by][char](6) NOT NULL,
	[date_created][date] NOT NULL,
	[updated_by][char](6) NULL,
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

--RACE RESULTS--
CREATE TABLE [dbo].[RACE_RESULTS](
	[meeting_date][nvarchar](15) NOT NULL,
	[race_time][time] NULL,
	[horse][char](6) NULL,
	[jockey][char](6) NULL,
	[trainer][char](6) NULL,
	[start_pos][smallint] NULL,
	[finish_pos][smallint] NULL,
)
GO
--RACE FIELDS--
CREATE TABLE [dbo].[RACE_FIELDS](
	[field][nvarchar](15) NOT NULL,
)
GO