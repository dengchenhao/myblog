<#include "include/macros.ftl">
<@compress single_line=false>
<@header title="相册 | ${config.siteName}" canonical="/guestbook" hasEditor=true>
</@header>

<div class="container custome-container">
    <nav class="breadcrumb">
        <a class="crumbs" title="返回首页" href="${config.siteUrl}" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-home"></i>首页</a>
    </nav>
    <div class="row guestbook-body">
        <@blogHeader title="相册"></@blogHeader>

        <div class="x_content photoAlbum-container" id="photoAlbum-container">
            <div class="col-md-55">
                <div class="albumnail">
                    <div class="image view view-first">
                        <p>暂无相册</p>
                    </div>
                </div>
            </div>
        </div>

        <HR align=center width=100% color=#987cb9 SIZE=1>
    </div>
    <div class="x_panel">
        <form id="file-form">
            <div class="x_content file-container" id="file-container">
                <div class="col-md-55">
                    <div class="thumbnail">
                        <div class="image view view-first">
                            <img style="display: block;margin: 0 auto;margin-top: 10px;" src="${config.siteUrl}/img/loading.gif" alt="image" />
                        </div>
                        <div class="caption">
                            <p>暂无可用的图片，请重新选择相册... </p>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<@footer>

    <script>
        var curSstorageType = '${config.storageType}';
        $(function () {
        });
            loadData(1, -1);
            loadPhotoAlbum(1);

            function loadData(pageNumber, albumId){
                $.ajax({
                    url: "${config.siteUrl}/photo/list",
                    data: {pageNumber: pageNumber, albumId:albumId},
                    // data: {pageNumber: pageNumber},
                    type: "POST",
                    success: function (json) {
                        var tpl = '{{#list}}<div class="col-md-55">\n' +
                            '                    <div class="thumbnail">\n' +
                            '                        <div class="image view view-first pointer file-item">\n' +
                            '                            <img style="width: 100%; display: block;" src="{{fullFilePath}}" onerror="this.alt=\'图片加载失败\'" alt="{{originalFileName}}" title="{{originalFileName}}" />\n' +
                            '                            <div class="vmask">\n' +
                            '                                <div class="tools tools-bottom">\n' +
                            '                                    <a href="{{fullFilePath}}" class="file-icon showImage" title="{{filePath}}"><i class="fa fa-eye"></i></a>\n' +
                            '                                    <a href="{{fullFilePath}}" target="_blank" class="file-icon" title="复制地址（打开标签后复制）"><i class="fa fa-link"></i></a>\n' +
                            '                                </div>\n' +
                            '                            </div>\n' +
                            '                        </div>\n' +
                            '                        <div class="caption">\n' +
                            '                            <p><span title="{{originalFileName}}">{{originalFileName}}</span><img src="${config.siteUrl}/img/{{storageType}}.svg" alt="{{storageType}}" title="{{storageType}}"></p>\n' +
                            '                        </div>\n' +
                            '                    </div>\n' +
                            '                </div>{{/list}}';
                        var html = Mustache.render(tpl, json);
                        var pageTpl = '<ul class="list-unstyled">{{#data}}<li class="file-page">\n' +
                            '    <div class="file-page-body">\n' +
                            '        {{#hasPreviousPage}}<a class="btn btn-default btn-sm file-pagination" data-page="{{prePage}}">\n' +
                            '            <i class="fa fa-caret-left"></i>\n' +
                            '        </a>{{/hasPreviousPage}}<span style="margin-right: 5px;">{{pageNum}}/{{pages}}</span>{{#hasNextPage}}<a class="btn btn-default btn-sm file-pagination" data-page="{{nextPage}}">\n' +
                            '            <i class="fa fa-caret-right"></i>\n' +
                            '        </a>{{/hasNextPage}}<input class="form-control input-sm file-input">\n' +
                            '    <a class="btn btn-default btn-sm file-jump">\n' +
                            '            Go\n' +
                            '        </a>\n' +
                            '    \n' +
                            '    </div>\n' +
                            '</li>{{/data}}</ul>';
                        html += Mustache.render(pageTpl, {data: json});
                        $("#file-container").html(html);

                        // 绑定分页点击事件
                        $(".file-pagination").unbind("click").click(function () {
                            var $this = $(this);
                            var pageNumber = $this.data("page");
                            loadData(!pageNumber || isNaN(pageNumber) ? 1 : parseInt(pageNumber), albumId);
                        });
                        // 绑定分页-跳转页面点击事件
                        $(".file-jump").unbind("click").click(function () {
                            var $this = $(this);
                            var jumpTarget = $(".file-input").val();
                            loadData(!jumpTarget || isNaN(jumpTarget) ? 1 : parseInt(jumpTarget), albumId);
                        });

                        // gentelella.initiICheck();
                        $(".file-icon").click(function () {
                            $(".file-item").unbind("click");
                            var event = $(this).data("event");
                            var id = $(this).data("value");
                            var storageType = $(this).data("storage-type");
                            if(event) {
                                del({'ids': id}, storageType);
                            } else {
                                setTimeout(function () {
                                    bindFileItemEvent();
                                })
                            }
                        });
                    },
                    error: $.alert.ajaxError
                });
            }

            // 加载相册
            function loadPhotoAlbum(pageNumber){
                $.ajax({
                    url: "${config.siteUrl}/bizPhotoAlbum/list",
                    data: {pageNumber: pageNumber},
                    // data: {pageNumber: pageNumber},
                    type: "POST",
                    success: function (json) {
                        var tpl = '{{#list}}<div class="col-md-55">\n' +
                            '                    <div class="albumnail">\n' +
                            '                        <div class="image view view-first pointer file-item">\n' +
                            '                            <img style="width: 100%; display: block;" src="{{cover}}" onerror="this.alt=\'图片加载失败\'" alt="{{name}}" title="{{name}}" />\n' +
                            '                            <div class="vmask">\n' +
                            '                                <div class="tools tools-bottom">\n' +
                            '                                    <button id="search-button" class="btn btn-primary" onclick="loadData(1, {{id}})"><i class="fa fa-search"></i>查看相册</button>\n' +
                            '                                </div>\n' +
                            '                            </div>\n' +
                            '                        </div>\n' +
                            '                        <div class="caption">\n' +
                            '                            <p><span title="{{name}}">{{name}}</span><img src="${config.siteUrl}/img/local.svg" alt="{{storageType}}" title="{{name}}"></p>\n' +
                            '                        </div>\n' +
                            '                    </div>\n' +
                            '                </div>{{/list}}';
                        var html = Mustache.render(tpl, json);
                        var pageTpl = '<ul class="list-unstyled">{{#data}}<li class="file-page">\n' +
                            '    <div class="file-page-body">\n' +
                            '        {{#hasPreviousPage}}<a class="btn btn-default btn-sm file-pagination" data-page="{{prePage}}">\n' +
                            '            <i class="fa fa-caret-left"></i>\n' +
                            '        </a>{{/hasPreviousPage}}<span style="margin-right: 5px;">{{pageNum}}/{{pages}}</span>{{#hasNextPage}}<a class="btn btn-default btn-sm file-pagination" data-page="{{nextPage}}">\n' +
                            '            <i class="fa fa-caret-right"></i>\n' +
                            '        </a>{{/hasNextPage}}<input class="form-control input-sm file-input">\n' +
                            '    <a class="btn btn-default btn-sm file-jump">\n' +
                            '            Go\n' +
                            '        </a>\n' +
                            '    \n' +
                            '    </div>\n' +
                            '</li>{{/data}}</ul>';
                        html += Mustache.render(pageTpl, {data: json});
                        $("#photoAlbum-container").html(html);

                        // 绑定分页点击事件
                        $(".photoAlbum-pagination").unbind("click").click(function () {
                            var $this = $(this);
                            var pageNumber = $this.data("page");
                            var albumId = $this.data("album");
                            loadPhotoAlbum(!pageNumber || isNaN(pageNumber) ? 1 : parseInt(pageNumber));
                        });
                        // 绑定分页-跳转页面点击事件
                        $(".photoAlbum-jump").unbind("click").click(function () {
                            var $this = $(this);
                            loadPhotoAlbum(!jumpTarget || isNaN(jumpTarget) ? 1 : parseInt(jumpTarget));
                        });

                        // gentelella.initiICheck();
                        $(".photoAlbum-icon").click(function () {
                            $(".photoAlbum-item").unbind("click");
                            var event = $(this).data("event");
                            var id = $(this).data("value");
                            var storageType = $(this).data("storage-type");
                            if(event) {
                                del({'ids': id}, storageType);
                            } else {
                                setTimeout(function () {
                                    bindFileItemEvent();
                                })
                            }
                        });
                    },
                    error: $.alert.ajaxError
                });
            }



    </script>





    <#if (config.enableHitokoto == 1 || config.enableHitokoto == "1")>
        <script src="https://v1.hitokoto.cn/?encode=js&c=i&select=.hitokoto" defer></script>
    </#if>

    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/highlight.js@9.12.0/lib/highlight.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/simplemde@1.11.2/dist/simplemde.min.js"></script>
</@footer>
</@compress>
