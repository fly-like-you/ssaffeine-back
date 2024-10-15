package com.ssaffeine.ssaffeine.surveys.controller;

import com.ssaffeine.ssaffeine.surveys.dto.SurveyRequestDto;
import com.ssaffeine.ssaffeine.surveys.dto.SurveyResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/survey")
public interface SurveyControllerDocs {

    /**
     * 현재 게시글에 주문이 가능한지 확인
     *
     * 권한: 사용자, 관리자
     * @return 게시글에 주문이 가능하면 True 반환
     */
    @Operation(summary = "게시글 주문 가능 여부 확인", description = "현재 게시글에 주문할 수 있는지 여부를 확인합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "주문 가능"),
            @ApiResponse(responseCode = "400", description = "주문 불가")
    })
    @GetMapping("/can-order")
    ResponseEntity<Void> canOrderToSurvey();

    /**
     * 새로운 게시글 작성
     *
     * 권한: 관리자
     * @param surveyRequestDto 게시글의 제목, 내용 등 정보
     * @return 작성된 게시글 정보
     */
    @Operation(summary = "새 게시글 작성", description = "관리자가 새로운 게시글을 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "게시글이 성공적으로 작성되었습니다.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SurveyResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 요청 (유저 정보, 본문, 제목 등)",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "설문을 삭제할 권한이 없음",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    ResponseEntity<SurveyResponseDto> createSurvey(
            @Parameter(description = "새 게시글 작성에 필요한 정보", required = true)
            @RequestBody SurveyRequestDto surveyRequestDto);

    /**
     * 특정 설문 조회
     *
     * 권한: 관리자, 사용자
     * @param surveyId 조회하려는 설문의 ID
     * @return 설문 세부 정보
     */
    @Operation(summary = "특정 설문 조회", description = "관리자 또는 사용자가 특정 ID의 설문을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "설문 조회 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SurveyResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "설문을 찾을 수 없음",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{surveyId}")
    ResponseEntity<SurveyResponseDto> getSurveyById(
            @Parameter(description = "조회할 게시글의 ID", required = true, example = "1")
            @PathVariable Long surveyId);

    /**
     * 게시글 수정
     *
     * 권한: 관리자
     * @param surveyId 수정하려는 게시글의 ID
     * @param surveyRequestDto 수정할 게시글의 제목, 내용 등 정보
     * @return 수정된 게시글 정보
     */
    @Operation(summary = "설문 수정", description = "관리자가 특정 설문의 내용을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "설문 수정 성공",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SurveyResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 요청 데이터",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "설문을 삭제할 권한이 없음",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "설문을 찾을 수 없음",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/{surveyId}")
    ResponseEntity<SurveyResponseDto> updateSurvey(
            @Parameter(description = "수정할 게시글의 ID", required = true, example = "1")
            @PathVariable Long surveyId,
            @Parameter(description = "게시글 수정에 필요한 정보", required = true)
            @RequestBody SurveyRequestDto surveyRequestDto);

    /**
     * 게시글 삭제
     *
     * 권한: 관리자
     * @param surveyId 삭제하려는 게시글의 ID
     * @return 응답 없음 (204 No Content)
     */
    @Operation(summary = "설문 삭제", description = "관리자가 특정 설문을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "설문 삭제 성공"),
            @ApiResponse(responseCode = "401", description = "설문을 삭제할 권한이 없음",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "설문을 찾을 수 없음",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = @Content(mediaType = "application/json"))
            //
    })
    @DeleteMapping("/{surveyId}")
    ResponseEntity<Void> deleteSurvey(
            @Parameter(description = "삭제할 설문의 ID", required = true, example = "1")
            @PathVariable Long surveyId);
}
