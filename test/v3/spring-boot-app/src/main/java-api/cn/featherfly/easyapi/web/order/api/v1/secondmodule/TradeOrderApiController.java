package cn.featherfly.easyapi.web.order.api.v1.secondmodule;

import cn.featherfly.easyapi.web.order.api.v1.secondmodule.dto.OrderStatus;
import cn.featherfly.easyapi.web.order.api.v1.secondmodule.dto.PagingOrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;

@jakarta.annotation.Generated(value = "cn.featherfly.easyapi.codegen.v3.spring.EasyapiSpringMvcCodegen", date = "2026-03-03T15:45:34.792214800+08:00[Asia/Shanghai]")
@RestController
@RequestMapping(value = "/api/v1")
public class TradeOrderApiController {
    private final TradeOrderApi delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public TradeOrderApiController(TradeOrderApi delegate) {
        this.delegate = delegate;
    }

    /**
     * 获取个人订单
     * @param status 订单号
     * @param page auto generate page with request parameters
     * @param pagination auto generate pagination with request parameters
     * @param request http servlet request
     * @param response http servlet response
     * @param file multipart file
     * @param multipartRequest multipart http servlet request
     * @return PagingOrderDto 
     */
    @Operation(summary = "获取个人订单", description = "获取个人订单 ", tags={ "trade-order" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PagingOrderDto.class))) })
    @RequestMapping(value = "/orders/my",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    public ResponseEntity<PagingOrderDto> getMyOrders(@Parameter(in = ParameterIn.QUERY, description = "订单号" ,schema=@Schema()) @Valid @RequestParam(value = "status", required = false) OrderStatus status
,
@Parameter(hidden = true) cn.featherfly.common.structure.page.Page page,
@Parameter(hidden = true) cn.featherfly.common.structure.page.Pagination pagination,
@Parameter(hidden = true) jakarta.servlet.http.HttpServletRequest request,
@Parameter(hidden = true) jakarta.servlet.http.HttpServletResponse response,
@Parameter(hidden = true) org.springframework.web.multipart.MultipartFile file,
@Parameter(hidden = true) org.springframework.web.multipart.MultipartHttpServletRequest multipartRequest) {
        return new ResponseEntity<>(delegate.getMyOrders(status, page, pagination, request, response, file, multipartRequest), HttpStatus.OK);
    }

}
