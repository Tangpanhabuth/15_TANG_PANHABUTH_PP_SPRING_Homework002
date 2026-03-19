package org.hrd.school_api.repository;

import org.apache.ibatis.annotations.*;
import org.hrd.school_api.model.Course;
import java.util.List;

@Mapper
public interface StudentCourseRepository {

    @Results(id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One (select = "org.hrd.school_api.repository.InstructorRepo.findInstructorById")
            )
    })
    @Select("""
        SELECT *
            FROM courses c
            INNER JOIN student_course sc
                ON c.course_id = sc.course_id
            WHERE sc.student_id = #{studentId}
    """)
    List<Course> findAllCoursesByStudentId(Integer studentId);

    @ResultMap("courseMapper")
    @Insert("""
    INSERT INTO student_course(student_id, course_id)
    VALUES (#{studentId}, #{courseId})
""")
    void addStudentToCourse(Integer studentId, Integer courseId);
}
