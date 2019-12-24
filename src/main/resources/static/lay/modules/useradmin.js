// /** layuiAdmin.std-v1.0.0 LPPL License By http://www.layui.com/admin/ */
;layui.define(["table", "form"], function (e) {
    var t = layui.$, i = layui.table;
    layui.form;
    i.render({
            elem: "#LAY-user-back-role",
            url: "/admin/api/dept/all",
            cols: [[{type: "checkbox", fixed: "left"},
                {field: "uid", width: 80, title: "ID", sort: !0},
                {field: "name", title: "角色名"},
                {
                    field: "realName",
                    title: "真实姓名",
                    templet: "<div>{{d.realName}}</div>"
                },
                {
                    field: "dept",
                    title: "权限列表",
                    templet: "<div><span id='dept{{d.uid}}'>{{showDept(d.depts)}}</div>"
                }, {
                    title: "操作",
                    width: 150,
                    align: "center",
                    fixed: "right",
                    toolbar: "#table-useradmin-admin"
                }]],
            response: {
                statusName: 'state',
                statusCode: 200,
                msgName: 'msg',
                countName: 'url',
                dataName: 'data'
            },
            text: "对不起，加载出现异常！"
        }), i.on("tool(LAY-user-back-role)", function (e) {
        e.data;
        if ("edit" === e.event) {
            t(e.tr);
            edit(e.data);
        }
    }), e("useradmin", {})
});