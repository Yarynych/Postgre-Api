package ua.yarynych.apipostgre.repository;

public interface UserRepository {

    default String buildFilterQuery(StringBuilder sql, String username, String name, String surname) {

        if (username != null && !username.isEmpty()) {
            sql.append(" AND login LIKE '%").append(username).append("%'");
        }
        if (name != null && !name.isEmpty()) {
            sql.append(" AND first_name LIKE '%").append(name).append("%'");
        }
        if (surname != null && !surname.isEmpty()) {
            sql.append(" AND last_name LIKE '%").append(surname).append("%'");
        }

        return sql.toString();
    }
}
