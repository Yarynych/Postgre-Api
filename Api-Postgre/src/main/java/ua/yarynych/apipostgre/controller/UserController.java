package ua.yarynych.apipostgre.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.yarynych.apipostgre.entity.User;
import ua.yarynych.apipostgre.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users API", description = "API for getting users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get a list of all users", responses = {
            @ApiResponse(description = "Success response", responseCode = "200",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
    })
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Get a list of users with filters", responses = {
            @ApiResponse(description = "Success response", responseCode = "200",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
    })
    @GetMapping("/filter")
    public ResponseEntity<List<User>> getUsersWithFilters(@RequestParam(required = false) String username,
                                                          @RequestParam(required = false) String name,
                                                          @RequestParam(required = false) String surname) {
        return ResponseEntity.ok(userService.getUsersWithFilters(username, name, surname));
    }
}
