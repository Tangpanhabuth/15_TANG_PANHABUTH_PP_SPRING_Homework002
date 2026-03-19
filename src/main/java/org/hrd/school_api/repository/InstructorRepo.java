package org.hrd.school_api.repository;


import org.apache.ibatis.annotations.*;
import org.hrd.school_api.dto.request.InstructorRequest;
import org.hrd.school_api.model.Instructor;

import java.util.List;

@Mapper
public interface InstructorRepo {
    @Results(id = "instructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name"),
            @Result(property = "email", column = "email"),
    })
    @Select("SELECT * FROM instructors LIMIT #{size} OFFSET (#{page} - 1) * #{size}")
    List<Instructor> findAllInstructors(Integer page, Integer size);

    @ResultMap("instructorMapper")
    @Select("""
        SELECT * FROM instructors
        WHERE instructor_id = #{instructorId}
    """)
    Instructor findInstructorById(Integer instructorId);

    @ResultMap("instructorMapper")
    @Select("""
        INSERT INTO instructors(instructor_name, email)
        VALUES (#{req.instructorName}, #{req.email})
        RETURNING *;
    """)
    Instructor saveInstructor(@Param("req") InstructorRequest instructorRequest);


    @Select("""
        UPDATE instructors
        SET instructor_name = #{req.instructorName},
            email = #{req.email}
        WHERE instructor_id = #{id}
        RETURNING *
    """)
    @ResultMap("instructorMapper")
    Instructor updateInstructor(@Param("id") Integer id, @Param("req") InstructorRequest instructorRequest);


    @ResultMap("instructorMapper")
    @Select("""
        DELETE FROM instructors
        WHERE instructor_id = #{instructorId}
    """)
    Instructor removeInstructor(Integer instructorId);
}
