package com.esen.java_kanban_rework.utils;
import com.esen.java_kanban_rework.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("dev") // This will only run in the "dev" profile
public class BootstrapData {
//    private final UserRepository userRepository;
//
//    public DataBootstrap(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    private void parseCsvString() {
//        String csvData = """
//                id,name,email
//                1,John Doe,john@example.com
//                2,Jane Smith,jane@example.com
//                3,Bob Johnson,bob@example.com
//                """;
//
//        List<User> users = new ArrayList<>();
//        String[] lines = csvData.split("\n");
//
//        for (int i = 1; i < lines.length; i++) { // Skip the header
//            String[] columns = lines[i].split(",");
//            if (columns.length == 3) {
//                User user = new User();
//                user.setId(Long.parseLong(columns[0]));
//                user.setName(columns[1]);
//                user.setEmail(columns[2]);
//                users.add(user);
//            }
//        }
        return;
    }

}
