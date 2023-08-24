$(function () {

    if ($.websocket) {
        var sitePath = appConfig.cmsPath;

        var schemes = {"http://": "ws://", "https://": "wss://"};
        var host, scheme;

        $.each(schemes, function (k, v) {
            if (sitePath.startsWith(k)) {
                scheme = v;
                host = sitePath.replaceAll(k, "");
                return false;
            }
        });
        // 默认取8085端口的程序
        host = host || document.domain + ":8888";
        console.log(document.domain);
        var url = "";
        if (host) {
            if (document.domain.startsWith("15862518424.gnway.cc")) {
                url = "ws://" + document.domain + sitePath+"websocket";
            } else {
                url = "ws://" + host +sitePath+ "websocket";
            }
            $.websocket.open({
                // host: scheme + host + "/websocket",
                host: url,
                reconnect: true,
                callback: function (result) {
                    var resultJson = JSON.parse(result);
                    wesocketMsgResolver[resultJson["fun"]](resultJson["msg"]);
                }
            });
        } else {
            console.warn("网站host获取失败，将不启动webscoket。");
        }
    }
});

/**
 * websocket消息解析器
 *
 * @type {{online: wesocketMsgResolver.online}}
 */
var wesocketMsgResolver = {
    online: function (value) {
        value && $("#online").html(value);
    },
    notification: function (value) {
        value && location.reload();
    }
};