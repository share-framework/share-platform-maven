package org.andot.share.app.line.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.andot.share.app.line.core.domain.ChatLog;
import org.andot.share.app.line.core.domain.ComLineMessage;
import org.andot.share.app.line.core.domain.Conversation;
import org.andot.share.app.line.core.domain.enums.MessageType;
import org.andot.share.app.line.core.resp.ConversationVo;
import org.andot.share.app.line.service.ChatLogService;
import org.andot.share.app.line.service.ConversationService;
import org.andot.share.app.line.service.MessageService;
import org.andot.share.basic.annotation.PageStart;
import org.andot.share.basic.dto.PageDTO;
import org.andot.share.basic.entity.UserDetail;
import org.andot.share.basic.service.UserService;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.andot.share.core.dto.RoleDTO;
import org.andot.share.core.util.CurrentUserUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ConversationController
 *
 * @Description TODO
 * @Author lucasser
 * @Date 2023/6/25 20:44
 * @since 1.0
 */
@Api(tags = "会话API", description = "会话请求控制器")
@RestController
@RequestMapping("/conversation")
public class ConversationController {

    @Resource
    private ConversationService conversationService;
    @Resource
    private MessageService mongoMessageService;
    @Resource
    private UserService userService;

    @ApiOperation("最近会话列表")
    @GetMapping("/list")
    public CommonResult messageList(@RequestParam Long xNumber) {
        List<Conversation> conversationList = conversationService.getListByXNumber(xNumber);
        if (CollectionUtils.isNotEmpty(conversationList)) {
            List<ConversationVo> list = conversationList.stream().map(conversation -> {
                ConversationVo conversationVo = new ConversationVo();
                conversationVo.setConversationId(conversation.getConversationId());
                conversationVo.setConversationType(conversation.getConversationType());
                conversationVo.setOwnerUserId(conversation.getOwnerUserId());
                conversationVo.setUserId(conversation.getUserId());
                Long acceptUserId = conversation.getUserId();
                UserDetail userDetail = userService.getUserDetail(acceptUserId);
                conversationVo.setAcceptPersonName(userDetail.getRealName());
                UserDetail sendUserDetail = userService.getUserDetail(conversation.getOwnerUserId());
                conversationVo.setSendPersonName(sendUserDetail.getRealName());
                ComLineMessage comLineMessage = mongoMessageService.getLastConversationMessage(conversation.getConversationId());
                if (Objects.nonNull(comLineMessage)) {
                    conversationVo.setLastConversation(comLineMessage.getBody().getContent());
                    conversationVo.setLastConversationTime(new Date(comLineMessage.getTime()));
                }
                return conversationVo;
            }).collect(Collectors.toList());
            return CommonResult.success(list);
        }
        return CommonResult.success(conversationList);
    }

    @ApiOperation("打开会话")
    @PageStart
    @GetMapping("/open")
    public CommonPage openConversation(PageDTO<String> chatLogPage) {
//        List<ChatLog> chatLogs = chatLogService.getChatLogsByConversationId(chatLogPage.getParam());
        List<ComLineMessage> chatLogs = mongoMessageService.getConversationMessageList(chatLogPage.getParam());
        return CommonPage.restPage(chatLogs);
    }

    @ApiOperation("创建会话")
    @PostMapping("/{rcvXNumber}")
    public CommonResult createConversation(@PathVariable("rcvXNumber") Long rcvXNumber) {
        Long xNumber = CurrentUserUtil.getUserCode();
        String conversationId = "single_"+xNumber+"_"+rcvXNumber;
        Conversation conversation = conversationService.getById(conversationId);
        if (Objects.isNull(conversation)) {
            conversation = new Conversation();
            conversation.setConversationId(conversationId);
            conversation.setConversationType(MessageType.TEXT.getCode());
            conversation.setOwnerUserId(xNumber);
            conversation.setUserId(rcvXNumber);
            conversationService.save(conversation);
        }
        return CommonResult.success(conversation);
    }

    @ApiOperation("更新会话")
    @PostMapping("/update")
    public CommonResult updateConversation(@RequestBody Conversation conversation) {
        conversationService.updateById(conversation);
        return CommonResult.success();
    }

    @ApiOperation("删除会话")
    @DeleteMapping("")
    public CommonResult delConversation(@RequestBody String conversationId) {
        conversationService.delConversation(conversationId);
        return CommonResult.success();
    }
}
