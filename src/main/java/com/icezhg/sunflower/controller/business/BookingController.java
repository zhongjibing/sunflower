package com.icezhg.sunflower.controller.business;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.BookingInfo;
import com.icezhg.sunflower.pojo.BookingObject;
import com.icezhg.sunflower.pojo.query.BookingQuery;
import com.icezhg.sunflower.service.BookingService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhongjibing on 2023/08/02.
 */
@RestController
@RequestMapping("/business/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @Secured({Authority.Wx.ADMIN, Authority.Wx.USER})
    @Operation(title = "booking creation", type = OperationType.INSERT)
    public Object booking(@RequestBody BookingObject bookingObject) {
        return bookingService.create(bookingObject);
    }

    @PutMapping
    @Secured(Authority.Wx.ADMIN)
    @Operation(title = "booking modification", type = OperationType.UPDATE)
    public Object bookingModify(@RequestBody BookingInfo bookingInfo) {
        return bookingService.modify(bookingInfo);
    }

    @GetMapping("/list")
    @Secured({Authority.Wx.ADMIN, Authority.Wx.USER})
    public Object bookingList(BookingQuery query) {
        return bookingService.find(query);
    }

    @GetMapping("/{id:\\d+}")
    @Secured({Authority.Wx.ADMIN, Authority.Wx.USER})
    public Object bookingInfo(@PathVariable Long id) {
        return bookingService.findById(id);
    }

    @PutMapping("/confirm")
    @Secured(Authority.Wx.ADMIN)
    @Operation(title = "booking confirmed", type = OperationType.UPDATE)
    public int bookingConfirm(@RequestBody BookingInfo bookingInfo) {
        return bookingService.confirm(bookingInfo);
    }

    @PutMapping("/cancel")
    @Secured({Authority.Wx.ADMIN, Authority.Wx.USER})
    @Operation(title = "booking canceled", type = OperationType.UPDATE)
    public int bookingCancel(@RequestBody BookingInfo bookingInfo) {
        return bookingService.cancel(bookingInfo);
    }
}
