--①生徒テーブルへの追加
insert into students (student_name,grade,created_at,updated_at)
values ('田中',8,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

insert into students (student_name,grade,created_at,updated_at)
values ('鈴木',5,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

insert into students (student_name,grade,created_at,updated_at)
values ('山田',12,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

--②学習日テーブルへの追加
/*insert into study_sessions(study_session)
values ('2025-05-07');

insert into study_sessions(study_session)
values ('2025-5-09');

--①と②生徒と学習日の中間　テーブル への追加
insert into  students_study_sessions(student_id,study_session_id)
values (1,1);

insert into students_study_sessions(student_id,study_session_id)
values (2,1);

insert into students_study_sessions(student_id,study_session_id)
values (3,2); */

--③科目テーブル　への追加
insert into subjects (subject_name)
values ('Java');

insert into subjects (subject_name)
values ('SpringFramework');

insert into subjects (subject_name)
values ('DB');

insert into subjects (subject_name)
values ('JavaScript');

insert into subjects (subject_name)
values ('HTML');

insert into subjects (subject_name)
values ('CSS');

insert into subjects (subject_name)
values ('Udemy');

insert into subjects (subject_name)
values ('Web開発');

--②と③　学習日と科目の中間テーブル
/*insert into  study_sessions_subjects(study_session_id,subject_id)
values (1,1);

insert into  study_sessions_subjects(study_session_id,subject_id)
values (1,2);

insert into  study_sessions_subjects(study_session_id,subject_id)
values (2,3);*/

--④参考書テーブル　科目：参考書 が　１：多　の場合
insert into textbooks(title,subject_id)
values ('すっきり分かるJava 実践編',1);

insert into textbooks(title,subject_id)
values ('SpringFramework 入門',2);

insert into textbooks(title,subject_id)
values ('DB入門',3);

insert into textbooks(title,subject_id)
values ('hibernate',3);

insert into textbooks(title,subject_id)
values ('JavaScriptの教科書',4);

insert into textbooks(title,subject_id)
values ('Rinux',5);

insert into textbooks(title,subject_id)
values ('Git',5);

insert into textbooks(title,subject_id)
values ('MDN(webサイト)',6);

insert into textbooks(title,subject_id)
values ('bootstrap',7);

insert into textbooks(title,subject_id)
values ('RESTAPI化',7);

--学習内容　詳細　①②④
insert into study_details (student_id,study_day,textbook_id)
values (1,'2025-5-11',1);

insert into study_details (student_id,study_day,textbook_id)
values (2,'2025-5-11',3);

insert into study_details (student_id,study_day,textbook_id)
values (2,'2025-5-12',1);

insert into study_details (student_id,study_day,textbook_id)
values (2,'2025-5-11',4);

insert into study_details (student_id,study_day,textbook_id)
values (3,'2025-5-12',5);


/*【科目：教科書　が　多：多　の場合】
--参考書テーブルへの追加
insert into textbooks (title)
values('NEW BASIC');

insert into textbooks (title)
values('ONLY UP');

insert into textbooks (title)
values('漢字ドリル');

--科目と参考書の中間テーブルの追加
insert into subjects_textbooks ((subject_id,study_session_id))
values(1,1);

insert into subjects_textbooks (subject_id,study_session_id)
values(1,2);

insert into subjects_textbooks (subject_id,study_session_id)
values(2,1);

insert into subjects_textbooks (subject_id,study_session_id)
values(2,2);

insert into subjects_textbooks (subject_id,study_session_id)
values(3,3);


--学習内容　詳細 ①②③④
insert into study_details (student_id,study_session_id,subject_id,textbook_id)
values (1,1,1,1);

insert into study_details (student_id,study_session_id,subject_id,textbook_id)
values (1,1,2,1);

insert into study_details (student_id,study_session_id,subject_id,textbook_id)
values (1,2,1,1);

insert into study_details (student_id,study_session_id,subject_id,textbook_id)
values (2,1,1,2);

insert into study_details (student_id,study_session_id,subject_id,textbook_id)
values (2,1,2,2);

insert into study_details (student_id,study_session_id,subject_id,textbook_id)
values (3,2,3,3); */
