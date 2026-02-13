package cn.featherfly.easyapi.web.order.api.v1.secondmodule;

import cn.featherfly.easyapi.web.order.api.v1.secondmodule.dto.OrderStatus;
import cn.featherfly.easyapi.web.order.api.v1.secondmodule.dto.PagingOrderDto;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegate to be called by the {@link TradeOrderApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@jakarta.annotation.Generated(value = "cn.featherfly.easyapi.codegen.v3.spring.EasyapiSpringMvcCodegen", date = "2026-02-06T16:12:03.352420200+08:00[Asia/Shanghai]")
public interface TradeOrderApi {

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
    PagingOrderDto getMyOrders( OrderStatus  status
    , cn.featherfly.common.structure.page.Page  page
    , cn.featherfly.common.structure.page.Pagination  pagination
    , jakarta.servlet.http.HttpServletRequest  request
    , jakarta.servlet.http.HttpServletResponse  response
    , org.springframework.web.multipart.MultipartFile  file
    , org.springframework.web.multipart.MultipartHttpServletRequest  multipartRequest
    );

}
