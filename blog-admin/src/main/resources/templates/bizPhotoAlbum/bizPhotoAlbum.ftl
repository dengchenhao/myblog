<#include "/include/macros.ftl">
<@header></@header>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <ol class="breadcrumb">
            <li><a href="${config.cmsUrl}/">首页</a></li>
            <li class="active">管理</li>
        </ol>
        <div class="x_panel">
            <div class="x_content">
                <div class="btn-group hidden-xs" id="toolbar">
                    <@shiro.hasPermission name="bizPhotoAlbum:add">
                        <button id="btn_add" type="button" class="btn btn-info" title="新增">
                            <i class="fa fa-plus"></i> 新增
                        </button>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="bizPhotoAlbum:batchDelete">
                        <button id="btn_delete_ids" type="button" class="btn btn-danger" title="批量删除">
                            <i class="fa fa-trash-o"></i> 批量删除
                        </button>
                    </@shiro.hasPermission>
                </div>
                <table id="tablelist">
                </table>
            </div>
        </div>
    </div>
</div>
<@addOrUpdateMOdal defaultTitle="添加">
    <input type="hidden" name="id">
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="name">
            相册名
            <span class="required">*</span>
        </label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" name="name" id="name" required="required" placeholder="name"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="detail">
            描述
            <span class="required">*</span>
        </label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" name="detail" id="detail" required="required" placeholder="detail"/>
        </div>
    </div>
    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="auth">
            相册是否公开
            <span class="required">*</span>
        </label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <select name="auth" id="auth" required="required" class="form-control col-md-7 col-xs-12">
                <option value="">请选择</option>
                <option value="0">是</option>
                <option value="1">否</option>
            </select>
        </div>
    </div>

    <div class="item form-group">
        <label class="control-label col-md-3 col-sm-3 col-xs-3" for="cover">
            封面
            <span class="required">*</span>
        </label>
        <div class="col-md-7 col-sm-7 col-xs-7">
            <input type="text" class="form-control" name="cover" id="cover" required="required" placeholder="cover"/>
        </div>
    </div>
</@addOrUpdateMOdal>
<@footer>
    <script>
        function operateFormatter(code, row, index) {
            var trId = row.id;
            var operateBtn = [
                '<@shiro.hasPermission name="bizPhotoAlbum:edit"><a class="btn btn-xs btn-primary btn-update" data-id="' + trId + '"><i class="fa fa-edit"></i>编辑</a></@shiro.hasPermission>',
                '<@shiro.hasPermission name="bizPhotoAlbum:delete"><a class="btn btn-xs btn-danger btn-remove" data-id="' + trId + '"><i class="fa fa-trash-o"></i>删除</a></@shiro.hasPermission>'
            ];
            return operateBtn.join('');
        }

        $(function () {
            var options = {
                modalName: "",
                url: "${config.cmsUrl}/bizPhotoAlbum/list",
                getInfoUrl: "${config.cmsUrl}/bizPhotoAlbum/get/{id}",
                updateUrl: "${config.cmsUrl}/bizPhotoAlbum/edit",
                removeUrl: "${config.cmsUrl}/bizPhotoAlbum/remove",
                createUrl: "${config.cmsUrl}/bizPhotoAlbum/add",
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'id',
                        title: '',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    },
                    {
                        field: 'name',
                        title: '相册名',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    },
                    {
                        field: 'detail',
                        title: '详情',
                        formatter: function (code) {
                            return code ? code : '-';
                        }
                    },
                    {
                        field: 'auth',
                        title: '相册是否公开',
                        formatter: function (code) {
                            return (code && code == '0') ? '<span class="label label-success">是</span>' : '<span class="label label-danger">否</span>';
                        }
                    },
                    {
                        field: 'cover',
                        title: '封面',
                        formatter: function (code) {
                            return code ? code : '-';
                        },
                        width: '130px'
                    },
                    {
                        field: 'createTime',
                        title: '创建时间',
                        formatter: function (code) {
                            return new Date(code).format("yyyy-MM-dd hh:mm:ss")
                        }
                    },
                    {
                        field: 'updateTime',
                        title: '更新时间',
                        formatter: function (code) {
                            return new Date(code).format("yyyy-MM-dd hh:mm:ss")
                        }
                    },
                    {
                        field: 'operate',
                        title: '操作',
                        width: '130px',
                        formatter: operateFormatter //自定义方法，添加操作按钮
                    }
                ]
            };
            // 初始化table组件
            var table = new Table(options);
            table.init();
        });
    </script>
</@footer>
