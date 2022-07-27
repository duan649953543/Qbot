package org.qbot.msgdeal;

import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.PlainText;
import org.qbot.toolkit.Setting;

/**
 * AdminMessageDeal class
 *
 * @author 649953543@qq.com
 * @date 2022/7/27
 */
public class AdminMessageDeal {
    private static final String AGREE_TO_FRIEND_REQUEST = "开启同意好友请求";

    private static final String DIS_AGREE_TO_FRIEND_REQUEST = "关闭同意好友请求";

    private static final String AGREE_TO_THE_GROUP_REQUEST = "开启同意入群请求";

    private static final String DIS_AGREE_TO_THE_GROUP_REQUEST = "关闭同意入群请求";

    private static final String OPEN_AI = "开启聊天";

    private static final String CLOSE_AI = "关闭聊天";

    private static final String OPEN_SEND_FORTUNE = "开启发送运势";

    private static final String CLOSE_SEND_FORTUNE = "关闭发送运势";

    private static final String OPEN_SEND_NEWS = "开启发送新闻";

    private static final String CLOSE_SEND_NEWS = "关闭发送新闻";

    private static final String OPEN_SEND_FISH = "开启发送摸鱼办";

    private static final String CLOSE_SEND_FISH = "关闭发送摸鱼办";

    private static final String SET_GROUP = "设置发送消息群号";

    private static final String DELETE_GROUP = "删除发送消息群号";

    private static final String OPEN_GROUP_MANAGEMENT = "开启群管系统";

    private static final String CLOSE_GROUP_MANAGEMENT = "关闭群管系统";


    public static void msgDel(String msg, Friend frind) {
        if (msg.contains(AGREE_TO_FRIEND_REQUEST)) {
            if (Setting.updateVersion("Friend", true)) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("同意好友请求已开启"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("同意好友请求开启失败"))
                        .build());
            }
        }
        if (msg.contains(DIS_AGREE_TO_FRIEND_REQUEST)) {
            if (Setting.updateVersion("Friend", false)) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("同意好友请求已关闭"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("同意好友请求关闭失败"))
                        .build());
            }
        }
        if (msg.contains(AGREE_TO_THE_GROUP_REQUEST)) {
            if (Setting.updateVersion("Group", true)) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("同意入群请求已开启"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("同意入群请求开启失败"))
                        .build());
            }
        }
        if (msg.contains(DIS_AGREE_TO_THE_GROUP_REQUEST)) {
            if (Setting.updateVersion("Group", false)) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("同意入群请求已关闭"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("同意入群请求关闭失败"))
                        .build());
            }

        }
        if (msg.contains(OPEN_AI)) {
            if (Setting.updateVersion("AI", true)) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("聊天已开启"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("聊天开启失败"))
                        .build());
            }

        }
        if (msg.contains(CLOSE_AI)) {
            if (Setting.updateVersion("AI", false)) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("聊天已关闭"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("聊天关闭失败"))
                        .build());
            }

        }
        if (msg.contains(OPEN_SEND_FORTUNE)) {
            if (Setting.updateVersion("Fortune", true)) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("发送运势已开启"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("发送运势开启失败"))
                        .build());
            }


        }
        if (msg.contains(CLOSE_SEND_FORTUNE)) {
            if (Setting.updateVersion("Fortune", false)) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("发送运势已关闭"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("发送运势关闭失败"))
                        .build());
            }


        }
        if (msg.contains(OPEN_SEND_NEWS)) {
            if (Setting.updateVersion("News", true)) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("发送新闻已开启"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("发送新闻开启失败"))
                        .build());
            }

        }
        if (msg.contains(CLOSE_SEND_NEWS)) {
            if (Setting.updateVersion("News", false)) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("发送新闻已关闭"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("发送新闻关闭失败"))
                        .build());
            }


        }
        if (msg.contains(OPEN_SEND_FISH)) {
            if (Setting.updateVersion("Fish", true)) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("发送摸鱼办已开启"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("发送摸鱼办开启失败"))
                        .build());
            }


        }
        if (msg.contains(CLOSE_SEND_FISH)) {
            if (Setting.updateVersion("Fish", false)) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("发送摸鱼办已关闭"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("发送摸鱼办关闭失败"))
                        .build());
            }

        }
        if (msg.contains(SET_GROUP)) {
            msg = msg.replace(SET_GROUP, "");
            msg = msg.replace(" ", "");
            if (Setting.updateVersion(true, Long.parseLong(msg))) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("设置群组成功"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("设置群组失败"))
                        .build());
            }
        }
        if (msg.contains(DELETE_GROUP)) {
            msg = msg.replace(DELETE_GROUP, "");
            msg = msg.replace(" ", "");
            if (Setting.updateVersion(false, Long.parseLong(msg))) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("删除群组成功"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("删除群组失败"))
                        .build());
            }
        }
        if (msg.contains(OPEN_GROUP_MANAGEMENT)) {
            if (Setting.updateVersion("GroupManagement", true)) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("群组管理已开启"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("群组管理开启失败"))
                        .build());
            }


        }
        if (msg.contains(CLOSE_GROUP_MANAGEMENT)) {
            if (Setting.updateVersion("GroupManagement", false)) {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("群组管理已关闭"))
                        .build());
            } else {
                frind.sendMessage(new MessageChainBuilder()
                        .append(new PlainText("群组管理关闭失败"))
                        .build());
            }
        }
    }
}