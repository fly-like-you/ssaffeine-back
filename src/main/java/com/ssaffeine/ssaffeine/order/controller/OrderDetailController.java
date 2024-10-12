package com.ssaffeine.ssaffeine.order.controller;


import com.ssaffeine.ssaffeine.order.dto.OrderDetailDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    /**
     * 요일별로 주문을 조회하는 메서드
     * 중요도: 중
     * 권한: 사용자, 관리자
     * @param weekday 요일 비트마스크
     * @return 해당 요일에 대한 주문 리스트
     */
    @GetMapping("/byWeekday/{weekday}")
    public List<OrderDetailDto> getOrderDetailsByWeekday(@PathVariable int weekday) {
        // TODO: 요일에 따른 주문 내역을 조회하는 로직 추가
        return null;
    }

    /**
     * TODO: 주문 상태를 변경하는 메서드
     */
    @PatchMapping("/{orderDetailId}")
    public ResponseEntity 주문_상태_변경하기() {
        return null;
    }

    /**
     * 게시글에 주문할 수 있는가?
     * 중요도: 상
     * 권한: 사용자, 관리자
     * @return: 현재 게시글에 주문할 수 있으면 True
     */
    @GetMapping("/canOrder/{surveyId}")
    public boolean canOrder(@PathVariable Long surveyId) {
        // TODO: 현재 게시글의 상태를 확인하여 주문이 가능한지 판단
        return false;
    }

    /**
     * 특정 주문 조회
     * 중요도: 중
     * 권한: 사용자, 관리자
     * @param orderDetailId 조회할 주문 상세 ID
     * @return 조회된 주문 상세 정보
     */
    @GetMapping("/{orderDetailId}")
    public OrderDetailDto getOrderDetailById(@PathVariable Long orderDetailId) {
        // TODO: 주문 상세 ID에 따른 주문 내역 조회 로직 추가
        return null;
    }

    /**
     * 새로운 주문 추가
     * 중요도: 상
     * 권한: 사용자
     * @param orderDetailDTO 추가할 주문 정보
     * @return 추가된 주문 상세 정보
     */
    @PostMapping("/")
    public OrderDetailDto createOrderDetail(@RequestBody OrderDetailDto orderDetailDTO) {
        // TODO: 새로운 주문 추가를 위한 로직 작성 및 예외 처리
        return null;
    }

    /**
     * 주문 상태 수정
     * 중요도: 중
     * 권한: 관리자
     * @param orderId 주문 ID
     * @param newStatus 새로운 주문 상태
     * @return 수정된 주문 상세 정보
     */
    @PutMapping("/status/{orderId}")
    public OrderDetailDto updateOrderStatus(@PathVariable Long orderId, @RequestParam String newStatus) {
        // TODO: 주문 상태 업데이트 로직 작성 및 예외 처리
        return null;
    }

    /**
     * 주문 삭제
     * 중요도: 중
     * 권한: 사용자, 관리자
     * @param orderDetailId 삭제할 주문 상세 ID
     * @return 성공 여부
     */
    @DeleteMapping("/{orderDetailId}")
    public boolean deleteOrderDetail(@PathVariable Long orderDetailId) {
        // TODO: 주문 삭제를 위한 로직 작성 및 예외 처리
        return false;
    }
}
