package cn.featherfly.easyapi.web.user.api.v1.secondmodule;

import cn.featherfly.easyapi.web.user.api.v1.secondmodule.dto.User;
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

@jakarta.annotation.Generated(value = "cn.featherfly.easyapi.codegen.v3.spring.EasyapiSpringMvcCodegen", date = "2026-02-06T16:12:02.900418+08:00[Asia/Shanghai]")
@RestController
@RequestMapping(value = "/api/v1")
public class UserApiController {
    private final UserApi delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(UserApi delegate) {
        this.delegate = delegate;
    }

    /**
     * 获取当前用户的用户信息
     * @param id id
     * @param page auto generate page with request parameters
     * @return User 
     */
    @Operation(summary = "获取当前用户的用户信息", description = "@Login @Page ", tags={ "user" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))) })
    @RequestMapping(value = "/user/my",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    public ResponseEntity<User> getUserMySelf(@Parameter(in = ParameterIn.QUERY, description = "id" ,schema=@Schema()) @Valid @RequestParam(value = "id", required = false) Long id
,
@Parameter(hidden = true) cn.featherfly.common.structure.page.Page page) {
        return new ResponseEntity<>(delegate.getUserMySelf(id, page), HttpStatus.OK);
    }

}
