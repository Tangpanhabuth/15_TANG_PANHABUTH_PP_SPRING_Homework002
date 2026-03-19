package org.hrd.school_api.repository;

import org.apache.ibatis.annotations.*;
import org.hrd.school_api.dto.request.CourseRequest;
import org.hrd.school_api.dto.request.InstructorRequest;
import org.hrd.school_api.model.Course;
import org.hrd.school_api.model.Instructor;

import java.util.List;

@Mapper
public interface CourseRepo {
    @Results(id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructor", column = "instructor_id",
            one = @One (select = "org.hrd.school_api.repository.InstructorRepo.findInstructorById")
            )
    })

    @Select("SELECT * FROM courses LIMIT #{size} OFFSET (#{page} - 1) * #{size}")
    List<Course> findAllCourses(Integer page, Integer size);

    @ResultMap("courseMapper")
    @Select("""
        SELECT * FROM courses
        WHERE course_id = #{courseId}
    """)
    Course findCourseById(Integer courseId);

    @ResultMap("courseMapper")
    @Select("""
        INSERT INTO courses(course_name, description, instructor_id)
        VALUES (#{req.courseName}, #{req.description }, #{req.instructorId})
        RETURNING *;
    """)
    Course saveCourse(@Param("req") CourseRequest courseRequest);

    @ResultMap("courseMapper")
    @Delete("""
        DELETE FROM courses
        WHERE course_id = #{courseId}
    """)
    int removeCourseById(Integer courseId);

    @ResultMap("courseMapper")
    @Select("""
        UPDATE courses
        SET course_name = #{req.courseName},
            description = #{req.description},
            instructor_id = #{req.instructorId}
        WHERE course_id = #{id}
        RETURNING *
    """)
    Course updateCourse(@Param("id") Integer courseId, @Param("req") CourseRequest courseRequest);
}
