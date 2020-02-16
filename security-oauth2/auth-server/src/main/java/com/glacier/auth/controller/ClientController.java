package com.glacier.auth.controller;

import com.glacier.auth.entity.dto.OauthClientDto;
import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.core.http.HttpResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户端 处理
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-15 14:17
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/client")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController {

    private final JdbcClientDetailsService clientDetailsService;

    /**
     * 新增客户端
     *
     * @param baseClientDetails
     * @return
     */
    @PostMapping("/add")
    public HttpResult<String> add(@RequestBody BaseClientDetails baseClientDetails) {
        HttpResult<String> result = null;
        try {
            clientDetailsService.addClientDetails(baseClientDetails);
            result = HttpResult.ok("客户端添加成功！", null);
        } catch (ClientAlreadyExistsException e) {
            log.warn("客户端已存在，不能重复添加！", e);
            result = HttpResult.error("客户端已存在，不能重复添加！");
        }
        return result;
    }

    /**
     * 更新客户端
     *
     * @param baseClientDetails
     * @return
     */
    @PostMapping("/update")
    public HttpResult<String> update(@RequestBody BaseClientDetails baseClientDetails) {
        HttpResult<String> result = null;
        try {
            clientDetailsService.updateClientDetails(baseClientDetails);
            result = HttpResult.ok("客户端更新成功！", null);
        } catch (NoSuchClientException e) {
            log.error("客户端不存在，不能更新！", e);
            result = HttpResult.error("客户端不存在，不能更新！");
        }
        return result;
    }

    /**
     * 更新客户端密码
     *
     * @param detailsDto
     * @return
     */
    @PostMapping("/updateSecret")
    public HttpResult<String> updateSecret(@RequestBody OauthClientDto detailsDto) {
        HttpResult<String> result = null;
        try {
            clientDetailsService.updateClientSecret(detailsDto.getClientId(), detailsDto.getClientSecret());
            result = HttpResult.ok("密码更新成功", null);
        } catch (NoSuchClientException e) {
            log.error("客户端不存在，不能更新！", e);
            result = HttpResult.error("客户端不存在，不能更新！");
        }
        return result;
    }

    /**
     * 删除指定客户端
     *
     * @param idDtos
     * @return
     */
    @PostMapping("/remove")
    public HttpResult<String> remove(@RequestBody List<IdDto> idDtos) {
        HttpResult<String> result = HttpResult.ok();
        ;
        if (idDtos != null && !idDtos.isEmpty()) {
            for (IdDto idDto : idDtos) {
                try {
                    clientDetailsService.removeClientDetails(idDto.getId());
                } catch (NoSuchClientException e) {
                    log.error("客户端不存在，不能删除！", e);
                    result = HttpResult.error("客户端不存在，不能删除！");
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 查询所有客户端
     *
     * @return
     */
    @GetMapping("/list")
    public HttpResult<List<ClientDetails>> list() {
        return HttpResult.ok(clientDetailsService.listClientDetails());
    }
}
