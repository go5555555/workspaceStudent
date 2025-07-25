--テーブルの種類7種類
DROP TABLE IF EXISTS study_details cascade;
--DROP TABLE IF EXISTS study_sessions_subjects cascade;
DROP TABLE IF EXISTS students_study_sessions cascade;
DROP TABLE IF EXISTS subjects cascade;
DROP TABLE IF EXISTS textbooks;
--DROP TABLE IF EXISTS study_sessions;
DROP TABLE IF EXISTS students;

--①生徒テーブル
create table students(
	id serial primary key,
	student_name varchar(225) not null,
	grade Integer not null,
	created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
	updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
	);
	
--②学習日テーブル
/*create table study_sessions (
	id serial primary key,
	study_session date
	);
	
--①と②生徒と学習日の中間　テーブル
create table students_study_sessions(
	--id SERIAL PRIMARY KEY,
	student_id INTEGER REFERENCES students(id) ON DELETE CASCADE,
	study_session_id INTEGER REFERENCES study_sessions(id) ON DELETE CASCADE,
	primary key(student_id,study_session_id)
	);*/

--②科目テーブル
CREATE TABLE subjects (
    id SERIAL PRIMARY KEY,
    subject_name VARCHAR(100) NOT NULL
	);

--②と③学習日と科目の中間テーブル いらないかも
/*create table study_sessions_subjects(
	id SERIAL PRIMARY KEY,
	study_session_id INTEGER REFERENCES study_sessions(id) ON DELETE CASCADE,
	subject_id INTEGER REFERENCES subjects(id) ON DELETE CASCADE
	--primary key(study_session_id,subject_id)
	);*/

--③参考書テーブル　科目：参考書 が　１：多　の場合
CREATE TABLE textbooks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    subject_id INTEGER REFERENCES subjects(id) ON DELETE CASCADE
	);

--①②③
CREATE TABLE study_details(
    id SERIAL PRIMARY KEY,
    student_id INTEGER REFERENCES students(id) ON DELETE CASCADE,
    --study_session_id INTEGER REFERENCES study_sessions(id) ON DELETE CASCADE,
    textbook_id INTEGER REFERENCES textbooks(id) ON DELETE CASCADE,
    study_day date,
    pages TEXT,
    memo TEXT,          -- 例：学習メモ
    UNIQUE(student_id, study_day, textbook_id)
    );


/*【科目：教科書　が　多：多　の場合】
--④参考書テーブル
CREATE TABLE textbooks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
	);
	
--③と④科目と参考書の中間テーブル
create table subjects_textbooks(
	subject_id INTEGER REFERENCES subjects(id),
	study_session_id INTEGER REFERENCES study_sessions(id),
	primary key(study_day_id,subject_id)
	);

--①②③④
CREATE TABLE study_details(
	student_id INTEGER REFERENCES students(id),
	study_session_id INTEGER REFERENCES study_sessions(id),
	subject_id INTEGER REFERENCES subjects(id),
	textbook_id INTEGER REFERENCES textbooks(id),
	primary key(student_id,study_session_id,subject_id,textbook_id)
	
	);*/
