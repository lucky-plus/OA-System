/* 
* @Author: xgd
* @Date:   2017-10-22 22:04:24
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 22:28:54
*/

Ext.define('Admin.view.activiti.activitiDeploymentGridViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.activitiDeploymentGridViewController',

    activitiDeploymentAdd: function(bt) {
        var cfg = Ext.apply({
            xtype:'activitiWindow'
        },{
            title:'新增部署',
            items:[Ext.apply({xtype:'activitiDeploymentSubmitForm'})]
        });
        Ext.create(cfg);
    },

    activitiDeploymentDelete: function(btn) {
        var grid = btn.up('gridpanel');
        var selModel = grid.getSelectionModel();
        if (selModel.hasSelection()) {
            Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                if (button == "yes") {
                    var selected = selModel.getSelection();
                    var selectIds = []; //要删除的id
                    Ext.each(selected, function (record) {
                        Ext.Ajax.request({ 
                            url : 'repository/deployments/'+record.data.id, 
                            method : 'delete', 
                            success: function(response, options) {
                                var json = Ext.util.JSON.decode(response.responseText);
                                if(json.success){
                                    Ext.toast('操作成功');
                                    grid.getStore().reload();
                                }else{
                                    Ext.toast('操作失败');
                                }
                            }
                        });
                    })
                }
            });
        }
   },
   activitiDeploymentSubmit: function(_this){
        var form = _this.up('form').getForm();
        if(form.isValid()) {
            form.submit({
                url: 'repository/deployments',
                waitMsg: '正在努力上传中',
                success: function(fp, o) {
                        Ext.toast('"' + o.result.name + '"部署成功.', 2000);
                    }
                });
            }
    },
   activitiDeploymentSubmitClose: function(btn){
        var win=btn.up('window');
        if(win){
            win.close();
        }
	}
});