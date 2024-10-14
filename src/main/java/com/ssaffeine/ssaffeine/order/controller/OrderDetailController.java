package com.ssaffeine.ssaffeine.order.controller;


import com.ssaffeine.ssaffeine.order.dto.OrderDetailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    // 사용자는 order가 결제 대기 상태 전까지 "자신"의 order_detail 을 CRUD할 수 있다. 결제 대기 상태가 지나면 조회만 가능하다.
    // 사용자는 하나의 요일에 하나만 주문을 생성할 수 있다.
    // 관리자는 사용자가 어떤 주문을 했는지 조회할 수 있다 (survey 별로 조회할 수 있는데 그건 Order 컨트롤러에서 확인가능)



    /**
     * 요일별로 주문을 조회하는 메서드
     * 중요도: 중
     * 권한: 사용자, 관리자
     * @param weekday 요일 비트마스크
     * @return 해당 요일에 대한 주문 리스트
     */
    @GetMapping("/byWeekday/{weekday}")
    public List<OrderDetailDto> getOrderDetailsByWeekday(@PathVariable int weekday) {

        // TODO: 요일에 따른 주문 내역을 조회하는 로직 추가, DTO( 수령 여부, 반, 이름, 옵션)
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
