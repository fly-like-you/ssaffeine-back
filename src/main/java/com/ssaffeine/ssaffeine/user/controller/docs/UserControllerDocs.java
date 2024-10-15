package com.ssaffeine.ssaffeine.user.controller.docs;

import com.ssaffeine.ssaffeine.user.dto.request.UserRequestDto;
import com.ssaffeine.ssaffeine.user.dto.response.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/users")
public interface UserControllerDocs {

    /**
     * 사용자 회원 가입
     *
     * 권한: 누구나 접근 가능 (회원가입 절차)
     * @param userRequestDto 사용자 회원 가입에 필요한 정보
     * @return 생성된 사용자 정보
     */
    @Operation(summary = "회원 가입", description = "새로운 사용자를 회원으로 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원 가입 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터 (필수 필드 누락 또는 형식 오류)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping("/signup")
    ResponseEntity<UserResponseDto> signUp(
            @Parameter(description = "사용자 회원 가입에 필요한 정보", required = true)
            @RequestBody UserRequestDto userRequestDto);

    /**
     * 특정 사용자의 정보 조회
     *
     * 권한: 관리자만 접근 가능
     * @param userId 조회할 사용자의 ID
     * @return 사용자의 세부 정보
     */
    @Operation(summary = "사용자 정보 조회", description = "관리자가 특정 사용자의 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 정보 조회 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
    })
    @GetMapping("/{userId}")
    ResponseEntity<UserResponseDto> getUserById(
            @Parameter(description = "조회할 사용자의 UUID", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID userId);

    /**
     * 특정 사용자의 정보 업데이트
     *
     * 권한: 사용자 본인만 접근가능
     * @param userId 업데이트할 사용자의 ID
     * @param userRequestDto 수정할 사용자 정보
     * @return 수정된 사용자 정보
     */
    @Operation(summary = "사용자 정보 수정", description = "관리자가 특정 사용자의 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 정보 수정 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터 (필수 필드 누락 또는 형식 오류)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
    })
    @PutMapping("/{userId}")
    ResponseEntity<UserResponseDto> updateUser(
            @Parameter(description = "수정할 사용자의 UUID", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID userId,
            @Parameter(description = "수정할 사용자 정보", required = true)
            @RequestBody UserRequestDto userRequestDto);

    /**
     * 특정 사용자의 삭제
     *
     * 권한: 사용자 본인만 삭제가능
     * @param userId 삭제할 사용자의 ID
     * @return 콘텐츠 없음 (204 No Content)
     */
    @Operation(summary = "사용자 삭제", description = "관리자가 특정 사용자를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "사용자 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
    })
    @DeleteMapping("/{userId}")
    ResponseEntity<Void> deleteUser(
            @Parameter(description = "삭제할 사용자의 UUID", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID userId);
}
