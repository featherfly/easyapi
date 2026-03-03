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

@jakarta.annotation.Generated(value = "cn.featherfly.easyapi.codegen.v3.spring.EasyapiSpringMvcCodegen", date = "2026-03-03T15:45:34.387696200+08:00[Asia/Shanghai]")
@RestController
@RequestMapping(value = "/api/v1")
public class User2ApiController {
    private final User2Api delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public User2ApiController(User2Api delegate) {
        this.delegate = delegate;
    }

    /**
     * 更新用户基础资料
     * @param body survey object that needs to be added to the store

     */
    @Operation(summary = "更新用户基础资料", description = "更新用户基础资料", tags={ "user2" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation") })
    @RequestMapping(value = "/user/my",
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUserBasicData(@Parameter(in = ParameterIn.DEFAULT, description = "survey object that needs to be added to the store", required=true, schema=@Schema()) @Valid @RequestBody User body
) {
        delegate.updateUserBasicData(body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
