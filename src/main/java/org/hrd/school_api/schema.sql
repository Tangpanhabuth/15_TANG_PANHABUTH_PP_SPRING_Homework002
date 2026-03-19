CREATE DATABASE school_db;

--student tbl
CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone_number VARCHAR(20)
);

--instructor tbl
CREATE TABLE instructors (
    instructor_id SERIAL PRIMARY KEY,
    instructor_name VARCHAR(100) NOT NULL,
    email VARCHAR(100)
);

-- course tbl
CREATE TABLE courses (
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    description TEXT,
    instructor_id INT REFERENCES instructors(instructor_id)
         ON DELETE SET NULL
         ON UPDATE CASCADE
);

-- join tbl
CREATE TABLE student_course (
    student_id INT REFERENCES students(student_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    course_id INT REFERENCES courses(course_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    PRIMARY KEY (student_id, course_id)
);