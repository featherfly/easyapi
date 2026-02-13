package cn.featherfly.easyapi.web.order.api.v1.secondmodule;

import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.CollectionUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Randoms;
import cn.featherfly.common.structure.page.Page;
import cn.featherfly.common.structure.page.Pagination;
import cn.featherfly.easyapi.web.order.api.v1.secondmodule.dto.OrderDto;
import cn.featherfly.easyapi.web.order.api.v1.secondmodule.dto.OrderStatus;
import cn.featherfly.easyapi.web.order.api.v1.secondmodule.dto.PagingOrderDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.stream.IntStreams;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The type TradeOrderApiImpl.
 *
 * @author zhongj
 */
@Component
public class TradeOrderApiImpl implements TradeOrderApi {

    @Override
    public PagingOrderDto getMyOrders(OrderStatus status, Page page, Pagination pagination, HttpServletRequest request, HttpServletResponse response, MultipartFile file, MultipartHttpServletRequest multipartRequest) {
        PagingOrderDto pagingOrderDto = new PagingOrderDto();
        pagingOrderDto.setTotal(101);
        pagingOrderDto.setPageNumber(Randoms.getInt(10));
        pagingOrderDto.setContent(IntStream.range(0, 10).mapToObj(operand -> createOrderDto(operand)).collect(Collectors.toList()));
        return pagingOrderDto;
    }

    private OrderDto createOrderDto(int id) {
        OrderDto order = new OrderDto();
        order.setId(Long.valueOf(id));
        order.setName("name_" + id);
        return order;
    }
}
