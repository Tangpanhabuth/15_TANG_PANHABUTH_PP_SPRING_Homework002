package org.hrd.school_api.repository;

import org.apache.ibatis.annotations.*;
import org.hrd.school_api.dto.request.CourseRequest;
import org.hrd.school_api.dto.request.StudentRequest;
import org.hrd.school_api.model.Course;
import org.hrd.school_api.model.Student;

import java.util.List;

@Mapper
public interface StudentRepo {

    @Results(id = "studentMapper", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "course" , column = "student_id",
                    many = @Many(select = "org.hrd.school_api.repository.StudentCourseRepository.findAllCoursesByStudentId")

            )
    })
    @Select("SELECT * FROM students LIMIT #{size} OFFSET (#{page} - 1) * #{size}")
    List<Student> findAllStudents(Integer page, Integer size);

    @ResultMap("studentMapper")
    @Select("""
        SELECT * FROM students
        WHERE student_id = #{studentId};
    """)
    Student findStudentById(Integer studentId);

    @ResultMap("studentMapper")
    @Delete("""
        DELETE FROM students
        WHERE student_id = #{studentId}
    """)
    int removeStudentById(Integer studentId);

    @ResultMap("studentMapper")
    @Select("""
        INSERT INTO students(student_name, email, phone_number)
        VALUES (#{req.studentName},#{req.email }, #{req.phoneNumber})
        RETURNING *;
    """)
    Student createStudent(@Param("req") StudentRequest studentRequest);

    @ResultMap("studentMapper")
    @Select("""
        UPDATE students SET student_name =#{res.studentName},
                            email=#{res.email},
                            phone_number=#{res.phoneNumber}
                            WHERE student_id = #{studentId} RETURNING *
    """)
    Student updateStudent(Integer studentId, @Param("res") StudentRequest studentRequest);
}
