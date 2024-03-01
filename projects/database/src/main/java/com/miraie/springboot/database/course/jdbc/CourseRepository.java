package com.miraie.springboot.database.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CourseRepository {
    @Autowired
    private JdbcTemplate template;

    private static final String INSERT_QUERY_STATIC =
            """
                    insert into course (id, name, author)
                    values (1, 'Algorithms', 'Princeton');
                    """;

    private static final String INSERT_QUERY_DYNAMIC =
            """
                    insert into course (id, name, author)
                    values (?, ?, ?);
                    """;

    private static final String DELETE_BY_ID =
            """
                     delete from course
                     where id = ?;
                    """;

    private static final String SELECT_QUERY =
            """
                     select * from course
                     where id = ?;
                    """;

    public void insertStatic () {
        template.update(INSERT_QUERY_STATIC);
    }

    public void insertDynamic (Course course) {
        template.update(INSERT_QUERY_DYNAMIC, course.getId(), course.getName(), course.getAuthor());
    }

    public void deleteById (int id) {
        template.update(DELETE_BY_ID, id);
    }

    public Course findById (int id) {
        return template.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class), id);
    }
}
